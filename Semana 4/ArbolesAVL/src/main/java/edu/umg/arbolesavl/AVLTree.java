/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.umg.arbolesavl;

/**
 *
 * @author erwin
 */
public class AVLTree {
    AVLNode root;
    
    public int height(AVLNode n){
        if (n == null)
            return 0;
        
        return n.height;
    }
    
    public int balance(AVLNode n){
        if (n == null) return 0;
        
        return height(n.left) - height(n.right);
    }
    
    public void updateHeight(AVLNode n){
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }
    
    public AVLNode rotateRight (AVLNode y){
        AVLNode x = y.left;
        AVLNode t2 = x.right;
        
        x.right = y;
        y.left = t2;
        
        updateHeight(x);
        updateHeight(y);
        
        return x;
    }
    
    public AVLNode rotateLeft(AVLNode x){
        AVLNode y = x.right;
        AVLNode t2 = x.left;
        
        y.left = x;
        x.right = t2;
        
        updateHeight(x);
        updateHeight(y);
        
        return y;
    }
    
    private AVLNode insert(AVLNode nodo, int key){
        if (nodo == null){
            return new AVLNode(key);
        }
        
        if (key < nodo.key){
            nodo.left = insert(nodo.left, key);
        }else if(key > nodo.key){
            nodo.right = insert(nodo.left, key);
        }else
            return nodo;
        
        updateHeight(nodo);
        
        int bf = balance(nodo);
        
        if (bf > 1 && key < nodo.left.key){
            return rotateRight(nodo);
        }
        
        if (bf < -1 && key < nodo.right.key){
            return rotateLeft(nodo);
        }
        
        return nodo;
    }

    public void insert(int key) {
            root = insert(root, key);
        }
}
