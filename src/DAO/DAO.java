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
    //@T
import java.util.ArrayList;
  public interface  DAO<T> 
  {
 
  public boolean create(T obj);
  public boolean delete(String field,String value);
  public  boolean update(T obj,String field,String value);
  public  T find(String field,String value);
  public ArrayList<T> getAll();
  
 }