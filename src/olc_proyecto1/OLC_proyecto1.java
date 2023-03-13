/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package olc_proyecto1;

import analisis.parser;
import analisis.scanner;

import java.io.StringReader;
/**
 *
 * @author Admin
 */
public class OLC_proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //frame fram = new frame();
        //fram.setVisible(true);
        leer("""
             {
             
             <! 
             \t############Este es el archiv facil para el proyecto 1##############
             \tNOTA: no se permite modificar
             !>
             
             //AGREGANDO CONJUNTOS
             CONJ: abecedarioMinus -> a~z; // declarando conjunto de letras desde a hasta z en minusculas
             CONJ: digito -> 0~5; // creamos el conjunto de digitos solo para 0, 1, 2, 3, 4 y 5
             
             //AGREGANDO EXPRESIONES REGULARES
             identificador -> . {abecedarioMinus} * | "_" | {abecedarioMinus} {digito}; //INFIJO: abecedarioMinus ("_"|abecedarioMinus|digito)*
             decimales -> . +{digito} . "." + {digito}; //INFIJO: digito+ "." digito+
             
             %%
             %%
             //ESTAS SON LAS PRUEBAS PARA LAS EXPRESIONES REGULARES
             identificador :  "soy_un_id_1"; //correcto
             identificador :  "ComPi1"; //incorrecto
             decimales : "301.59"; //incorrecto
             decimales: "1505.55";//correcto
             
             }
             
             <! YA s4lIO cOMPi 1
             \tsale con 100
             \tel proyecto 1 #4&$2=?
             \t#####RESULTADO SATISFACTORIO PARA ESTE COMENTARIO######
             !>""");
        
        
    }
    
    
    
    public static void leer(String entrada){
        try{
            scanner scanner = new scanner(new java.io.StringReader(entrada));
            parser sint = new parser(scanner);
            sint.parse();
            System.out.println("Se ha analizado correctamente");
        } catch (Exception e){
            e.printStackTrace();
        }
        
    }
    
    

}
