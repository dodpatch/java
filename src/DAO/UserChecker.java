/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * cette classe permet d'authenfier un utilisateur
 * @author digital
 */

public class UserChecker{
    private static DaoFactory daoFactory;
    String table;
    private String password;
  public UserChecker(DaoFactory dao, String table, String pass)
  {
      this.daoFactory = dao;
      this.password = pass;
      this.table = table;
  }
  
        
  public boolean Check()
  {
      try
      { 
          Connection connexion=this.daoFactory.getConnection();
          Statement stmt=connexion.createStatement();
          String sql="select count(*) from " + table + " where password ='"+ this.password +"'";
          ResultSet rs = stmt.executeQuery(sql);
          if(rs.first())
            return true;
      }
      catch(SQLException e)
      {
          e.printStackTrace();
      }
    return false;
  }
  
}
    
