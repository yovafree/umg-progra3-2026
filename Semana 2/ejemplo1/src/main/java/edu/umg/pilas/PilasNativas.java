package edu.umg.pilas;

/**
 * Implementación de una Pila (Stack) usando únicamente clases propias,
 * sin utilizar las colecciones de Java (Stack, ArrayList, etc.).
 *
 * Estructura interna: lista enlazada simple con puntero al tope.
 *
 *  TOPE
 *   ↓
 *  [dato|sig] -> [dato|sig] -> [dato|sig] -> null
 *   (último      (penúltimo     (primero
 *    en entrar)   en entrar)     en entrar)
 *
 * Principio LIFO: Last In, First Out
 *   - Se inserta (push) por el TOPE
 *   - Se elimina (pop) por el TOPE
 *
 * @author erwin
 */
public class PilasNativas {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear pila y apilar elementos (push)
        // =============================================
        System.out.println("=== Creando pila y apilando elementos (push) ===");
        Pila<String> pila = new Pila<>();

        pila.push("Libro A");
        pila.push("Libro B");
        pila.push("Libro C");
        pila.push("Libro D");

        System.out.println("Pila: " + pila);
        System.out.println("Tamaño: " + pila.tamaño());

        // =============================================
        // 2. Consultar el tope (peek)
        // =============================================
        System.out.println("\n=== Consultando el tope (peek) ===");
        System.out.println("Elemento en el tope: " + pila.peek());
        System.out.println("La pila NO se modifica: " + pila);

        // =============================================
        // 3. Desapilar elementos (pop)
        // =============================================
        System.out.println("\n=== Desapilando elementos (pop) ===");
        String eliminado = pila.pop();
        System.out.println("Desapilado: " + eliminado);
        System.out.println("Pila después: " + pila);

        eliminado = pila.pop();
        System.out.println("Desapilado: " + eliminado);
        System.out.println("Pila después: " + pila);

        // =============================================
        // 4. Verificar si está vacía
        // =============================================
        System.out.println("\n=== Verificar si está vacía ===");
        System.out.println("¿Está vacía? " + pila.estaVacia());

        // =============================================
        // 5. Apilar más elementos
        // =============================================
        System.out.println("\n=== Apilando más elementos ===");
        pila.push("Libro E");
        pila.push("Libro F");
        System.out.println("Pila: " + pila);
        System.out.println("Tamaño: " + pila.tamaño());

        // =============================================
        // 6. Buscar un elemento (search)
        // =============================================
        System.out.println("\n=== Buscar elemento (search) ===");
        int pos = pila.buscar("Libro A");
        System.out.println("Posición de 'Libro A' desde el tope: " + pos);

        pos = pila.buscar("Libro Z");
        System.out.println("Posición de 'Libro Z': " + pos + " (no encontrado)");

        // =============================================
        // 7. Recorrer sin destruir la pila
        // =============================================
        System.out.println("\n=== Recorrer la pila sin destruirla ===");
        pila.recorrer();

        // =============================================
        // 8. Vaciar la pila completamente
        // =============================================
        System.out.println("\n=== Vaciando la pila ===");
        while (!pila.estaVacia()) {
            System.out.println("  Pop -> " + pila.pop());
        }
        System.out.println("¿Está vacía? " + pila.estaVacia());
        System.out.println("Pila: " + pila);

        // =============================================
        // 9. Ejemplo práctico: invertir una cadena
        // =============================================
        System.out.println("\n=== Ejemplo práctico: invertir una cadena ===");
        String original = "Programacion III";
        String invertida = invertirCadena(original);
        System.out.println("Original:  " + original);
        System.out.println("Invertida: " + invertida);

        // =============================================
        // 10. Ejemplo práctico: verificar paréntesis balanceados
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

        // =============================================
        // 11. Pila de enteros
        // =============================================
        System.out.println("\n=== Pila de enteros ===");
        Pila<Integer> pilaNumeros = new Pila<>();
        pilaNumeros.push(10);
        pilaNumeros.push(20);
        pilaNumeros.push(30);
        pilaNumeros.push(40);

        System.out.println("Pila: " + pilaNumeros);
        System.out.println("Tope: " + pilaNumeros.peek());
        System.out.println("Pop: " + pilaNumeros.pop());
        System.out.println("Pila después: " + pilaNumeros);
    }

    /**
     * Invierte una cadena usando nuestra Pila personalizada.
     */
    public static String invertirCadena(String cadena) {
        Pila<Character> pila = new Pila<>();

        for (char c : cadena.toCharArray()) {
            pila.push(c);
        }

        StringBuilder resultado = new StringBuilder();
        while (!pila.estaVacia()) {
            resultado.append(pila.pop());
        }

        return resultado.toString();
    }

    /**
     * Verifica si los paréntesis, corchetes y llaves están balanceados
     * usando nuestra Pila personalizada.
     */
    public static boolean parentesisBalanceados(String expresion) {
        Pila<Character> pila = new Pila<>();

        for (char c : expresion.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                pila.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                if (pila.estaVacia()) {
                    return false;
                }

                char apertura = pila.pop();
                if (!esPareja(apertura, c)) {
                    return false;
                }
            }
        }

        return pila.estaVacia();
    }

    private static boolean esPareja(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')')
                || (apertura == '[' && cierre == ']')
                || (apertura == '{' && cierre == '}');
    }
}

