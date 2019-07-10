/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author DIGITAL
 */
public class Filiere 
{
    private int id;
    private String filiere;
    Filiere()
    {
        //constructeur par defaut
    }
    public Filiere(String filiere)
    {
        this.filiere = filiere;
            
    }
    public Filiere(int id,String filiere)
    {
        this.filiere = filiere;
        this.id=id;
            
    }

    public void setFiliere(String filiere)
    {
        this.filiere = filiere;
    }
    public int getId()
    {
        return this.id;
    }
    public String getNomFiliere()
    {
        return this.filiere;
    }
    public String toString()
    {
        return("filiere :" +this.filiere);
    }
}
