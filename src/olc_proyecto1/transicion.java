/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class transicion {
   
        ArrayList <trans> transiciones = new ArrayList <trans>();

        public void getTransition(ArrayList <Integer> first ,ArrayList<ArrayList> table){
        
        //primero se crea las transiciones del estado 0
        int contador = 1; 
        boolean primero = true;
        ArrayList<repetido> repetidos = new ArrayList<repetido>();
        int calcular_state = 0;
        
        
        for (Integer fir: first){
            
            for(ArrayList tab: table){
                
                if (fir == tab.get(0)){
                    System.out.println("Se econtro una vez");
                    System.out.println(tab.get(2));
                    
                    String terminal = (String)tab.get(1);
                    ArrayList <Integer> sig = (ArrayList<Integer>) tab.get(2);
                    String calculo = Integer.toString(calcular_state);
                    String ini = "S"+calculo;
                    trans nueva_trans = new trans(ini,terminal , sig);
                    
                    //aqui haremos los finales
                    
                    if (primero){
                        
                        String state = Integer.toString(contador);
                        String fin = "S"+state;
                        nueva_trans.setFinal(fin);
                        repetido rep = new repetido(sig,fin);
                        repetidos.add(rep);
                        primero = false;
                        contador++;
                        
                    }
                    
                    boolean encontrar = false;
                    if (repetidos != null){  
                        for(repetido repe: repetidos){      
                            if (repe.Siguientes == sig){
                                nueva_trans.setFinal(repe.Final_state);
                                encontrar = true;
                                break;
          
                        }
                    }
                        
                    if (encontrar == false){
                        
                        String Ostate = Integer.toString(contador);
                        String Ofin = "S"+Ostate;
                        nueva_trans.setFinal(Ofin);
                        repetido rep = new repetido(sig,Ofin);
                        repetidos.add(rep);
                        contador++;
                    }
                    

                    transiciones.add(nueva_trans);
                    
                }
                
                
            }

            
        }
            
        //for repetidos.get(0)
       
        
     
        
        
        //DefinirFinales();
        
        imprimir();
        

    }
    }
        
        
    public void DefinirFinales(){
        
        int estado = 1;
        for (int i = 0; i < transiciones.size(); i++) {
        
                trans tr = transiciones.get(i);
                String inicial = tr.Inicial;
                ArrayList<Integer> sig = tr.Siguientes;
                
                for (int j = 0; j < transiciones.size(); j++) {
                
                    trans tr2 = transiciones.get(j);
                    
                    if (inicial == tr2.Inicial){
                        
                        if (sig == tr2.Siguientes){
                            
                            String state = Integer.toString(estado);
                            String fin = "S"+state;
                            tr.setFinal(fin);
                            tr2.setFinal(fin);
                            
                        }else{
                            String state = Integer.toString(estado);
                            String fin = "S"+state;
                            estado++;
                            
                            
                        }
                        
                        
                        
                    }
                    
                    
                    
            }
            
            
            
        }
        
        
        
    }    
        
   
        
    
    
    
    
        
    public void imprimir(){
        
        for (int i = 0; i < transiciones.size(); i++) {
            trans tr = transiciones.get(i);
            System.out.println("("+tr.Inicial+","+tr.terminal+") - "+tr.Siguientes+" - "+tr.Final);
            
        }
        
     
        
        
    }
    
   
    
}


class repetido{
    
    
    ArrayList<Integer> Siguientes;
    String Final_state;
    
    
    public repetido(ArrayList<Integer> siguientes, String final_state){
        this.Siguientes = siguientes;
        this.Final_state = final_state;
    }
    
    
    
    
}
