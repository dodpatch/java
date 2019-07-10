package models;

import java.util.HashMap;
import java.util.Map;

public class Etudiant extends Personne
{
    private int id;
    private Filiere filiere;
    private String niveau;
    private String departement;
    private String matricule=null;
    HashMap listNote = null;
        //constructeur par defaut
    public Etudiant(){}  
    
    public Etudiant(String nom, String prenom, String date_naiss,String lieu,String sexe, String matricule, Filiere fil, String niveau)
    {
        super(nom,prenom,date_naiss,lieu,sexe);
        this.niveau = niveau;
        this.filiere = fil;
        this.matricule = matricule;
    }
    public Etudiant(int id, String nom, String prenom, String date_naiss,String lieu,String sexe, String matricule, Filiere fil, String niveau)
    {
        super(nom,prenom,date_naiss,lieu,sexe);
        this.id = id;
        this.niveau = niveau;
        this.filiere = fil;
        this.matricule = matricule;
    }
    
    //un etudiant qui n'a pas de matricule
     public Etudiant(String nom,String prenom,String date_naiss,String lieu,String sexe, Filiere fil, String niveau)
    {
        super(nom,prenom,date_naiss,lieu,sexe);
        this.niveau=niveau;
        this.filiere=fil;
    }

	//geters
        public int getId()
        {
           return this.id;
        }
	public String getNiveau()
	{
         return this.niveau;
	}
	public Filiere getFiliere()
	{
            return this.filiere;
	}
	public String getDepartement()
	{
             return this.departement;
	}
	public String getMatricule()
	{
            return this.matricule;
	}
        public HashMap getListNote()
        {
            return this.listNote;
        }
	//setters
	public void setNiveau(String niveau)
	{
            this.niveau=niveau;
	}
	public void setFiliere(Filiere filiere)
	{
            this.filiere=filiere;
	}
	public void setDepartement(String departement)
	{
            this.departement=departement;
	}
	public void setMatricule(String matricule)
	{
            this.matricule=matricule;
	}
        public void setListNote(HashMap list)
        {
            this.listNote = list;
        }
	

	
	public String toString()
	{

            return("Nom: "+this.getNom()+"\nPrenom: "+this.getPrenom()+"\nDate Naissance:" +this.getDate() +"\nFilere: "+ this.filiere.getNomFiliere()+"\nNiveau: "+this.niveau+"\nMatricule:"+this.matricule+"\n");
	}
	
}