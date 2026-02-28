/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.umg.arbolesavl;

/**
 *
 * @author erwin
 */
public class ArbolesAVL2 {
    
    public static void main(String[] args){
        int[] numeros = new int[]{10,20,30};
        
        AVLTree arbolAVL = new AVLTree();
        for (int x = 0; x < numeros.length;x++){
            arbolAVL.insert(x);
        }
    }
}
