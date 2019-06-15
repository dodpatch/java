/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author DIGITAL
 */
import DAO.DAO;
import java.util.ArrayList;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import models.Etudiant;
import models.Filiere;
import models.Matiere;
//CTRL + SHIFT + O pour générer les imports
public class EtudiantDAO implements DAO<Etudiant> {
  private final DaoFactory daoFactory;
  EtudiantDAO(DaoFactory daoFactory)
  {
      this.daoFactory=daoFactory;
  }
  @Override
  public boolean create(Etudiant etd) {
      try
      { 
          Connection connexion=daoFactory.getConnection();
          Statement stmt=connexion.createStatement();
          String sql="insert into etudiants(ID_FIL,NOM,PRENOM,DATE,LIEU,SEXE,MATRICULE,NIVEAU) values("+
           etd.getFiliere().getId()+",'"+
           etd.getNom()+"','"+
           etd.getPrenom()+"','"+
           etd.getDate()+"','"+
           etd.getLieu()+"','"+
           etd.getSexe()+"','"+
           etd.getMatricule()+"','"+
           etd.getNiveau() +"')" ; 
           stmt.executeUpdate(sql);
           System.out.println("operation effectuée avec succes");
           return true;
      }
      catch(SQLException e)
      {
          e.printStackTrace();
      }
    return false;
  }

    /**
     *
     * @param field
     * @param value

     * @return
     */
    @Override
  public boolean delete(int id) 
  {
      Connection connexion=null;
      PreparedStatement stmt=null;
       try 
       {
            connexion=daoFactory.getConnection();
            stmt=connexion.prepareStatement("delete FROM etudiants where id = "+ id +"");
            stmt.executeUpdate();
            return true;
        }
       catch(SQLException e)
       {
          System.out.println("oops un probleme est survenu. Veillez reessayer");
       }
       
       return false;
  }
   
  public boolean update(Etudiant etd, int id) {
      Connection connexion=null;
      Statement stmt=null;
       try 
       {
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          String sql="update etudiants set "+ 
                  "id_fil ="+ etd.getFiliere().getId()+ ","+
                  "nom='"+ etd.getNom()+"',"+ 
                  "prenom='"+ etd.getPrenom()+"',"+ 
                  "date='"+ etd.getDate()+"',"+ 
                  "lieu='"+ etd.getLieu()+"',"+ 
                  "sexe='"+ etd.getSexe()+"',"+ 
                  "matricule='"+ etd.getMatricule()+"',"+ 
                  "niveau='"+ etd.getNiveau()+"',"+ 
                  " where id = " + id +"";
          stmt.executeUpdate(sql);
          return true;
        }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
    return false;
  }
   
