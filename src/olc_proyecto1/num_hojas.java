/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olc_proyecto1;

/**
 *
 * @author Admin
 */

//esta clase nos funciona para ir contando el numero de hojas que obtenemos
public class num_hojas {
    
    
    public int content;
    
    public num_hojas(String content){
        this.content = clean(content)+1;  
        
    }
    
    public int clean(String content){
        return content.replace(".", "").replace("|", "").replace("*", "").replace("+", "").replace("?", content).length();
    }
    
    public int getNum(){
        content -= 1;
        return content;
    }
    
    
}
