/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import models.Filiere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Etudiant;

/**
 *
 * @author DIGITAL
 */
public class FiliereDAO implements DAO<Filiere>{
  private final DaoFactory daoFactory;
  FiliereDAO(DaoFactory daoFactory)
  {
    this.daoFactory=daoFactory;
  }

    @Override
    public boolean create(Filiere fil)
    {  
        try
        {
            Connection connexion=daoFactory.getConnection();
            Statement stmt=connexion.createStatement();
            String sql="insert into filieres(FILIERE) values('"+fil.getNomFiliere()+"')" ;  
            return true;
        }
        catch(SQLException e)
        {
            
        }
        return false;
    }

    @Override
    public boolean delete(int id)
    {
      Connection connexion=null;
      Statement stmt=null;
       try 
       {
            connexion=daoFactory.getConnection();
            String sql = "delete FROM filieres where ID_FIL =" + id + "";
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
    public boolean update(Filiere fil,int id) 
   {
      Connection connexion=null;
      Statement stmt=null;
       try 
       {
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          String sql="update filieres set "+ 
                  "filiere='"+ fil.getNomFiliere()+"'"+ 
                  " where ID_FIL = " + id + "";
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
    public Filiere find(String field, String value) 
    {
        return null;
    }

    @Override
    public ArrayList<Filiere> getAll(){
      Etudiant etudiant=null;
      ArrayList<Filiere> listFiliere=null;
      Connection connexion=null;
      PreparedStatement stmt=null;
      ResultSet rs=null;
    try 
    {
        connexion=daoFactory.getConnection();
        stmt=connexion.prepareStatement("Select * from filieres");
        rs=stmt.executeQuery();
        listFiliere = new ArrayList<Filiere>();
        while(rs.next())
        {
            Filiere fil = new Filiere(rs.getInt("ID_FIL"),rs.getString("FILIERE")); 
            listFiliere.add(fil);
        }
    }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    return listFiliere;
  }

}