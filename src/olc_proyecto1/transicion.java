/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author Admin
 */
public class transicion {
   
        ArrayList <trans> transiciones = new ArrayList <trans>();
        ArrayList <repetido> repetidos = new ArrayList<repetido>();

        public void getTransition(ArrayList <Integer> first ,ArrayList<ArrayList> table){
        
            //primero se crea las transiciones del estado 0

            boolean primero = true;
            //ArrayList<repetido> repetidos = new ArrayList<repetido>();            
            int contador = 0 ; 

            for (Integer fir: first){

                for(ArrayList tab: table){

                    if (fir == tab.get(0)){
                        System.out.println("Se econtro una vez");
                        System.out.println(tab.get(2));
                        

                        String terminal = (String)tab.get(1);
                        ArrayList <Integer> sig = (ArrayList<Integer>) tab.get(2);           
                        String ini = "S0";
                        trans nueva_trans = new trans(ini,terminal , sig);

                        //aqui haremos los finales

                        if (primero){
                            contador++;
                            String state = Integer.toString(contador);
                            String fin = "S"+state;
                            nueva_trans.setFinal(fin);
                            //repetido rep = new repetido(sig,fin);
                            //repetidos.add(rep);
                            primero = false;
                            

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
                        }

                        if (encontrar == false){

                            String Ostate = Integer.toString(contador);
                            String Ofin = "S"+Ostate;
                            nueva_trans.setFinal(Ofin);
                            //repetido rep = new repetido(sig,Ofin);
                            contador++;
                        }


                        transiciones.add(nueva_trans);




                }


            }


        }
        
        
        for(repetido rep : repetidos){
            System.out.println("Se genera: "+rep.Final_state);
            generartrans(rep.Final_state, rep.Siguientes,table);
        }
        /*
        generartrans("S1", repetidos.get(0).Siguientes,table);
        generartrans ("S2",repetidos.get(1).Siguientes, table);
        */
        
        imprimir();
    }
        
        
    public void generartrans(String estado, ArrayList<Integer> siguiente, ArrayList<ArrayList> table){
        
        
        
        for (Integer sig: siguiente){
            
            for(ArrayList tab: table){
                
                if (sig == tab.get(0)){
                    
                    String terminal = (String)tab.get(1);
                    ArrayList <Integer> sg = (ArrayList<Integer>) tab.get(2);
                    //String calculo = Integer.toString(estado);
                    String ini = "S"+estado;
                    trans nueva_trans = new trans(ini,terminal , sg);
                    
                 
                    if (repetidos != null){  
                        for(repetido repe: repetidos){      
                            if (repe.Siguientes == sg){
                                nueva_trans.setFinal(repe.Final_state);
                                break;
          
                            }
                        }
                    }
                    
                     transiciones.add(nueva_trans);
                    
   
                }
                
                
            }
            
        }
        
        
        
    }
        
    public void crear_estados(ArrayList<ArrayList> table){
        
        int estado = 1;
        ArrayList<ArrayList> nueva_tabla = new ArrayList<ArrayList> (); 
        HashSet<Object> Elementosrep = new HashSet<Object>();
        
       
        
        for (int i = 0; i < table.size(); i++) {
            
            if(!Elementosrep.contains(table.get(i).get(2))){
                Elementosrep.add(table.get(i).get(2));
                nueva_tabla.add(table.get(i));
                
            }
               
        }
        
        
        for (int i = 0; i < nueva_tabla.size(); i++) {
            
            ArrayList<Integer> tab =(ArrayList<Integer>) nueva_tabla.get(i).get(2);   
            
            
            String state = "S"+Integer.toString(estado);
            repetido rep = new repetido(tab,state);
            repetidos.add(rep);
            estado++;
            
            
        }
        
    }
    
    
    public void PrintEstados(){
        
        
        for(repetido tmp: repetidos){
            
            System.out.println("EStado:" +tmp.Final_state+" Vienen con: "+tmp.Siguientes);
            
        }
        
        
    }
        
        
    
    public void trans(ArrayList <Integer> first ,ArrayList<ArrayList> table, int calcular_state, int index){
        
        boolean primero = true;
        int contador = calcular_state;
        
        
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
                        contador++;
                        String state = Integer.toString(contador);
                        String fin = "S"+state;
                        nueva_trans.setFinal(fin);
                        repetido rep = new repetido(sig,fin);
                        repetidos.add(rep);
                        primero = false;
                       
                        
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
        
        
        
        
        //imprimir();
        
        
        
        
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
