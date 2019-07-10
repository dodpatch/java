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
import models.Matiere;
public class MatiereDAO implements DAO<Matiere> {
    private final DaoFactory daoFactory;
  public MatiereDAO(DaoFactory daoFactory)
  {
    this.daoFactory=daoFactory;
  }

  public boolean create(Matiere mat) {
      
      try
      {
          Connection connexion=daoFactory.getConnection();
          Statement stmt=connexion.createStatement();
           String sql="insert into matieres(INTITULE,COEF) values('"+
           mat.getIntitule()+"',"+
           mat.getCoef()+")";
           stmt.executeUpdate(sql);
      }
      catch(SQLException e)
      {
          e.printStackTrace();
      }
    return true;
  }

    /**
     *
     * @param index
     * @return
     */
    @Override
  public boolean delete(int index) {
       try 
       {
           Connection connexion=daoFactory.getConnection();
           PreparedStatement stmt=connexion.prepareStatement("delete from matieres where ID_MAT=?");
           stmt.setInt(1, index);
       }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
       return true;
  }
   
    /**
     *
     * @param mat
     * @param id
     * @return
     */
    @Override
  public boolean update(Matiere mat, int id) {
     Connection connexion=null;
      Statement stmt=null;
       try 
       {
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          String sql="update matieres set "+ 
                  "intitule='"+ mat.getIntitule()+"'"+
                  "coef="+ mat.getCoef() +""+
                  " where ID_MAT = " + id + "";
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
  public Matiere find(String field, String value) 
  {
      Matiere matiere = null;
     Statement stmt = null;
    try 
    {
        Connection connexion=daoFactory.getConnection();
        String sql = "select * from matieres where " + field + " =" + value +"";
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next())
        {
            matiere = new Matiere(
            rs.getInt("ID_MAT"),
            rs.getString("INTITULE"),
            rs.getInt("COEF"));  
        }
    } 
    catch (SQLException e) 
    {
    }
    return matiere;
  }
  public ArrayList<Matiere> getAll(){
      Matiere matiere=null;
      ArrayList<Matiere> listMatiere=null;
    try {
           Connection connexion=daoFactory.getConnection();
            PreparedStatement stmt=connexion.prepareStatement("Select * from matieres");
            ResultSet rs=stmt.executeQuery();
            listMatiere = new ArrayList<Matiere>();
         while(rs.next())
          {
              matiere = new Matiere(
              rs.getString("intitule"),
              rs.getInt("coef"));
              listMatiere.add(matiere);
           }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    return listMatiere;
  }
  
}
