package DAO;
import java.sql.*;
//import java.DriverManager;
//import java.sql.SQLException;
public class DaoFactory
{
  private static String username;
  private static String password;
  private static String dbname;
  public static int driverState=0;
  public int connectionState=0;
//pvate static String password;
  public DaoFactory(String dbname, String username, String password)
  {
      this.dbname = dbname;
      this.username = username;
      this.password = password;
  }
   public static DaoFactory getInstance()
   {
       try
       {
           Class.forName("com.mysql.jdbc.Driver");
           driverState=1;
           //System.out.println("Driver Load success");
       }
       catch(Exception e)
       {
           System.out.println("le driver n'a pas pu être chargé");
       }
       DaoFactory instance=new DaoFactory(dbname, username, password);
       return instance;
   }
   public Connection getConnection() throws SQLException
   {
        return DriverManager.getConnection("jdbc:mysql://localhost/"+dbname, username, password);
   }
   public EtudiantDAO getEtudiantDAO()
   {
       return new EtudiantDAO(this);
   }
   
   public EnseignantDAO getEnseignantDAO()
   {
       return new EnseignantDAO(this);
   }
   
   public MatiereDAO getMatiereDAO()
   {
       return new MatiereDAO(this);
   }
   public FiliereDAO getFiliereDAO()
   {
      return new FiliereDAO(this);
   }
   public NoteDAO getNoteDAO()
   {
      return new NoteDAO(this);
   }
}