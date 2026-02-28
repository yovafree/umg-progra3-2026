/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.umg.arbolesavl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author erwin
 */
public class ArbolesAVL {

    // =========================
    // AVL Node
    // =========================
    static class AVLNode {
        int key;
        int height;
        AVLNode left, right;

        AVLNode(int key) {
            this.key = key;
            this.height = 1; // por convención: null = 0, hoja = 1
        }
    }

    // =========================
    // AVL Tree
    // =========================
    static class AVLTree {
        AVLNode root;

        // ---- Helpers ----
        private int height(AVLNode n) {
            return (n == null) ? 0 : n.height;
        }

        private int balance(AVLNode n) {
            return (n == null) ? 0 : height(n.left) - height(n.right);
        }

        private void updateHeight(AVLNode n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }

        // ---- Rotations ----
        private AVLNode rotateRight(AVLNode y) {
            AVLNode x = y.left;
            AVLNode T2 = x.right;

            // rotación
            x.right = y;
            y.left = T2;

            // actualizar alturas
            updateHeight(y);
            updateHeight(x);

            return x;
        }

        private AVLNode rotateLeft(AVLNode x) {
            AVLNode y = x.right;
            AVLNode T2 = y.left;

            // rotación
            y.left = x;
            x.right = T2;

            // actualizar alturas
            updateHeight(x);
            updateHeight(y);

            return y;
        }

        // ---- Public insert ----
        public void insert(int key) {
            root = insert(root, key);
        }

        // ---- Core insert (LL, RR, LR, RL) ----
        private AVLNode insert(AVLNode node, int key) {
            // 1) inserción BST normal
            if (node == null) return new AVLNode(key);

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node; // sin duplicados
            }

            // 2) actualizar altura
            updateHeight(node);

            // 3) obtener balance
            int bf = balance(node);

            // 4) casos de desbalance

            // LL
            if (bf > 1 && key < node.left.key) {
                return rotateRight(node);
            }

            // RR
            if (bf < -1 && key > node.right.key) {
                return rotateLeft(node);
            }

            // LR
            if (bf > 1 && key > node.left.key) {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }

            // RL
            if (bf < -1 && key < node.right.key) {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }

            return node;
        }

        // =========================
        // Lecturas del árbol
        // =========================
        public List<Integer> inOrder() {
            List<Integer> out = new ArrayList<>();
            inOrder(root, out);
            return out;
        }

        private void inOrder(AVLNode n, List<Integer> out) {
            if (n == null) return;
            inOrder(n.left, out);
            out.add(n.key);
            inOrder(n.right, out);
        }

        public List<Integer> preOrder() {
            List<Integer> out = new ArrayList<>();
            preOrder(root, out);
            return out;
        }

        private void preOrder(AVLNode n, List<Integer> out) {
            if (n == null) return;
            out.add(n.key);
            preOrder(n.left, out);
            preOrder(n.right, out);
        }

        // =========================
        // Validación AVL
        // =========================
        public boolean isValidAVL() {
            return isValidAVL(root).isAVL;
        }

        private static class Check {
            boolean isAVL;
            int height;
            int min;
            int max;

            Check(boolean isAVL, int height, int min, int max) {
                this.isAVL = isAVL;
                this.height = height;
                this.min = min;
                this.max = max;
            }
        }

        private Check isValidAVL(AVLNode n) {
            if (n == null) {
                return new Check(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
            }

            Check L = isValidAVL(n.left);
            Check R = isValidAVL(n.right);

            boolean bstOk = (n.left == null || L.max < n.key) && (n.right == null || n.key < R.min);
            int bf = (L.height - R.height);
            boolean balanceOk = Math.abs(bf) <= 1;

            // también validamos que la altura almacenada coincida con la calculada
            int computedHeight = 1 + Math.max(L.height, R.height);
            boolean heightOk = n.height == computedHeight;

            boolean ok = L.isAVL && R.isAVL && bstOk && balanceOk && heightOk;

            int min = (n.left == null) ? n.key : L.min;
            int max = (n.right == null) ? n.key : R.max;

            return new Check(ok, computedHeight, min, max);
        }

        // =========================
        // Pretty print (opcional)
        // =========================
        public void printTreeSideways() {
            printTreeSideways(root, 0);
        }

        private void printTreeSideways(AVLNode n, int depth) {
            if (n == null) return;
            printTreeSideways(n.right, depth + 1);
            System.out.printf("%s%d(h=%d,bf=%d)%n", "   ".repeat(depth), n.key, n.height, balance(n));
            printTreeSideways(n.left, depth + 1);
        }
    }

    // =========================
    // Demo / pruebas solicitadas
    // =========================
    public static void main(String[] args) {
        runCase("LL (30,20,10)", new int[]{30, 20, 10});
        runCase("RR (10,20,30)", new int[]{10, 20, 30});
        runCase("LR (30,10,20)", new int[]{30, 10, 20});
        runCase("RL (10,30,20)", new int[]{10, 30, 20});
    }

    private static void runCase(String title, int[] values) {
        AVLTree t = new AVLTree();
        for (int v : values) t.insert(v);

        System.out.println("==================================================");
        System.out.println("Caso: " + title);
        System.out.println("In-Order : " + t.inOrder());
        System.out.println("Pre-Order: " + t.preOrder());
        System.out.println("AVL valido: " + t.isValidAVL());
        System.out.println("Arbol (sideways):");
        t.printTreeSideways();
        System.out.println();
    }
}
