package edu.umg.colas;

/**
 * Implementación de una Cola (Queue) usando únicamente clases propias,
 * sin utilizar las colecciones de Java (Queue, LinkedList, etc.).
 *
 * Estructura interna: lista enlazada simple con punteros al frente y al final.
 *
 *  FRENTE                              FINAL
 *  [dato|sig] -> [dato|sig] -> [dato|sig] -> null
 *
 * Principio FIFO: First In, First Out
 *   - Se inserta (encolar) por el FINAL
 *   - Se elimina (desencolar) por el FRENTE
 *
 * @author erwin
 */
public class ColasNativas {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear cola y encolar elementos
        // =============================================
        System.out.println("=== Creando cola y encolando elementos ===");
        Cola<String> cola = new Cola<>();

        cola.encolar("Cliente 1");
        cola.encolar("Cliente 2");
        cola.encolar("Cliente 3");
        cola.encolar("Cliente 4");

        System.out.println("Cola: " + cola);
        System.out.println("Tamaño: " + cola.tamaño());

        // =============================================
        // 2. Consultar el frente (peek)
        // =============================================
        System.out.println("\n=== Consultando el frente (frente) ===");
        System.out.println("Elemento al frente: " + cola.frente());
        System.out.println("La cola NO se modifica: " + cola);

        // =============================================
        // 3. Desencolar elementos (dequeue)
        // =============================================
        System.out.println("\n=== Desencolando elementos ===");
        String atendido = cola.desencolar();
        System.out.println("Desencolado: " + atendido);
        System.out.println("Cola después: " + cola);

        atendido = cola.desencolar();
        System.out.println("Desencolado: " + atendido);
        System.out.println("Cola después: " + cola);

        // =============================================
        // 4. Verificar si está vacía
        // =============================================
        System.out.println("\n=== Verificar si está vacía ===");
        System.out.println("¿Está vacía? " + cola.estaVacia());

        // =============================================
        // 5. Encolar más elementos
        // =============================================
        System.out.println("\n=== Encolando más elementos ===");
        cola.encolar("Cliente 5");
        cola.encolar("Cliente 6");
        System.out.println("Cola: " + cola);
        System.out.println("Tamaño: " + cola.tamaño());

        // =============================================
        // 6. Buscar un elemento
        // =============================================
        System.out.println("\n=== Buscar elemento (contiene) ===");
        System.out.println("¿Contiene 'Cliente 3'? " + cola.contiene("Cliente 3"));
        System.out.println("¿Contiene 'Cliente 5'? " + cola.contiene("Cliente 5"));

        // =============================================
        // 7. Recorrer sin destruir la cola
        // =============================================
        System.out.println("\n=== Recorrer la cola sin destruirla ===");
        cola.recorrer();

        // =============================================
        // 8. Vaciar la cola completamente
        // =============================================
        System.out.println("\n=== Vaciando la cola ===");
        while (!cola.estaVacia()) {
            System.out.println("  Atendido -> " + cola.desencolar());
        }
        System.out.println("¿Está vacía? " + cola.estaVacia());
        System.out.println("Cola: " + cola);

        // =============================================
        // 9. Ejemplo práctico: simulación de fila en banco
        // =============================================
        System.out.println("\n=== Simulación: Fila en un banco ===");
        simularFilaBanco();

        // =============================================
        // 10. Cola de enteros
        // =============================================
        System.out.println("\n=== Cola de enteros ===");
        Cola<Integer> colaNumeros = new Cola<>();
        colaNumeros.encolar(10);
        colaNumeros.encolar(20);
        colaNumeros.encolar(30);
        colaNumeros.encolar(40);

        System.out.println("Cola: " + colaNumeros);
        System.out.println("Frente: " + colaNumeros.frente());
        System.out.println("Desencolar: " + colaNumeros.desencolar());
        System.out.println("Cola después: " + colaNumeros);
    }

    /**
     * Simula una fila de atención en un banco usando nuestra Cola personalizada.
     */
    public static void simularFilaBanco() {
        Cola<String> filaBanco = new Cola<>();

        // Llegan clientes
        String[] clientes = {"Ana", "Roberto", "María", "Carlos", "Lucía"};
        for (String cliente : clientes) {
            filaBanco.encolar(cliente);
            System.out.println("  Llega: " + cliente + " | Fila: " + filaBanco);
        }

        System.out.println();

        // Se atienden los primeros 3
        for (int i = 0; i < 3; i++) {
            String clienteAtendido = filaBanco.desencolar();
            System.out.println("  Ventanilla atiende a: " + clienteAtendido
                    + " | Fila restante: " + filaBanco);
        }

        // Llegan 2 más
        filaBanco.encolar("Pedro");
        filaBanco.encolar("Sofía");
        System.out.println("  Llegan más clientes. Fila: " + filaBanco);

        // Se atiende al resto
        System.out.println("  Atendiendo al resto:");
        while (!filaBanco.estaVacia()) {
            System.out.println("    Atendido -> " + filaBanco.desencolar());
        }
    }
}

