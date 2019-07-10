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
public class Matiere 
{
    private int id;
    private String intitule;
    private int coef;
    public Matiere(String intitule, int coef)
    {
        this.intitule=intitule;
        this.coef=coef;
    }
    
    public Matiere(int id,String intitule, int coef)
    {
        this.id = id;
        this.intitule = intitule;
        this.coef = coef;
    }
    
    public int getId()
    {
        return this.id;
    }
    public String getIntitule()
    {
            return this.intitule;
    }
    public int getCoef()
   {
        return this.coef;
   }

    //setters
    public void setIntitule(String intitule)
    {
            this.intitule=intitule;
    }
    public void setCoef(byte coef)
    {
            this.coef=coef;
    }

    public String toString()
    {
        return ("Intitule :" + this.intitule +"\n Coefficient: "+this.coef);
    }
//public static void main(String[] args)
//{
        
    //}
}

