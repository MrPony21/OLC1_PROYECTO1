/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analisis;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Tree {

    private NodoBin arbol;
    private int numeroNodo = 1;
    private ArrayList <Siguientes> lista_siguientes = new ArrayList<>(); 
    private String nombre = "";

    public Tree(NodoBin arbol, String nombre) {
        this.nombre = nombre;
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
        String arbgraph = graficar(this.arbol, numeroNodo);
        //printSiguientes();
        generarTransiciones();
        dotTree("SALIDAS/ARBOLES_202003220/ARB_", arbgraph);
        dotSig("SALIDAS/SIGUIENTES_202003220/SIG_");
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
        
        System.out.println(nuevo);
        
        
        
        
        
        transicion transic = new transicion();
        transic.crear_estados(nuevo);
        transic.PrintEstados();
        
        ArrayList <Integer> first = this.arbol.getPrimeros();
        System.out.println(first);
        ArrayList <trans> transiciones = transic.getTransition(first, nuevo);
        ArrayList<String> estados = transic.getEstados();
        
        
        dotTrans(transiciones,estados, "SALIDAS/TRANSICIONES_202003220/TRANS_");
        
        
        
    }
    
    
    
    
    public void dotTree(String tmpPath, String graph){
        
        String graphica = "digraph s {\n";
        graphica += graph;
        graphica += "\n}";
        
       // System.out.println(graphica);
                
         try {
            try (FileWriter fw = new FileWriter(tmpPath + this.nombre + ".dot", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                out.println(graphica);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng " + tmpPath + this.nombre + ".dot -o " + tmpPath + this.nombre + ".png");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
             
         }
    }
    
    
    public void dotSig(String tmpPath){
        
        String graph = "";
        
        graph += "a0 [shape=none label=<\n" 
                +"<TABLE border=\"1\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                + "<TR>"
                + "<TD colspan=\"3\"> TABLA DE SIGUIENTES </TD>"
                + "</TR>\n"
                + "<TR>"
                + "<TD colspan=\"2\"> HOJAS </TD>"
                + "<TD colspan=\"2\"> SIGUIENTES </TD>"
                + "</TR>";
        
        
        
        for (Siguientes sg: lista_siguientes){
            
            graph += " <TR>\n"
                    + " <TD> "+ sg.getNumeracion()+ "</TD>\n"
                    + " <TD border=\"1\" bgcolor=\"#bebebe\" > "+ sg.getLexema() + "</TD>\n"
                    + " <TD> "+ sg.getSig() + "</TD>\n"
                    + " </TR>\n";
                   
        }
        
        graph += " </TABLE>>];";
        
        
        String graphica = "digraph s {\n";
        graphica += graph;
        graphica += "\n}";
        
        //System.out.println(graphica);
                
         try {
            try (FileWriter fw = new FileWriter(tmpPath + this.nombre + ".dot", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                out.println(graphica);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng " + tmpPath + this.nombre + ".dot -o " + tmpPath + this.nombre + ".png");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
             
         }
                
               
        
        
        
    }
    
    
    public void dotTrans(ArrayList<trans> transiciones ,ArrayList<String> estados,String tmpPath){
        
        String graph = "";
        int nodos = 1;
        
        for (Siguientes sig: lista_siguientes){
            
            nodos += 1;
            
        }
        
        System.out.println(nodos);
        ArrayList<String> lex = new ArrayList<>();
        
        graph += "a0 [shape=none label=<\n" 
                +"<TABLE border=\"1\" cellspacing=\"2\" cellpadding=\"10\" >\n"
                + "<TR>"
                + "<TD colspan=\"9\"> TABLA DE TRANSICIONES </TD>"
                + "</TR>\n"
                + "<TR>"
                + "<TD > ESTADOS </TD>\n"
                + "<TD > SIGUIENTES </TD>\n";
                
        
        for (Siguientes sig: lista_siguientes){
            
            System.out.println(sig.getLexema());
            graph += "<TD>" +sig.getLexema() + "</TD>\n";
            lex.add(sig.getLexema());
 
            
        }
        
        graph += "</TR>";
        //System.out.println(transiciones.);
        
        for (String t: estados){
            
            graph += "<TR>\n";
            
            graph += "<TD>"+t+"</TD>\n";
            
            
         
                for (trans tran: transiciones){
                    
                    for (Siguientes sig: lista_siguientes){
                        
                        if (tran.terminal == sig.getLexema()){
                            System.out.println(sig.getLexema());
                            graph += "<TD> x </TD>\n";
                            lex.add(sig.getLexema());
                        }else{
                            System.out.println(sig.getLexema());
                            graph += "<TD> - </TD>\n";
                            lex.add(sig.getLexema());
                        }
                        
                       
 
            
        }
                    
                }
                
            
            
            
            graph += "</TR>\n";
            
        }
        /*
        for (trans t: transiciones){
            
            graph += "<TR>\n";
            
            
            
            
            graph += "<TD>"+t.Inicial +"</TD>\n"
                    + "<TD>"+t.Siguientes+"</TD>\n";
           
            for (Integer  list:t.Siguientes){
                
                
                
                graph += "<TD>"+list+"</TD>\n";
                
            }
            
            graph += "</TR>\n";
                    
            
 
            
        }
        
        */
        
        graph += " </TABLE>>];";
        
        //System.out.println(graph);
        
        String graphica = "digraph s {\n";
        graphica += graph;
        graphica += "\n}";
        
        System.out.println(graphica);
                
         try {
            try (FileWriter fw = new FileWriter(tmpPath + this.nombre + ".dot", true); BufferedWriter bw = new BufferedWriter(fw); PrintWriter out = new PrintWriter(bw)) {
                out.println(graphica);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tpng " + tmpPath + this.nombre + ".dot -o " + tmpPath + this.nombre + ".png");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
             
        }
                
               
        
        
        
        
    }
         
         
 
    
      

}