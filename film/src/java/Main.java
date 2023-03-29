
import dao.HibernateDao;
import java.util.ArrayList;
import java.util.List;
import model.Film;
import model.Scene;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mandresy
 */
public class Main {
    //@Autowired
    HibernateDao dao;
    public void main(String args[]){
        Scene scene = new Scene();
        Film film = new Film();
        film.setId(1);
        scene.setFilm(film);
        List<Scene> ls = dao.findWhere(scene);
        System.out.print(ls.size());
    }
}
