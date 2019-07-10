package models;
public class Personne
{
	private String nom;
	private String prenom;
	private String date;
        private String lieu;
        private String sexe;
        Personne(){}
	Personne(String nom ,String prenom,String date_naiss,String lieu,String sexe)
	{
            this.nom=nom;
            this.prenom=prenom;
            this.date=date_naiss;
            this.lieu=lieu;
            this.sexe=sexe;
	}

	//geters
	public String getNom()
	{
            return this.nom;
	}
	public String getPrenom()
	{
            return this.prenom;
	}
	public String getDate()
	{
            return this.date;
	}
        public String getLieu()
	{
            return this.lieu;
	}
        public String getSexe()
	{
            return this.sexe;
	}


	//setters
	public void setNom(String nom)
	{
		this.nom=nom;
	}
	public void setPrenom(String prenom)
	{
		this.prenom=prenom;
	}
	public void setDate(String date_naiss)
	{
		this.date=date_naiss;
	}
        public void setLieu(String lieu)
	{
		this.lieu=lieu;
	}
        public void setSexe(String sexe)
	{
		this.sexe=sexe;
	}


}

