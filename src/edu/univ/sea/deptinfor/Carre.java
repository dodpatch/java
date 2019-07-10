/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.univ.sea.deptinfor;

/**
 *
 * @author DIGITAL
 */
public class Carre extends Forme{
	private float cote;
	Carre(float cote){
		this.cote=cote;
	}
	public float getCote(){
		return this.cote;
	}
	public void setCode(float c){
		this.cote=c;
	}
    public double perimetre(){
    	return this.cote*4;
    }

    public double surface(){
    	return this.cote*this.cote;
    }
}