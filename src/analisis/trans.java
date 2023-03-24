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
public class trans {
    
    
    String Inicial;
    String terminal;
    ArrayList <Integer> Siguientes = new ArrayList();
    String Final;
    
    
    
    public trans(String Inicial, String terminal, ArrayList <Integer> Siguientes){
        this.Inicial = Inicial;
        this.terminal = terminal;
        this.Siguientes = Siguientes;
        //this.Final = Final;
        

    }
    
   
    public void setFinal(String Final){
        this.Final = Final;
    }
    
}
    
    
    
    
    

