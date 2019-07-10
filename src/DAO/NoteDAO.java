/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author digital
 */
public class NoteDAO
{
    private DaoFactory daoFactory;
 public NoteDAO(DaoFactory dao)
 {
     this.daoFactory = dao;
 }

    public boolean create(float note, int id_etd, int id_mat ) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
      
      try
      {
          System.out.println("creation");
          Connection connexion=daoFactory.getConnection();
          Statement stmt=connexion.createStatement();
           String sql = "INSERT INTO `notes`(`ID_ETD`, `ID_MAT`, `NOTE`) VALUES ("+id_etd+","+id_mat+","+note+")";
           stmt.executeUpdate(sql);
           return true;
      }
      catch(SQLException e)
      {
          e.printStackTrace();
      }
    return false;
    }
    public boolean update(float note, int id_etd, int id_mat) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     Connection connexion=null;
      Statement stmt=null;
       try 
       {
          System.out.println("mise a jour");
          connexion=daoFactory.getConnection();
          stmt=connexion.createStatement();
          
          String sq="UPDATE `notes` SET `NOTE`="+ note +" WHERE `ID_MAT`="+id_mat+" AND `ID_ETD`="+id_etd;
          stmt.executeUpdate(sq);
          //JOptionPane.showMessageDialog(null, "Après Update !","Confirmation", JOptionPane.ERROR_MESSAGE);
          return true;
        }
       catch(SQLException e)
       {
           e.printStackTrace();
       }
    return false;
    }

}