  @Override
   public Etudiant find(String field, String value) 
  {
        Etudiant etudiant=null;  
        String sql=null;
        //Connection connexion=null;
       // PreparedStatement stmt=null;
        ResultSet rs=null;

        try 
        {
            
            Connection connexion=daoFactory.getConnection();
            Statement stmt=connexion.createStatement();
//            "+var+"="+value;
            sql="select * from etudiants where "+field+"='"+value+"'"; 
            
            rs=stmt.executeQuery(sql);
            if(rs.first())
            {
                sql = "select * from filieres where ID_FIL = " + rs.getInt("ID_FIL") +"";
                ResultSet r = stmt.executeQuery(sql);
                Filiere fil = new Filiere(r.getInt("ID_FIL"), rs.getString("FILIERE"));
                etudiant = new Etudiant(
                rs.getInt("ID_ETD"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("date"),
                rs.getString("lieu"),
                rs.getString("sexe"),
                rs.getString("matricule"),
                fil,
                rs.getString("niveau")); 
                // recuperation de la note
                ResultSet rsMat = null, rsNote = null;
                Statement stmt2 = null, stmt3 = null;
                HashMap list = new HashMap();
                sql = "select NOTE, ID_MAT from notes where ID_ETD = " + rs.getInt("ID_ETD") + "";
                rsNote = stmt2.executeQuery(sql);
                while(rsNote.next())
                {
                    sql= "select INTITULE from matieres where ID_MAT = " + rsNote.getInt("ID_MAT") + "";
                    rsMat = stmt3.executeQuery(sql);
                     if(rsMat.first())
                         list.put(rsMat.getString("INTITULE"), rs.getFloat("NOTE")); 
                }  
                etudiant.setListNote(list);
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        return etudiant;
   }
  public ArrayList<Etudiant> getAll(){
      Etudiant etudiant=null;
      ArrayList<Etudiant> listEtudiant = null;
      Connection connexion = null;
      Statement stmt = null, stmt2 = null, stmt3 = null, stmt4 = null;
      ResultSet rs = null, rsNote = null, rsMat = null;
      ResultSet r = null;
      String sql, sql2;
    try 
    {
        connexion = daoFactory.getConnection();
        stmt = connexion.createStatement();
        int id = 1;
        sql = "Select * from etudiants";
        rs = stmt.executeQuery(sql);
        listEtudiant = new ArrayList<Etudiant>();
        while(rs.next())
        {
             HashMap list = new HashMap();
//            String getN="select NIVEAU from niveaux where ID_NV = "+ rs.getInt("ID_ETD") +"";
//            String getFil = "select filiere from filieres where ID_FIL = " + rs.getInt("ID_FIL") +"";
//            String getDep = "select DEP from niveaux where ID_DEP = " + rs.getInt("ID_DEP") +"";
//            stmt = connexion.prepareStatement(getN);
//            ResultSet rsgetN = stmt.executeQuery();
//            ResultSet rsgetFil = stmt.executeQuery();
//            ResultSet rsgetDep = stmt.executeQuery();
            
            sql = "select * from filieres where ID_FIL = " + rs.getInt("ID_FIL") + "";
            Filiere fil = null;
            stmt2 = connexion.createStatement();
            stmt3 = connexion.createStatement();
            stmt4 = connexion.createStatement();
            r = stmt2.executeQuery(sql);
            if(r.first())
            fil = new Filiere(r.getString("FILIERE"));
            etudiant = new Etudiant(
            rs.getInt("ID_ETD"),
            rs.getString("NOM"),
            rs.getString("PRENOM"),
            rs.getString("DATE"),
            rs.getString("LIEU"),
            rs.getString("SEXE"),
            rs.getString("MATRICULE"),
            fil,
            rs.getString("NIVEAU"));
            sql = "select NOTE, ID_MAT from notes where ID_ETD = " + rs.getInt("ID_ETD") + "";
            rsNote = stmt3.executeQuery(sql);
            while(rsNote.next())
            {
               sql= "select INTITULE from matieres where ID_MAT = " + rsNote.getInt("ID_MAT") + "";
               rsMat = stmt4.executeQuery(sql);
                if(rsMat.first())
                    list.put(rsMat.getString("INTITULE"), rsNote.getFloat("NOTE")); 
            }
            etudiant.setListNote(list);
            listEtudiant.add(etudiant);
        }
    }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    return listEtudiant;
  }
  public static void main(String[] args)
  {
    DaoFactory daoFactory = null;
    String dbname = "seainfo20162017l3s6";
    String username = "idopaul";
    String password = "12231381";
    daoFactory = new DaoFactory(dbname, username, password);
    ArrayList<Etudiant> listEtudiant = daoFactory.getEtudiantDAO().getAll(); ;
    ArrayList<Matiere> listMatiere = null;
    listMatiere = daoFactory.getMatiereDAO().getAll();
    for(Etudiant e : listEtudiant)
    {
        System.out.println(e.getNom());
        for(Matiere m : listMatiere)
    {   System.out.println(m.getIntitule());
        System.out.println(e.getListNote().get(m.getIntitule()));
    }
    }
    
  }
}
