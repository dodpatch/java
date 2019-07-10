package DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
  
  private static final String CREATE_TABLE_ETUDIANT="CREATE TABLE idopaul.etudiants ("
                    + "ID_ETD int(255) NOT NULL AUTO_INCREMENT,"
                    + "ID_FIL int(255) NOT NULL,"
                    + "NOM varchar(26) DEFAULT NULL,"
                    + "PRENOM varchar(26) DEFAULT NULL,"
                    + "DATE varchar(10) DEFAULT NULL,"
                    + "LIEU varchar(26) DEFAULT NULL,"
                    + "SEXE varchar(1) NOT NULL,"
                    + "MATRICULE varchar(10) DEFAULT NULL,"
                    + "NIVEAU varchar(10) NOT NULL,"
                    + "PRIMARY KEY (ID_ETD),"
                    + " KEY FK_INSCRIRE(ID_FIL))";
  
private static final String CREATE_TABLE_ENSEIGNANT="CREATE TABLE idopaul.enseignants ("
                    + "ID_ENS int(255) NOT NULL AUTO_INCREMENT,"
                    + "ID_FIL int(255) NOT NULL,"
                    + "NOM varchar(26) DEFAULT NULL,"
                    + "PRENOM varchar(26) DEFAULT NULL,"
                    + "DATE varchar(10) DEFAULT NULL,"
                    + "LIEU varchar(26) DEFAULT NULL,"
                    + "SEXE varchar(1) NOT NULL,"
                    + "TITRE varchar(26) DEFAULT NULL,"
                    + "PRIMARY KEY (ID_ENS),"
                    + "KEY FK_FILIERE_ENSEIGNANT (ID_FIL))";
   
private static final String CREATE_TABLE_MATIERE="CREATE TABLE idopaul.matieres ("
                    + "ID_MAT int(255) NOT NULL AUTO_INCREMENT,"
                    + "INTITULE varchar(52) NOT NULL,"
                    + "COEF int(255) NOT NULL,"
                    + "PRIMARY KEY (ID_MAT))";
  
    
private static final String CREATE_TABLE_NOTE="CREATE TABLE idopaul.notes ("
                    + "ID_NOTE int(255) NOT NULL AUTO_INCREMENT,"
                    + "ID_ETD int(255) NOT NULL,"
                    + "ID_MAT int(255) NOT NULL,"
                    + "NOTE decimal(8,0) DEFAULT NULL,"
                    + "PRIMARY KEY (ID_NOTE),"
                    + "KEY FK_ETUDIANTS_NOTES (ID_ETD),"
                    + "KEY FK_MATIERES_NOTES (ID_MAT))";
              
private static final String CREATE_TABLE_ENSEIGNER="CREATE TABLE idopaul.enseigner ("
                  + "ID_MAT int(255) NOT NULL,"
                  + "ID_ENS int(255) NOT NULL,"
                  + "COURS tinyint(1) DEFAULT '1',"
                  + "JURY tinyint(1) DEFAULT '0',"
                  + "PRIMARY KEY (ID_MAT,ID_ENS),"
                  + "KEY FK_ENSEIGNER (ID_ENS))";
private static final String CREATE_TABLE_FILIERE="CREATE TABLE idopaul.filieres("
                  + "ID_FIL int(255) NOT NULL AUTO_INCREMENT,"
                  + "FILIERE varchar(26) DEFAULT NULL,"
                  + "PRIMARY KEY (ID_FIL))";

private static final String CREATE_TABLE_FILIERE_MATIERE="CREATE TABLE idopaul.filiere_matiere("
                  + "ID_FIL INT(255) NOT NULL,"
                  + "ID_MAT INT(255) NOT NULL,"
                  + "PRIMARY KEY (ID_FIL,ID_MAT),"
                  + "KEY FK_FILIERES_MATIERES2 (ID_MAT))";
public void createDatabase(Connection conn)
{   
    Statement stmt = null;
    try 
    {

      stmt = conn.createStatement();
      stmt.executeUpdate(CREATE_TABLE_FILIERE);
      stmt.executeUpdate(CREATE_TABLE_NOTE);
      stmt.executeUpdate(CREATE_TABLE_ENSEIGNER);
      stmt.executeUpdate(CREATE_TABLE_ENSEIGNANT);
      stmt.executeUpdate(CREATE_TABLE_ETUDIANT);
      stmt.executeUpdate(CREATE_TABLE_MATIERE);
      stmt.executeUpdate(CREATE_TABLE_FILIERE_MATIERE);
      System.out.println("Tables created");

    } catch (SQLException e) 
    {
      e.printStackTrace();
    } 
    finally 
    {
      try {
        // Close connection
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
        