// =====================================================================
//  IMPLEMENTACIÓN DE LA PILA CON CLASES PROPIAS (SIN java.util)
// =====================================================================

/**
 * Nodo de la lista enlazada.
 * Cada nodo almacena un dato y una referencia al siguiente nodo (el de abajo).
 *
 *   [dato | siguiente] -> [dato | siguiente] -> null
 *    (tope)                (fondo)
 *
 * @param <T> Tipo de dato que almacena el nodo.
 */
class NodoPila<T> {

    T dato;
    NodoPila<T> siguiente;

    public NodoPila(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

/**
 * Pila genérica implementada con lista enlazada simple.
 *
 * Mantiene una referencia al TOPE de la pila.
 * Cada nuevo elemento se coloca encima del anterior.
 *
 *  Estado visual de la pila:
 *
 *    TOPE →  | Libro D |
 *            | Libro C |
 *            | Libro B |
 *            | Libro A |
 *            +---------+
 *
 *  En la lista enlazada se ve así:
 *
 *    tope
 *     ↓
 *    [D|sig] -> [C|sig] -> [B|sig] -> [A|sig] -> null
 *
 * Operaciones y su complejidad:
 *   - push:      O(1) — apilar
 *   - pop:       O(1) — desapilar
 *   - peek:      O(1) — consultar tope
 *   - estaVacia: O(1)
 *   - tamaño:    O(1)
 *   - buscar:    O(n) — buscar desde el tope
 *
 * @param <T> Tipo de dato que almacena la pila.
 */
class Pila<T> {

    private NodoPila<T> tope;  // Referencia al nodo en el tope
    private int tamaño;

    /**
     * Crea una pila vacía.
     */
    public Pila() {
        this.tope = null;
        this.tamaño = 0;
    }

    /**
     * Push (apilar): agrega un elemento en el TOPE de la pila.
     *
     * Antes:      tope
     *              ↓
     *             [C] -> [B] -> [A] -> null
     *
     * Después de push("D"):
     *             tope
     *              ↓
     *             [D] -> [C] -> [B] -> [A] -> null
     *
     * El nuevo nodo apunta al antiguo tope, y tope se actualiza.
     */
    public void push(T dato) {
        NodoPila<T> nuevoNodo = new NodoPila<>(dato);

        // El nuevo nodo apunta al tope actual
        nuevoNodo.siguiente = tope;

        // El tope ahora es el nuevo nodo
        tope = nuevoNodo;

        tamaño++;
    }

    /**
     * Pop (desapilar): elimina y retorna el elemento del TOPE.
     *
     * Antes:      tope
     *              ↓
     *             [D] -> [C] -> [B] -> [A] -> null
     *
     * Después de pop():  retorna "D"
     *             tope
     *              ↓
     *             [C] -> [B] -> [A] -> null
     *
     * @return El dato del elemento eliminado.
     * @throws RuntimeException si la pila está vacía.
     */
    public T pop() {
        if (estaVacia()) {
            throw new RuntimeException("Error: la pila está vacía, no se puede desapilar.");
        }

        T dato = tope.dato;
        tope = tope.siguiente;
        tamaño--;

        return dato;
    }

    /**
     * Peek: consulta el elemento en el TOPE sin eliminarlo.
     *
     * @return El dato del elemento en el tope.
     * @throws RuntimeException si la pila está vacía.
     */
    public T peek() {
        if (estaVacia()) {
            throw new RuntimeException("Error: la pila está vacía, no hay tope.");
        }
        return tope.dato;
    }

    /**
     * Verifica si la pila está vacía.
     */
    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Retorna la cantidad de elementos en la pila.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Busca un elemento en la pila.
     * Retorna la posición desde el tope (1 = tope).
     * Retorna -1 si no existe.
     */
    public int buscar(T dato) {
        NodoPila<T> actual = tope;
        int posicion = 1;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return posicion;
            }
            actual = actual.siguiente;
            posicion++;
        }

        return -1; // No encontrado
    }

    /**
     * Recorre e imprime todos los elementos sin modificar la pila.
     * Muestra desde el tope hasta el fondo.
     */
    public void recorrer() {
        if (estaVacia()) {
            System.out.println("  (pila vacía)");
            return;
        }

        NodoPila<T> actual = tope;
        int posicion = 0;
        while (actual != null) {
            String marcador = (actual == tope) ? " ← tope" : "";
            if (actual.siguiente == null) {
                marcador = (actual == tope) ? " ← tope y fondo" : " ← fondo";
            }
            System.out.println("  [" + posicion + "] " + actual.dato + marcador);
            actual = actual.siguiente;
            posicion++;
        }
    }

    /**
     * Representación en texto de la pila.
     * Muestra los elementos desde el tope hasta el fondo.
     */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[] (vacía)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[TOPE: ");

        NodoPila<T> actual = tope;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }

        sb.append(" :FONDO]");
        return sb.toString();
    }
}
