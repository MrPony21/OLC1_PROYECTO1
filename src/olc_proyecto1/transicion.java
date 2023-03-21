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
   
              
        ArrayList <ArrayList> tran = new ArrayList();
        ArrayList <String> terminales = new ArrayList();
        
        
        public void Introducir_Terminales(ArrayList <String> terminalesA){
            
            for(String ter: terminalesA){
                
                if (terminales == null){
                     terminales.add(ter);
                }else{
                    
                    if (!terminales.contains(ter)){
                        terminales.add(ter);
                    }

                }
                
                
                
            }
            
        }
        
        
        public void printTerminales(){
            
            for(String ter: terminales){
                
                System.out.println(ter);
                
            }
            
        }
       
        
        public void getTransition(ArrayList <Integer> first ,ArrayList<ArrayList> table){
        
       
        //todo esto unicamente para crear la tabla del estado 0
        ArrayList <Integer> transiciones_estado0 = new ArrayList();
       
        ArrayList estado = new ArrayList();
        
        String s0 = "";
        //en este for asignaremos el primer estado
        for(int sig : first){
            
            for (ArrayList item: table){
                if (sig == ((Integer) item.get(0)).intValue()){
                   // System.out.println("Se ha encontrado "+ sig);
                    s0 += sig+",";  
                    
                    transiciones_estado0.add(sig);
                }
                
            }
            
        }
        
        estado.add("s0 ["+s0+"]");
        System.out.println(estado);
        
        for(ArrayList item2: table){
            estado.add("-");
        }

        for(ArrayList item: table){      
            for (String item2: terminales){
                String str = item.get(1).toString();
                System.out.println(str);
                String stritem2 = item2.toString();
                System.out.println(str+"se compara con :"+stritem2);
                System.out.println(str==stritem2);
                if (str == item2){                    
                    System.out.println("se encontroesto"+ item.get(1));
                    estado.set(item2.indexOf(str),item.get(1));                                                
                }else{                  
                  //  System.out.println("No se encontro esto: "+item.get(0));
                  
                }       
            }
        }
        
        tran.add(estado);
        
        //AQUI CREAREMOS TODO LO DEMAS
        /*
        int numestado = 1;
        for(ArrayList item: table){
            
            agregarestado(item,numestado ,item.get(2),estado, table);
            numestado++;
            
        }
        */
        
        
        imprimir();
        
        
        
        
    }
        
        
    public void agregarestado(ArrayList<ArrayList> item, int  numestate ,Object siguientes , ArrayList estado, ArrayList <ArrayList>table){
        
        int numestado = numestate;
        ArrayList nuevo = new ArrayList();
        ArrayList <Integer> transiciones = new ArrayList();
        System.out.println(tran.size());
            
        
        
        System.out.println("se asigno el estado S1");
        nuevo.add("S"+numestado+" ["+siguientes+"]");
        System.out.println("S"+numestado+" ["+siguientes+"]");
        /*
        for (int i = 0; i < tran.size(); i++) {

            for (int j = 0; j < estado.size(); j++) {
                if (item.get(1) == tran.get(i).get(j) ){
                    

                    System.out.println("se asigno el estado S1");
                    nuevo.add("S"+numestado+" ["+siguientes+"]");
                    System.out.println("S"+numestado+" ["+siguientes+"]");
                    
                    
                    

                }
            }


        }*/
        
        //AQUI ESTA EL ERROR
        List<Object> numeros = (List<Object>) siguientes;
        int[] arrayNumeros = numeros.stream()
                .mapToInt(o -> Integer.parseInt(o.toString()))
                .toArray();


        for (int k = 0; k < arrayNumeros.length; k++) {
            transiciones.add(arrayNumeros[k]);
        }
        
   
        for(ArrayList item2: table){
            nuevo.add("-");
        }
            
        for(ArrayList i: table){      
            for (Integer item2: transiciones){
                if (i.get(0)== item2){                    
                  //  System.out.println("se encontroesto"+ i.get(0));
                    nuevo.set(item2, i.get(1));                                                
                }else{                  
                //    System.out.println("No se encontro esto: "+i.get(0));
                }       
            }
        }
        
        
        tran.add(nuevo);

                
        
    }
    
    
    
    
        
    public void imprimir(){
        
        for (int i = 0; i < tran.size(); i++) {
            
            System.out.println(tran.get(i));
            
        }
        
     
        
        
    }
    
    
    
        
    
    
    
    
        
        
        
    
    
}
