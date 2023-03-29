/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.HibernateDao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Morgan
 */
@Controller

public class ActualiteController {

    @Autowired
    HibernateDao dao;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }
    
      @RequestMapping(value = "/insertscene", method = RequestMethod.GET)
    public String form1(Model model, HttpServletRequest request) { // formulaire creer scene
       List<Film>lf=dao.findAll(Film.class);
       List<Plateau>lp=dao.findAll(Plateau.class);
       model.addAttribute("lf",lf);
       model.addAttribute("lp",lp);
       
       return "#";//insert scene
    }
    
       @RequestMapping(value = "/newscene", method = RequestMethod.GET)
    public String savescene(Model model, HttpServletRequest request) {
      Scene s=new Scene();
      Film f=new Film();
      Plateau p=new Plateau();
      p.setId(Integer.parseInt(request.getParameter("idp")));
      f.setId(Integer.parseInt(request.getParameter("idf")));
      s.setFilm(f);
      s.setPlateau(p);
      s.setDuree(Double.parseDouble(request.getParameter("duree")));
      s.setLabel(request.getParameter("label"));
      dao.create(s);
       return "#";
    }
    
    @RequestMapping(value = "/insertaction", method = RequestMethod.GET)
    public String form2(Model model, HttpServletRequest request) { // formulaire creer action
       List<Scene>ls=dao.findAll(Scene.class);
       List<Personnage>lpers=dao.findAll(Personnage.class);
       model.addAttribute("ls",ls);
       model.addAttribute("lpers",lpers);
       
       return "#";//insert scene
    }
  
       @RequestMapping(value = "/newsaction", method = RequestMethod.GET)
    public String saveaction(Model model, HttpServletRequest request) {
     Scene s=new Scene();
     s.setId(Integer.parseInt(request.getParameter("ids")));
     Action a=new Action();
     a.setScene(s);
     a.setLabel(request.getParameter("description"));
     dao.create(a);
     a=dao.findWhere(a).get(0);
     String[] lp=request.getParameterValues("personnage");
        for(int i=0;i<lp.length;i++){
            Personnage pers=new Personnage();
            pers.setId(Integer.parseInt(lp[i]));
            Actionpersonnage ap=new Actionpersonnage();
            ap.setAction(a);
            ap.setPers(pers);
            dao.create(ap);
        }
       return "#";
    }
    
    @RequestMapping(value = "/redirectIntervalleDate", method = RequestMethod.GET)
    public String redirectIntervalleDate(Model model, HttpServletRequest request) {
    int idFilm = Integer.valueOf(request.getParameter("idFilm"));
    //Date d1 = Date.valueOf(request.getParameter("date1"));
    //Date d2 = Date.valueOf(request.getParameter("date2"));
    //model.addAttribute("date1", d1);
    //model.addAttribute("date2",d2);
    model.addAttribute("idFilm", idFilm);
    return "intervalle_date";
    }
    
    
    @RequestMapping(value = "/planningFilm2", method = RequestMethod.GET)
    public String planningFilm2(Model model, HttpServletRequest request){ 
        final int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        Date d1 = Date.valueOf(request.getParameter("date1"));
        Date d2 = Date.valueOf(request.getParameter("date2"));
        
        
        List<Scene> l = dao.findAll(Scene.class);
         List<Scene> ls = new ArrayList<>();
        for(int i=0; i<l.size(); i++){
            if(l.get(i).getFilm().getId() == idFilm){
                ls.add(l.get(i));
            }
        }
        
        List<Actionpersonnage> apOld = dao.findAll(Actionpersonnage.class);
        List<Actionpersonnage> apNew = new ArrayList<>();
        for(int i=0; i<apOld.size(); i++){
            if(apOld.get(i).getAction().getScene().getFilm().getId() == idFilm){
                apNew.add(apOld.get(i));
            }
        }
        
        // idFilm: ap.get(1).getAction().getScene().getFilm().getId();
        // action personnage: ap.get(1).getAction().getLabel();
        //nom personnage: ap.get(1).getPers().getNom();
                
        

        List<Scene> lsFinal = new ArrayList<>();
        ArrayList<List<Scene>> lls = new ArrayList<>();
        // 12h = 720min 
        double countDuree = 0.0;
        HashMap<Integer, LocalDate> map = new HashMap<>();
        // intervalle debut et Fin
        LocalDate dateDebut = d1.toLocalDate();
        LocalDate dateFin = d2.toLocalDate();
        for(int i=0; i<ls.size(); i++){
            if(dateDebut.isBefore(dateFin) || dateDebut.isEqual(dateFin)){
                if(countDuree < 12.0){
                    // verif etat scène: terminé, plateau dispo
                    if(ls.get(i).getEtat() == 3 && !ls.get(i).getPlateau().getDate_indispo().equals(Date.valueOf(dateDebut))){
                        for(int j=0; j<apNew.size(); j++){
                            // verif acteur dispo, idScene list == idScene Action personnage
                           if(!apNew.get(j).getPers().getDate_indispo().equals(Date.valueOf(dateDebut)) && ls.get(i).getId() == apNew.get(j).getAction().getScene().getId()){
                                ls.get(i).setEtat(4);
                                lsFinal.add(ls.get(i));
                                countDuree = countDuree + ls.get(i).getDuree();
                           } 
                           
                        }
                    }
                    
                    if(i == ls.size()-1){
                        lls.add(lsFinal);
                        map.put(lls.size() - 1, dateDebut);
                        dateDebut = dateDebut.plusDays(1);
                    }
                }

                if(countDuree == 12.0){
                    lls.add(lsFinal);
                    map.put(lls.size()-1, dateDebut);
                    lsFinal = new ArrayList<>();
                    dateDebut = dateDebut.plusDays(1);
                    countDuree = 0.0;
                    i = 0;
                }

                if(countDuree > 12.0){
                    lsFinal.get(lsFinal.size()-1).setEtat(3);
                    lsFinal.remove(lsFinal.size()-1);
                    lls.add(lsFinal);
                    map.put(lls.size() - 1, dateDebut);
                    lsFinal = new ArrayList<>();
                    dateDebut = dateDebut.plusDays(1);
                    countDuree = 0.0;
                    i = 0;
                }
            }
            else {
                lls.add(lsFinal);
                map.put(lls.size() - 1, dateDebut);
                break;
            }
            
        }

        model.addAttribute("lls", lls);
        model.addAttribute("map", map);

        //model.addAttribute("ls", ls);
        return "planningFilm";
    }

}
