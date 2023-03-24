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
public class NodoBin {
    
    private String data;
    private NodoBin left;
    private NodoBin right;
    private boolean leave = false;
    private int num;
    private boolean anulable;
    private ArrayList<Integer> primeros = new ArrayList<>();
    private ArrayList<Integer> ultimos = new ArrayList<>();
    
   
    public NodoBin(String data){
        this.data = data;
        
    }
    
    public NodoBin getLeft(){
        return left; 
    }
    
    public NodoBin getRight(){
        return right;
    }
    
    public void setLeft(NodoBin left){
        this.left = left;
    }
    
    public void setRight(NodoBin right){
        this.right = right;
    }
    
    public boolean isLeave() {
        return leave;
    }

    public void setLeave(boolean leave) {
        this.leave = leave;
    }
    
    public String getData(){
        return data;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isAnulable() {
        return anulable;
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    public ArrayList<Integer> getPrimeros() {
        return primeros;
    }

    public void setPrimeros(ArrayList<Integer> primeros) {
        this.primeros = primeros;
    }

    public ArrayList<Integer> getUltimos() {
        return ultimos;
    }

    public void setUltimos(ArrayList<Integer> ultimos) {
        this.ultimos = ultimos;
    }
    
    public String EsAnulable(){
        if (this.anulable == true){
            return "Anulable";
        }
        
        return "No Anulable";
        
    }
    
    
    
    
}