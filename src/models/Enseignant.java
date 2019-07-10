package models;

import java.util.ArrayList;

public class Enseignant extends Personne
{
        private int id;
	private ArrayList<Matiere> listMatiere = null;
	private String titre;
        private Filiere filiere = null;
        private String password = null; // proteger l'acces à la page d'un prog pour la saisie des notes
	
        Enseignant()
        {
            //constructeur par defaut
        }
	public Enseignant(String nom,String prenom,String date_naiss,String lieu,String sexe,String titre)
	{
            super(nom,prenom,date_naiss,lieu,sexe);
            this.titre=titre;
	}
        //contructeur avec un identifiant
       public Enseignant(int id,String nom,String prenom,String date_naiss,String lieu,String sexe,String titre)
	{
            super(nom,prenom,date_naiss,lieu,sexe);
            this.id=id;
            this.titre=titre;
	}
       //constructeur avec une filiere
       public Enseignant(String nom,String prenom,String date_naiss,String lieu,String sexe,String titre,Filiere filiere)
	{
            super(nom,prenom,date_naiss,lieu,sexe);
            this.titre=titre;
            this.filiere = filiere;
	}
       public Enseignant(String nom,String prenom,String date_naiss,String lieu,String sexe,String titre,Filiere filiere,String pwd)
	{
            super(nom,prenom,date_naiss,lieu,sexe);
            this.titre=titre;
            this.filiere = filiere;
            this.password = pwd;
	}

	//geters
       public int getId()
       {
           return this.id;
       }
	public ArrayList getListMatiere()
	{
             return this.listMatiere;
	}
	public String getTitre()
	{
            return this.titre;
	}
        public Filiere getFiliere()
	{
            return this.filiere;
	}
        public String getPassword()
	{
            return this.password;
	}

	//setters
	public void setListMatiere(ArrayList list)
	{
            this.listMatiere = list;
	}
	public void setTitre(String titre)
	{
            this.titre=titre;
	}

	public String toString()
	{
            return("Nom: "+this.getNom()+"\nPrenom: "+this.getPrenom()+"\nAge: " +this.getDate() +"\nTitre: "+this.titre+ "\n");
	}
	
}

