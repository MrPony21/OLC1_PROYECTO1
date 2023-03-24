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
public class Tree {

    private NodoBin arbol;
    private int numeroNodo = 1;
    private ArrayList <Siguientes> lista_siguientes = new ArrayList<>(); 

    public Tree(NodoBin arbol) {
        NodoBin raiz = new NodoBin(".");
        NodoBin estado_aceptacion = new NodoBin("#");
        estado_aceptacion.setLeave(true);
        raiz.setLeft(arbol);
        raiz.setRight(estado_aceptacion);
        this.arbol = raiz;
        asignarNumeros(this.arbol);
        numeroNodo =0;
        calculos(this.arbol);
        //System.out.println(graficar(this.arbol,numeroNodo));
        printSiguientes();
        generarTransiciones();
    }
    

    public String graficar(NodoBin nodo, int padre){
        
        String graph = "";
        numeroNodo +=1;
        
        int actual = numeroNodo;
        if(nodo == null){
            numeroNodo -=1;
            return graph;
        }
        
        if (nodo.isLeave()){
            graph += "N_"+ actual + "[shape = none label=<\n"
                    + " <TABLE border=\"0\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">"+ nodo.EsAnulable() +"</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD> "+ nodo.getPrimeros() + "</TD>\n"
                    + " <TD border=\"1\" bgcolor=\"#bebebe\" > "+ nodo.getData() + "</TD>\n"
                    + " <TD> "+ nodo.getUltimos() + "</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">" + nodo.getNum() + "</TD>\n"
                    + " </TR>\n"
                    + " </TABLE>>];";
  
        } else{
            graph += "N_"+ actual + "[shape = none label=<\n"
                    + " <TABLE border=\"0\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                    + " <TR>\n"
                    + " <TD colspan=\"3\">"+ nodo.EsAnulable()+"</TD>\n"
                    + " </TR>\n"
                    + " <TR>\n"
                    + " <TD> "+ nodo.getPrimeros() + "</TD>\n"
                    + " <TD border=\"1\" bgcolor=\"#F9902D\"> "+ nodo.getData() + "</TD>\n"
                    + " <TD> "+ nodo.getUltimos() + "</TD>\n"
                    + " </TR>\n"
                    + " </TABLE>>];";

        }
        
        if (padre != 0){
            graph += "N_"+ padre + "-> N_" + actual + ";\n";
        }
        
        graph += graficar(nodo.getLeft(), actual);
        
        graph += graficar(nodo.getRight(), actual);
        
        
        return graph;
    }
    
    
    public void asignarNumeros(NodoBin actual){
        
        if (actual == null){
            return;
        }
        
        if (actual.isLeave()){
            actual.setNum(numeroNodo);
            numeroNodo++;
            return;
        }
        
        asignarNumeros(actual.getLeft());
        asignarNumeros(actual.getRight());
        
        
    }
    
