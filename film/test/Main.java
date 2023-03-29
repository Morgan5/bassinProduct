
import dao.HibernateDao;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
//import model.Admin;
//import model.Sujet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author morga
 */
public class Main {
    @Autowired
    HibernateDao dao;
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        HibernateDao dao = context.getBean(HibernateDao.class);
        System.out.println(dao);
        
        //System.out.print(hdao.findWhere(sujet));
    }
}
