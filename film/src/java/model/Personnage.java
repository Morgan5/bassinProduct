/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mandresy
 */
@Entity
public class Personnage {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
     
    @Column(name="nom")
    String nom;
    
    Date date_indispo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDate_indispo() {
        return date_indispo;
    }

    public void setDate_indispo(Date date_indispo) {
        this.date_indispo = date_indispo;
    }
    
    
}
