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
import java.util.ArrayList;
import java.sql.*;
import models.Enseignant;
import models.Matiere;
import models.Filiere;

public class EnseignantDAO implements DAO<Enseignant> {
  private final DaoFactory daoFactory;
  public EnseignantDAO(DaoFactory daoFactory)
  {
      this.daoFactory=daoFactory;
  }
  @Override
  
  public boolean create(Enseignant ens) {
    try
    {
      int var = 1;
      Connection connexion=daoFactory.getConnection();
       Statement stmt=connexion.createStatement();
       String sql="insert into enseignants(ID_FIL,NOM,PRENOM,DATE,LIEU,SEXE,TITRE,PASSWORD) values("+
       ens.getFiliere().getId() +",'"+
       ens.getNom()+"','"+
       ens.getPrenom()+"','"+
       ens.getDate()+"','"+
       ens.getLieu()+"','"+
       ens.getSexe()+"','"+
       ens.getTitre()+"','"+
       ens.getPassword()+ "')";
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
  public boolean delete(int index) {
       try 
       {
            Connection connexion=daoFactory.getConnection();
            PreparedStatement stmt=connexion.prepareStatement("delete from enseignants where id=?");
            stmt.setInt(1,index);
            stmt.executeUpdate();
       }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       return true;
  }
   
  @Override
  public boolean update(Enseignant ens, int id) {
    Connection connexion=null;
      Statement stmt=null;
       try 
       {
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          String sql="update enseignants set "+ 
                  "nom='"+ ens.getNom()+"',"+ 
                  "prenom='"+ ens.getPrenom()+"',"+ 
                  "date='"+ ens.getDate()+"',"+ 
                  "lieu='"+ ens.getLieu()+"',"+ 
                  "sexe='"+ ens.getSexe()+"',"+ 
                  "matricule='"+ ens.getTitre()+"',"+ 
                  "where id = " + id +"";
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
  public Enseignant find(String field, String value) 
  {
     Enseignant enseignant=null;
     Statement stmt=null;
     ResultSet rs = null,rsMat = null;
     String sql;
    try 
    {
        ArrayList<Matiere> list = new ArrayList();
        Matiere matiere;
        Connection connexion=daoFactory.getConnection();
        stmt = connexion.createStatement();
        sql = "select * from enseignants where " + field + " ='" + value +"'";
        rs=stmt.executeQuery(sql);
        if(rs.next())
        {
            enseignant = new Enseignant(
            rs.getInt("ID_ENS"),
            rs.getString("NOM"),
            rs.getString("PRENOM"),
            rs.getString("DATE"),
            rs.getString("LIEU"),
            rs.getString("SEXE"),
            rs.getString("TITRE"));  
            sql = "select INTITULE, COEF from matieres, enseigner where"+
            " matieres.ID_MAT = enseigner.ID_MAT and enseigner.ID_ENS = " + rs.getInt("ID_ENS") + ""; 
            rsMat = stmt.executeQuery(sql);
            while(rsMat.next())
            {
                matiere = new Matiere(rsMat.getString("INTITULE"), rsMat.getByte("COEF"));
                list.add(matiere);
            }
            enseignant.setListMatiere(list);
        }
    } 
    catch (SQLException e) 
    {
    }
    return enseignant;
  }
  
    /**
     *
     * @return
     */
    @Override
  public ArrayList<Enseignant> getAll(){
      Enseignant enseignant=null;
      ResultSet rs = null, rsMat = null;
      Statement stmt2 = null;
      String sql;
      ArrayList<Enseignant> listEnseignant=null;
    try {
           Matiere matiere = null;
           Connection connexion=daoFactory.getConnection();
           PreparedStatement stmt=connexion.prepareStatement("select * from enseignants");
           rs=stmt.executeQuery();
           listEnseignant = new ArrayList<Enseignant>();
           stmt2 = connexion.createStatement();
            while(rs.next())
           {
               ArrayList<Matiere> list = new ArrayList();
              enseignant = new Enseignant(
              rs.getInt("ID_ENS"),
              rs.getString("NOM"),
              rs.getString("PRENOM"),
              rs.getString("DATE"),
              rs.getString("SEXE"),
              rs.getString("LIEU"),
              rs.getString("TITRE"));
              sql = "select INTITULE, COEF from matieres, enseigner where"+
            " matiereS.ID_MAT = enseigner.ID_MAT and enseigner.ID_ENS = " + rs.getInt("ID_ENS") + ""; 
            rsMat = stmt2.executeQuery(sql);
            while(rsMat.next())
            {
                matiere = new Matiere(rsMat.getString("INTITULE"), rsMat.getInt("COEF"));
                list.add(matiere);
            }
              enseignant.setListMatiere(list);
              listEnseignant.add(enseignant);
           }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    return listEnseignant;
  }
  
}