// =====================================================================
//  IMPLEMENTACIÓN DE LA COLA CON CLASES PROPIAS (SIN java.util)
// =====================================================================

/**
 * Nodo de la lista enlazada.
 * Cada nodo almacena un dato y una referencia al siguiente nodo.
 *
 *   [dato | siguiente] -> [dato | siguiente] -> null
 *
 * @param <T> Tipo de dato que almacena el nodo.
 */
class Nodo<T> {

    T dato;
    Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

/**
 * Cola genérica implementada con lista enlazada simple.
 *
 * Mantiene dos referencias:
 *   - frente: apunta al primer nodo (por donde se desencola)
 *   - fin:    apunta al último nodo (por donde se encola)
 *
 * Operaciones y su complejidad:
 *   - encolar:    O(1)
 *   - desencolar: O(1)
 *   - frente:     O(1)
 *   - estaVacia:  O(1)
 *   - tamaño:     O(1)
 *   - contiene:   O(n)
 *
 * @param <T> Tipo de dato que almacena la cola.
 */
class Cola<T> {

    private Nodo<T> frente;  // Primer nodo (por donde sale)
    private Nodo<T> fin;     // Último nodo (por donde entra)
    private int tamaño;

    /**
     * Crea una cola vacía.
     */
    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    /**
     * Encolar (enqueue): agrega un elemento al FINAL de la cola.
     *
     * Antes:  [A] -> [B] -> [C] -> null
     *          ↑               ↑
     *        frente            fin
     *
     * Después de encolar("D"):
     *          [A] -> [B] -> [C] -> [D] -> null
     *           ↑                    ↑
     *         frente                fin
     */
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);

        if (estaVacia()) {
            // Si la cola está vacía, el nuevo nodo es frente y fin
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            // El último nodo apunta al nuevo, y fin se actualiza
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }

        tamaño++;
    }

    /**
     * Desencolar (dequeue): elimina y retorna el elemento del FRENTE.
     *
     * Antes:  [A] -> [B] -> [C] -> null
     *          ↑               ↑
     *        frente            fin
     *
     * Después de desencolar():  retorna "A"
     *          [B] -> [C] -> null
     *           ↑      ↑
     *         frente   fin
     *
     * @return El dato del elemento eliminado.
     * @throws RuntimeException si la cola está vacía.
     */
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("Error: la cola está vacía, no se puede desencolar.");
        }

        T dato = frente.dato;
        frente = frente.siguiente;
        tamaño--;

        // Si la cola quedó vacía, fin también debe ser null
        if (frente == null) {
            fin = null;
        }

        return dato;
    }

    /**
     * Consulta el elemento al FRENTE sin eliminarlo.
     *
     * @return El dato del elemento al frente.
     * @throws RuntimeException si la cola está vacía.
     */
    public T frente() {
        if (estaVacia()) {
            throw new RuntimeException("Error: la cola está vacía, no hay frente.");
        }
        return frente.dato;
    }

    /**
     * Verifica si la cola está vacía.
     */
    public boolean estaVacia() {
        return frente == null;
    }

    /**
     * Retorna la cantidad de elementos en la cola.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Busca si un elemento existe en la cola.
     * Recorre desde el frente hasta el fin.
     */
    public boolean contiene(T dato) {
        Nodo<T> actual = frente;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /**
     * Recorre e imprime todos los elementos sin modificar la cola.
     */
    public void recorrer() {
        if (estaVacia()) {
            System.out.println("  (cola vacía)");
            return;
        }

        Nodo<T> actual = frente;
        int posicion = 0;
        while (actual != null) {
            String marcador = "";
            if (actual == frente && actual == fin) {
                marcador = " ← frente y fin";
            } else if (actual == frente) {
                marcador = " ← frente";
            } else if (actual == fin) {
                marcador = " ← fin";
            }
            System.out.println("  [" + posicion + "] " + actual.dato + marcador);
            actual = actual.siguiente;
            posicion++;
        }
    }

    /**
     * Representación en texto de la cola.
     * Muestra los elementos desde el frente hasta el fin.
     */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[] (vacía)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        Nodo<T> actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" -> ");
            }
            actual = actual.siguiente;
        }

        sb.append("]");
        return sb.toString();
    }
}
