/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.umg.arbolesavl;

/**
 *
 * @author erwin
 */
public class AVLNode {
    public int key;
    public int height;
    
    public AVLNode left, right;
    
    public AVLNode (int key){
        this.key = key;
        this.height = 1;
    }
}
