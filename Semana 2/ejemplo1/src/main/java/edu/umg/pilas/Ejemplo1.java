package edu.umg.pilas;

import java.util.Stack;

/**
 * Ejemplo del uso de Pilas (Stack) en Java.
 *
 * Una pila es una estructura de datos LIFO (Last In, First Out):
 * el último elemento en entrar es el primero en salir.
 *lup
 * @author erwin
 */
public class Ejemplo1 {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear una pila y agregar elementos (push)
        // =============================================
        Stack<String> pila = new Stack<>();

        System.out.println("=== Agregando elementos a la pila (push) ===");
        pila.push("Libro A");
        pila.push("Libro B");
        pila.push("Libro C");
        pila.push("Libro D");

        System.out.println("Pila después de push: " + pila);
        // Salida: [Libro A, Libro B, Libro C, Libro D]
        //  (el tope es "Libro D")

        // =============================================
        // 2. Consultar el elemento en el tope (peek)
        // =============================================
        System.out.println("\n=== Consultando el tope de la pila (peek) ===");
        String tope = pila.peek();
        System.out.println("Elemento en el tope: " + tope);
        System.out.println("La pila NO se modifica: " + pila);

        // =============================================
        // 3. Eliminar elementos del tope (pop)
        // =============================================
        System.out.println("\n=== Eliminando elementos de la pila (pop) ===");
        String eliminado = pila.pop();
        System.out.println("Elemento eliminado: " + eliminado);
        System.out.println("Pila después de pop: " + pila);

        // =============================================
        // 4. Buscar un elemento en la pila (search)
        //    Retorna la posición desde el tope (1 = tope)
        //    Retorna -1 si no existe
        // =============================================
        System.out.println("\n=== Buscando elementos en la pila (search) ===");
        int posicion = pila.search("Libro A");
        System.out.println("Posición de 'Libro A' desde el tope: " + posicion);

        int noExiste = pila.search("Libro Z");
        System.out.println("Posición de 'Libro Z': " + noExiste + " (no encontrado)");

        // =============================================
        // 5. Verificar si la pila está vacía (empty)
        // =============================================
        System.out.println("\n=== Verificando si la pila está vacía ===");
        System.out.println("¿Está vacía? " + pila.empty());

        // =============================================
        // 6. Tamaño de la pila (size)
        // =============================================
        System.out.println("\n=== Tamaño de la pila ===");
        System.out.println("Tamaño: " + pila.size());

        // =============================================
        // 7. Recorrer y vaciar la pila
        // =============================================
        System.out.println("\n=== Vaciando la pila elemento por elemento ===");
        while (!pila.empty()) {
            System.out.println("  Pop -> " + pila.pop());
        }
        System.out.println("¿Está vacía ahora? " + pila.empty());

        // =============================================
        // 8. Ejemplo práctico: invertir una cadena
        // =============================================
        System.out.println("\n=== Ejemplo práctico: invertir una cadena con pila ===");
        String original = "Programacion III";
        String invertida = invertirCadena(original);
        System.out.println("Original:  " + original);
        System.out.println("Invertida: " + invertida);

        // =============================================
        // 9. Ejemplo práctico: verificar paréntesis balanceados
        // =============================================
        System.out.println("\n=== Ejemplo práctico: verificar paréntesis balanceados ===");
        String[] expresiones = {
            "(a + b) * (c - d)",
            "((a + b)",
            "{[a + b] * (c - d)}",
            "{[a + b) * (c - d)}"
        };

        for (String expr : expresiones) {
            boolean balanceada = parentesisBalanceados(expr);
            System.out.println("  \"" + expr + "\" -> "
                    + (balanceada ? "Balanceada" : "NO balanceada"));
        }
    }

    /**
     * Invierte una cadena usando una pila.
     * Cada carácter se apila y luego se desapila en orden inverso.
     */
    public static String invertirCadena(String cadena) {
        Stack<Character> pila = new Stack<>();

        // Apilar cada carácter
        for (char c : cadena.toCharArray()) {
            pila.push(c);
        }

        // Desapilar para construir la cadena invertida
        StringBuilder resultado = new StringBuilder();
        while (!pila.empty()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

    /**
     * Verifica si los paréntesis, corchetes y llaves están balanceados.
     * Usa una pila para emparejar cada símbolo de cierre con su apertura.
     */
    public static boolean parentesisBalanceados(String expresion) {
        Stack<Character> pila = new Stack<>();

        for (char c : expresion.toCharArray()) {
            // Si es un símbolo de apertura, apilarlo
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            }
            // Si es un símbolo de cierre, verificar el tope
            else if (c == ')' || c == ']' || c == '}') {
                if (pila.empty()) {
                    return false; // No hay apertura correspondiente
                }

                char apertura = pila.pop();
                if (!esPareja(apertura, c)) {
                    return false; // No coincide la pareja
                }
            }
        }

        // La pila debe quedar vacía si todo está balanceado
        return pila.empty();
    }

    /**
     * Verifica si un carácter de apertura corresponde a uno de cierre.
     */
    private static boolean esPareja(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')')
                || (apertura == '[' && cierre == ']')
                || (apertura == '{' && cierre == '}');
    }
}
