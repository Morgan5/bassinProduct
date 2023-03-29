/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Mandresy
 */
@Entity(name="action_personnage")
public class Actionpersonnage {
       @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
     @ManyToOne
    @JoinColumn(name = "idp", referencedColumnName = "id")
    Personnage pers;
     @ManyToOne
    @JoinColumn(name = "ida", referencedColumnName = "id")
    Action action;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Personnage getPers() {
        return pers;
    }

    public void setPers(Personnage plateau) {
        this.pers = plateau;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
     
}
