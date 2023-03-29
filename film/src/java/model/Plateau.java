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
public class Plateau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
     
    @Column(name="label")
    String label;
    
    Date date_indispo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDate_indispo() {
        return date_indispo;
    }

    public void setDate_indispo(Date date_indispo) {
        this.date_indispo = date_indispo;
    }
    
    
    
}
