/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author digital
 */
public class EnsMat {

 private DaoFactory daoFactory;
 public EnsMat(DaoFactory dao)
 {
     this.daoFactory = dao;
 }

    public boolean create(int id_mat, int id_ens) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
      try
      {
          Connection connexion=daoFactory.getConnection();
          Statement stmt=connexion.createStatement();
           String sql="insert into enseigner(ID_MAT, ID_ENS) values("+
           id_mat+","+
           id_ens+")";      ;
           stmt.executeUpdate(sql);
           return true;
      }
      catch(SQLException e)
      {
          e.printStackTrace();
      }
    return false;
    }
    public boolean update(int id_mat, int id_ens) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     Connection connexion=null;
      Statement stmt=null;
       try 
       {
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          String sql="update enseigner set "+ 
                  "ID_MAT="+ id_mat +""+
                  " where ID_ENS = " + id_ens + "";
          stmt.executeUpdate(sql);
          return true;
        }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
    return false;
    }

}
    

