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
public class table {
    
    public void appendfolow(int numNodo, String lexema, ArrayList follist, ArrayList<ArrayList> table){
        
        for (ArrayList item: table){
            if ((int) item.get(0) == numNodo && item.get(1)== lexema){
                for (Object folitem: follist){
                    if (! ((ArrayList)item.get(2)).contains((int)folitem)){
                        ((ArrayList)item.get(2)).add(folitem);
                    }
                }
                return;
            }
        }
        ArrayList data = new ArrayList();
        data.add(numNodo);
        data.add(lexema);
        data.add(follist);
        
        table.add(data);
        
        
    }
    
    
    public ArrayList next(int numNodo, ArrayList<ArrayList> table){
        ArrayList result = new ArrayList();
        for (ArrayList item: table){
            if ((int) item.get(0) == numNodo){
                result.add(item.get(1));
                result.add(((ArrayList)item.get(2)).clone());
                return result;
            }
        }
        result.add("");
        result.add(new ArrayList());
    return result;
    }
   
    
    public void PrintTable(ArrayList<ArrayList> table){
        for(ArrayList item: table){
            System.out.println(item.get(0)+" - "+item.get(1)+ " - "+item.get(2));
        }
    }  
    
    
    public void OrdenamientoBurbuja(ArrayList<ArrayList> table){
        for (int i = 0; i < table.size() - 1; i++) {
            for (int j = 0; j < table.size() - 1 - i; j++) {
                int num1 = Integer.parseInt(table.get(j).get(0).toString());
                int num2 = Integer.parseInt(table.get(j+1).get(0).toString());
                if (num1 > num2) {
                    ArrayList<Integer> temp = table.get(j);
                    table.set(j, table.get(j+1));
                    table.set(j+1, temp);
                }
            }
        }
        
        
    }
    
    
     
   
         
    
}



