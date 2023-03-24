/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Siguientes {
    
    private int numeracion;
    private String lexema;
    private ArrayList <Integer> Sig = new ArrayList<Integer>();

    public Siguientes(int numeracion, String lexema) {
        this.numeracion = numeracion;
        this.lexema = lexema;

    }

    public int getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(int numeracion) {
        this.numeracion = numeracion;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public ArrayList<Integer> getSig() {
        return Sig;
    }

    public void setSig(ArrayList<Integer> Sig) {
        this.Sig = Sig;
    }
    
    
    
    
    
}
