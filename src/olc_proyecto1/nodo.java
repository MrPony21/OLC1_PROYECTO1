/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

import java.util.ArrayList;
import static olc_proyecto1.tipo.Tipos;
/**
 *
 * @author Admin
 */
public class nodo {
    
    ArrayList<Integer> first;
    ArrayList<Integer> last;
    boolean anullable;
    
    String lexema;
    Tipos type;
    int number;
    
    boolean accept;
    
    Object left;
    Object right;
    
    ArrayList<nodo> leaves;
    ArrayList<ArrayList> table;
    
    public nodo(String lexema, Tipos type, int number, Object left, Object right, ArrayList<nodo> leaves, ArrayList<ArrayList> table){
        first = new ArrayList();
        last = new ArrayList();
        anullable = true;
        
        this.lexema = lexema;
        this.type = type;
        this.number = number;
        
        accept = "#".equals(this.lexema);
        
        this.left = left;
        this.right = right;
        
        this.leaves = leaves;
        this.table = table;
        
        
    }
    
    //AQUI DEFINIMOS LOS ANULABLES, PRIMEROS Y ULTIMOS
    public nodo getNodo(){
        
        Object leftnodo = this.left instanceof nodo ? ((nodo) this.left).getNodo(): null;
        Object rightnodo = this.right instanceof nodo ? ((nodo)this.right).getNodo(): null;
        
        if(null != this.type) switch (this.type){
            case HOJA:
                this.anullable = false;
                this.first.add(this.number);
                this.last.add(this.number);
                break;
                
            case AND:
                if (leftnodo instanceof nodo && rightnodo instanceof nodo){
                    this.anullable = ((nodo)leftnodo).anullable && ((nodo)rightnodo).anullable;
                    
                    this.first.addAll(((nodo)leftnodo).first);
                    if (((nodo)leftnodo).anullable){
                        this.first.addAll(((nodo)leftnodo).first);
                    }
                    
                    if (((nodo)rightnodo).anullable){
                        this.last.addAll(((nodo)leftnodo).last);
                    }
                    this.last.addAll(((nodo)rightnodo).last);
                           
                }
                break;
                
            case OR:
                if (leftnodo instanceof nodo && rightnodo instanceof nodo){
                    this.anullable = ((nodo)leftnodo).anullable || ((nodo)rightnodo).anullable;
                    
                    this.first.addAll(((nodo)leftnodo).first);
                    this.first.addAll(((nodo)rightnodo).first);
                    
                    this.last.addAll(((nodo)leftnodo).last);
                    this.last.addAll(((nodo)rightnodo).last);
                    
                    
                }
                break;
                
            case KLEENE:
                if (leftnodo instanceof nodo){
                    this.anullable = true;
                    this.first.addAll(((nodo)leftnodo).first);
                    this.last.addAll(((nodo)leftnodo).last);
                            
                }
                break;
            
            case POSITIVA:
                if (leftnodo instanceof nodo){
                    this.anullable = ((nodo)leftnodo).anullable;
                    this.first.addAll(((nodo)leftnodo).first);
                    this.last.addAll(((nodo)leftnodo).last);

                }
                break;
                
            case OPCIONAL:
                if (leftnodo instanceof nodo) {
                    this.anullable = true;
                    this.first.addAll(((nodo)leftnodo).first);
                    this.last.addAll(((nodo)leftnodo).last);
                    
                    
                }
                break;
            
            default:
                break;
             
            
        }
        return this;
        
        
    }
    
    
    //Aqui se calcularan los siguientes:
    public Object siguientes(){
        Object leftSiguiente = this.left instanceof nodo? ((nodo)this.left).siguientes(): null;
        Object rightSiguiente = this.right instanceof nodo? ((nodo)this.right).siguientes(): null;
        
        
        if (null != this.type)switch (this.type){
            case AND:
                for(int item: ((nodo)leftSiguiente).last){
                    hojas hoja = new hojas();
                    nodo node = hoja.getHoja(item, leaves);
                    table tabla = new table();
                    tabla.appendfolow(node.number, node.lexema, ((nodo) rightSiguiente).first, table);
                }
                break;
                
            case KLEENE:
                for(int item: ((nodo)leftSiguiente).last){
                    hojas hoja = new hojas();
                    nodo node = hoja.getHoja(item, leaves);
                    table tabla = new table();
                    tabla.appendfolow(node.number, node.lexema, ((nodo)leftSiguiente).first, table);
                }
                break;
            case POSITIVA:
                for(int item: ((nodo)leftSiguiente).last){
                    hojas hoja = new hojas();
                    nodo node = hoja.getHoja(item, leaves);
                    table tabla = new table();
                    tabla.appendfolow(node.number, node.lexema, ((nodo)leftSiguiente).first, table);
                }
                break;
                
            default:
                break;
           
                        
        }
        return this;
        
        
    }
    
    
}