    public void calculos(NodoBin actual){
        
        if (actual == null){
            return;
        }
        
        if(actual.isLeave()){
            actual.getPrimeros().add(actual.getNum());
            actual.getUltimos().add(actual.getNum());
            
            int numero = actual.getNum();
            String lexema = (String)actual.getData();
            Siguientes nuevo_sig = new Siguientes(numero,lexema);
            lista_siguientes.add(nuevo_sig);
            
            return;
        }
        
        calculos(actual.getLeft());
        calculos(actual.getRight());
        
        
        if(actual.getData().equals("*")){
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
            actual.getUltimos().addAll(actual.getLeft().getUltimos());
            
            ArrayList <Integer> UltimosC1 = actual.getLeft().getUltimos();
            ArrayList <Integer> PrimerosC1 = actual.getLeft().getPrimeros();
            
            for (Integer ult: UltimosC1){
                for(Siguientes sig: lista_siguientes){
                    if (ult == sig.getNumeracion()){
                        for(Integer prim: PrimerosC1){
                            if(!(sig.getSig().contains(prim))){                               
                                sig.getSig().add(prim);    
                            }

                        }

                    }
                    
                }
                
                
            }
            
            
        }else if (actual.getData().equals("+")){
            boolean nd = actual.getLeft().isAnulable();
            actual.setAnulable(nd);
            actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
            actual.getUltimos().addAll(actual.getLeft().getUltimos());
            
            
            ArrayList <Integer> UltimosC1 = actual.getLeft().getUltimos();
            ArrayList <Integer> PrimerosC1 = actual.getLeft().getPrimeros();
            
            for (Integer ult: UltimosC1){               
                for(Siguientes sig: lista_siguientes){
                    if (ult == sig.getNumeracion()){ 
                        for(Integer prim: PrimerosC1){
                            if(!(sig.getSig().contains(prim))){  
                                sig.getSig().add(prim);          
                            }

                        }

                    }
                    
                }
                
                
            }
            
            
        }else if (actual.getData().equals("?")){
            actual.setAnulable(true);
            actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
            actual.getUltimos().addAll(actual.getLeft().getUltimos());
        }else if (actual.getData().equals("|")){
            boolean nd = actual.getLeft().isAnulable() || actual.getRight().isAnulable();
            actual.setAnulable(nd);
            actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
            actual.getPrimeros().addAll(actual.getRight().getPrimeros());
            actual.getUltimos().addAll(actual.getLeft().getUltimos());
            actual.getUltimos().addAll(actual.getRight().getUltimos());
        }else if (actual.getData().equals(".")){
            boolean nd = actual.getLeft().isAnulable() && actual.getRight().isAnulable();
            actual.setAnulable(nd);
            if (actual.getLeft().isAnulable()){
                actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
                actual.getPrimeros().addAll(actual.getRight().getPrimeros());
            }else{
                actual.getPrimeros().addAll(actual.getLeft().getPrimeros());
            }
            
            if (actual.getLeft().isAnulable()){
                actual.getUltimos().addAll(actual.getLeft().getUltimos());
                actual.getUltimos().addAll(actual.getRight().getUltimos());
            }else{
                actual.getUltimos().addAll(actual.getRight().getUltimos());
            }
            
            
            ArrayList <Integer> UltimosC1 = actual.getLeft().getUltimos();
            ArrayList <Integer> PrimerosC2 = actual.getRight().getPrimeros();
            
            for (Integer ult: UltimosC1){
                for(Siguientes sig: lista_siguientes){
                    if (ult == sig.getNumeracion()){ 
                        for(Integer prim: PrimerosC2){                      
                            if(!(sig.getSig().contains(prim))){
                                sig.getSig().add(prim);      
                            }
                            
                            
 
                        }

                    }
                    
                }
                
                
            }
            
        }
        
        
        
        
    }
    
    
    public void printSiguientes(){
        
        
        for (Siguientes sg: lista_siguientes){
            
            
            System.out.println(sg.getNumeracion()+","+sg.getLexema()+","+sg.getSig());
            
        }
        
        
        
    }
    
    
    public void generarTransiciones(){
        
        
        ArrayList <ArrayList> nuevo = new ArrayList<>();
        
        for (int i = 0; i < lista_siguientes.size(); i++) {
            
            ArrayList <Object> nuevo2 = new ArrayList();
            nuevo2.add(lista_siguientes.get(i).getNumeracion());
            nuevo2.add(lista_siguientes.get(i).getLexema());
            nuevo2.add(lista_siguientes.get(i).getSig());
            
            nuevo.add(nuevo2);
            
        }
        
        for (int i = 0; i < nuevo.size(); i++) {
            
            System.out.println(nuevo.get(i));
            
        }
        
        for(ArrayList item: nuevo){
            System.out.println(item.get(0)+" - "+item.get(1)+ " - "+item.get(2));
        }
        
        
        tabla_transiciones transic = new tabla_transiciones();
        tansic.crear_estados(nuevo);
        
        
        
        
        
        
        
    }
    
      

}