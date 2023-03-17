/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;
import olc_proyecto1.tipo.Tipos;

/**
 *
 * @author Admin
 */
public class tree {
    
    nodo root;
    
    public tree(String exp, ArrayList<nodo> leaves, ArrayList<ArrayList> table){
        
        num_hojas numHoja = new num_hojas(exp);
        Stack pila = new Stack();
        String[] expred = exp.split("");
        
        ArrayList<String> strexp = new ArrayList<>(Arrays.asList(expred));
        Collections.reverse(strexp);
        
        strexp.forEach((n) -> {
            
            switch(n){
                case "|":
                    nodo leftor = (nodo) pila.pop();
                    nodo rightor = (nodo) pila.pop();
                    
                    nodo Nodoor = new nodo(n, Tipos.OR,0, leftor, rightor, leaves,table);
                    pila.push(Nodoor);
                   
                    break;
                
                case ".":
                    
                    nodo leftand = (nodo) pila.pop();
                    nodo rightand = (nodo) pila.pop();
                    
                    nodo Nodoand = new nodo(n, Tipos.AND, 0, leftand, rightand, leaves, table);
                    pila.push(Nodoand);
                    
                    break;
                 
                case "*":
                    
                    nodo leftkle = (nodo) pila.pop();
   
                    nodo Nodokle = new nodo (n, Tipos.KLEENE, 0, leftkle, null, leaves, table);
                    pila.push(Nodokle);
                    
                    break;
                    
                case "+":
                    
                    nodo leftpos = (nodo) pila.pop();
                    
                    nodo Nodopos = new nodo(n, Tipos.POSITIVA, 0, leftpos, null, leaves, table);
                    pila.push(Nodopos);
                    
                    break;
                    
                case"?":
                    
                    nodo leftop = (nodo) pila.pop();
                    
                    nodo Nodoop = new nodo(n, Tipos.OPCIONAL, 0, leftop, null, leaves, table);
                    pila.push(Nodoop);
                    
                    break;
                
                default:
                    nodo NodoHoja = new nodo(n, Tipos.HOJA, numHoja.getNum(), null, null, leaves, table);
                    pila.push(NodoHoja);
                    hojas Hoja = new hojas();
                    Hoja.addHoja(NodoHoja, leaves);
                    break;
                                       
            }
                 
            
            
            
        });
        this.root = (nodo) pila.pop();
    }
    
    public nodo getRoot(){
        return this.root;
    }
    
    
    
    
    
    
    
   
    
    
}
