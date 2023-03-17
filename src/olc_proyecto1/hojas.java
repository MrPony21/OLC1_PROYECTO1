/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class hojas {
    
    public void addHoja(nodo nodo, ArrayList<nodo> hojas){
        hojas.add(nodo);
    }
    
    public nodo getHoja(int numLeave, ArrayList<nodo> hojas){
        for (nodo item: hojas){
            if (item.number == numLeave){
                return item;
            }
        }
        return null;
    } 
    
    
    public boolean isAccept(int numLeave, ArrayList<nodo> hojas){
        for (nodo item: hojas){
            if (item.number == numLeave){
                return item.accept;
            }
        }
        return false;
    }
  
    
}
