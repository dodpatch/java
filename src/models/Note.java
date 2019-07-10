/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author digital
 */
public class Note {
    float note;
    int id_etd;
    public Note(float note, int id)
    {
        this.note = note;
        this.id_etd = id;
    }
    public float getNote()
    {
        return this.note;
    }
    public int getIdEtd()
    {
        return this.id_etd;
    }
    
}
