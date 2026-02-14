package edu.umg.listas;

/**
 * Implementación de una Lista Enlazada usando únicamente clases propias,
 * sin utilizar las colecciones de Java (ArrayList, LinkedList, etc.).
 *
 * Estructura interna: lista doblemente enlazada con punteros a cabeza y cola.
 *
 *  null <- [ant|dato|sig] <-> [ant|dato|sig] <-> [ant|dato|sig] -> null
 *            ↑                                       ↑
 *          cabeza                                   cola
 *
 * Ventajas de la lista doblemente enlazada:
 *   - Inserción al inicio y al final en O(1)
 *   - Eliminación por referencia en O(1)
 *   - Recorrido en ambas direcciones
 *
 * @author erwin
 */
public class ListasNativas {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear lista y agregar elementos al final
        // =============================================
        System.out.println("=== Agregando elementos al final (agregar) ===");
        Lista<String> lista = new Lista<>();

        lista.agregar("Manzana");
        lista.agregar("Banana");
        lista.agregar("Cereza");
        lista.agregar("Durazno");

        System.out.println("Lista: " + lista);
        System.out.println("Tamaño: " + lista.tamaño());

        // =============================================
        // 2. Agregar al inicio
        // =============================================
        System.out.println("\n=== Agregando al inicio (agregarAlInicio) ===");
        lista.agregarAlInicio("Fresa");
        System.out.println("Lista: " + lista);

        // =============================================
        // 3. Insertar en posición específica
        // =============================================
        System.out.println("\n=== Insertar en posición (insertarEn) ===");
        lista.insertarEn(2, "Mango");
        System.out.println("Después de insertarEn(2, \"Mango\"): " + lista);

        // =============================================
        // 4. Acceder por índice (get)
        // =============================================
        System.out.println("\n=== Acceso por índice (obtener) ===");
        System.out.println("Elemento en índice 0: " + lista.obtener(0));
        System.out.println("Elemento en índice 3: " + lista.obtener(3));
        System.out.println("Último elemento: " + lista.obtener(lista.tamaño() - 1));

        // =============================================
        // 5. Modificar un elemento (set)
        // =============================================
        System.out.println("\n=== Modificar elemento (establecer) ===");
        String anterior = lista.establecer(1, "Papaya");
        System.out.println("Se reemplazó \"" + anterior + "\" por \"Papaya\"");
        System.out.println("Lista: " + lista);

        // =============================================
        // 6. Eliminar por índice
        // =============================================
        System.out.println("\n=== Eliminar por índice (eliminarEn) ===");
        String eliminado = lista.eliminarEn(0);
        System.out.println("Eliminado índice 0: " + eliminado);
        System.out.println("Lista: " + lista);

        // =============================================
        // 7. Eliminar por valor
        // =============================================
        System.out.println("\n=== Eliminar por valor (eliminar) ===");
        boolean fueEliminado = lista.eliminar("Cereza");
        System.out.println("Eliminado \"Cereza\": " + fueEliminado);
        System.out.println("Lista: " + lista);

        // =============================================
        // 8. Buscar elementos
        // =============================================
        System.out.println("\n=== Buscar elementos ===");
        System.out.println("¿Contiene \"Mango\"? " + lista.contiene("Mango"));
        System.out.println("Índice de \"Durazno\": " + lista.indiceDe("Durazno"));
        System.out.println("Índice de \"Kiwi\" (no existe): " + lista.indiceDe("Kiwi"));

        // =============================================
        // 9. Tamaño y verificar vacía
        // =============================================
        System.out.println("\n=== Tamaño y vacía ===");
        System.out.println("Tamaño: " + lista.tamaño());
        System.out.println("¿Está vacía? " + lista.estaVacia());

        // =============================================
        // 10. Recorrer la lista (hacia adelante y atrás)
        // =============================================
        System.out.println("\n=== Recorrer hacia adelante ===");
        lista.recorrerAdelante();

        System.out.println("\n=== Recorrer hacia atrás ===");
        lista.recorrerAtras();

        // =============================================
        // 11. Vaciar la lista
        // =============================================
        System.out.println("\n=== Vaciando la lista ===");
        lista.limpiar();
        System.out.println("Lista: " + lista);
        System.out.println("¿Está vacía? " + lista.estaVacia());

        // =============================================
        // 12. Ejemplo práctico: lista de estudiantes
        // =============================================
        System.out.println("\n=== Ejemplo práctico: lista de estudiantes ===");
        ejemploEstudiantes();

        // =============================================
        // 13. Lista de enteros
        // =============================================
        System.out.println("\n=== Lista de enteros ===");
        Lista<Integer> numeros = new Lista<>();
        numeros.agregar(50);
        numeros.agregar(20);
        numeros.agregar(80);
        numeros.agregar(10);
        numeros.agregar(40);

        System.out.println("Lista: " + numeros);
        System.out.println("Elemento en [2]: " + numeros.obtener(2));
        numeros.eliminarEn(1);
        System.out.println("Después de eliminar [1]: " + numeros);
    }

    /**
     * Ejemplo práctico con una lista de objetos EstudianteLista.
     */
    public static void ejemploEstudiantes() {
        Lista<EstudianteLista> estudiantes = new Lista<>();

        estudiantes.agregar(new EstudianteLista("Ana", 85));
        estudiantes.agregar(new EstudianteLista("Carlos", 92));
        estudiantes.agregar(new EstudianteLista("María", 78));
        estudiantes.agregar(new EstudianteLista("Roberto", 95));
        estudiantes.agregar(new EstudianteLista("Lucía", 88));

        // Mostrar todos
        System.out.println("Estudiantes registrados:");
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            EstudianteLista e = estudiantes.obtener(i);
            System.out.println("  " + (i + 1) + ". " + e.nombre + " - Nota: " + e.nota);
        }

        // Buscar mejor y peor nota
        EstudianteLista mejor = estudiantes.obtener(0);
        EstudianteLista peor = estudiantes.obtener(0);
        double sumaNotas = 0;

        for (int i = 0; i < estudiantes.tamaño(); i++) {
            EstudianteLista e = estudiantes.obtener(i);
            sumaNotas += e.nota;
            if (e.nota > mejor.nota) {
                mejor = e;
            }
            if (e.nota < peor.nota) {
                peor = e;
            }
        }

        double promedio = sumaNotas / estudiantes.tamaño();
        System.out.printf("\nPromedio general: %.2f%n", promedio);
        System.out.println("Mejor estudiante: " + mejor.nombre + " (" + mejor.nota + ")");
        System.out.println("Menor nota: " + peor.nombre + " (" + peor.nota + ")");

        // Filtrar aprobados (nota >= 80)
        System.out.println("\nEstudiantes aprobados (nota >= 80):");
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            EstudianteLista e = estudiantes.obtener(i);
            if (e.nota >= 80) {
                System.out.println("  ✓ " + e.nombre + " (" + e.nota + ")");
            }
        }

        // Eliminar al estudiante con menor nota
        System.out.println("\nEliminando al estudiante con menor nota (" + peor.nombre + ")...");
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            if (estudiantes.obtener(i).nombre.equals(peor.nombre)) {
                estudiantes.eliminarEn(i);
                break;
            }
        }

        System.out.println("Lista actualizada:");
        for (int i = 0; i < estudiantes.tamaño(); i++) {
            EstudianteLista e = estudiantes.obtener(i);
            System.out.println("  " + (i + 1) + ". " + e.nombre + " - Nota: " + e.nota);
        }
    }
}

// =====================================================================
//  IMPLEMENTACIÓN DE LA LISTA CON CLASES PROPIAS (SIN java.util)
// =====================================================================

/**
 * Nodo de la lista doblemente enlazada.
 * Cada nodo tiene referencia al anterior y al siguiente.
 *
 *   null <- [ant|dato|sig] <-> [ant|dato|sig] <-> [ant|dato|sig] -> null
 *
 * @param <T> Tipo de dato que almacena el nodo.
 */
class NodoLista<T> {

    T dato;
    NodoLista<T> anterior;
    NodoLista<T> siguiente;

    public NodoLista(T dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}

/**
 * Lista genérica implementada con lista doblemente enlazada.
 *
 * Mantiene dos referencias:
 *   - cabeza: primer nodo de la lista.
 *   - cola:   último nodo de la lista.
 *
 * Operaciones y su complejidad:
 *   - agregar (al final):      O(1)
 *   - agregarAlInicio:         O(1)
 *   - insertarEn (posición):   O(n)
 *   - obtener (por índice):    O(n)
 *   - establecer (por índice): O(n)
 *   - eliminarEn (por índice): O(n)
 *   - eliminar (por valor):    O(n)
 *   - contiene / indiceDe:     O(n)
 *   - estaVacia / tamaño:      O(1)
 *
 * @param <T> Tipo de dato que almacena la lista.
 */
class Lista<T> {

    private NodoLista<T> cabeza;  // Primer nodo
    private NodoLista<T> cola;    // Último nodo
    private int tamaño;

    /**
     * Crea una lista vacía.
     */
    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }

    /**
     * Agrega un elemento al FINAL de la lista.
     *
     * Antes:  [A] <-> [B] <-> [C] -> null
     *          ↑               ↑
     *        cabeza           cola
     *
     * Después de agregar("D"):
     *         [A] <-> [B] <-> [C] <-> [D] -> null
     *          ↑                       ↑
     *        cabeza                   cola
     */
    public void agregar(T dato) {
        NodoLista<T> nuevoNodo = new NodoLista<>(dato);

        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.anterior = cola;
            cola.siguiente = nuevoNodo;
            cola = nuevoNodo;
        }

        tamaño++;
    }

    /**
     * Agrega un elemento al INICIO de la lista.
     *
     * Antes:  [A] <-> [B] <-> [C] -> null
     *          ↑               ↑
     *        cabeza           cola
     *
     * Después de agregarAlInicio("Z"):
     *         [Z] <-> [A] <-> [B] <-> [C] -> null
     *          ↑                       ↑
     *        cabeza                   cola
     */
    public void agregarAlInicio(T dato) {
        NodoLista<T> nuevoNodo = new NodoLista<>(dato);

        if (estaVacia()) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            cabeza = nuevoNodo;
        }

        tamaño++;
    }

    /**
     * Inserta un elemento en una posición específica.
     *
     * @param indice Posición donde insertar (0 a tamaño).
     * @param dato   Elemento a insertar.
     * @throws IndexOutOfBoundsException si el índice es inválido.
     */
    public void insertarEn(int indice, T dato) {
        validarIndiceParaInsercion(indice);

        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }
        if (indice == tamaño) {
            agregar(dato);
            return;
        }

        // Insertar en el medio
        NodoLista<T> nodoActual = obtenerNodo(indice);
        NodoLista<T> nuevoNodo = new NodoLista<>(dato);

        // Enlazar el nuevo nodo entre el anterior y el actual
        nuevoNodo.anterior = nodoActual.anterior;
        nuevoNodo.siguiente = nodoActual;
        nodoActual.anterior.siguiente = nuevoNodo;
        nodoActual.anterior = nuevoNodo;

        tamaño++;
    }

    /**
     * Obtiene el elemento en una posición específica.
     *
     * @param indice Posición del elemento (0 a tamaño-1).
     * @return El dato en esa posición.
     * @throws IndexOutOfBoundsException si el índice es inválido.
     */
    public T obtener(int indice) {
        validarIndice(indice);
        return obtenerNodo(indice).dato;
    }

    /**
     * Reemplaza el elemento en una posición específica.
     *
     * @param indice Posición del elemento a reemplazar.
     * @param dato   Nuevo dato.
     * @return El dato anterior.
     * @throws IndexOutOfBoundsException si el índice es inválido.
     */
    public T establecer(int indice, T dato) {
        validarIndice(indice);
        NodoLista<T> nodo = obtenerNodo(indice);
        T datoAnterior = nodo.dato;
        nodo.dato = dato;
        return datoAnterior;
    }

    /**
     * Elimina el elemento en una posición específica.
     *
     * Casos:
     *   - Eliminar la cabeza: cabeza avanza al siguiente.
     *   - Eliminar la cola: cola retrocede al anterior.
     *   - Eliminar en el medio: se enlazan anterior y siguiente.
     *
     * @param indice Posición del elemento a eliminar.
     * @return El dato eliminado.
     * @throws IndexOutOfBoundsException si el índice es inválido.
     */
    public T eliminarEn(int indice) {
        validarIndice(indice);
        NodoLista<T> nodo = obtenerNodo(indice);
        return desvincularNodo(nodo);
    }

    /**
     * Elimina la primera ocurrencia de un elemento por valor.
     *
     * @param dato Elemento a buscar y eliminar.
     * @return true si se encontró y eliminó, false si no existía.
     */
    public boolean eliminar(T dato) {
        NodoLista<T> actual = cabeza;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                desvincularNodo(actual);
                return true;
            }
            actual = actual.siguiente;
        }

        return false;
    }

    /**
     * Verifica si la lista contiene un elemento.
     */
    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    /**
     * Retorna el índice de la primera ocurrencia de un elemento.
     * Retorna -1 si no existe.
     */
    public int indiceDe(T dato) {
        NodoLista<T> actual = cabeza;
        int indice = 0;

        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return indice;
            }
            actual = actual.siguiente;
            indice++;
        }

        return -1;
    }

    /**
     * Verifica si la lista está vacía.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }

    /**
     * Retorna la cantidad de elementos en la lista.
     */
    public int tamaño() {
        return tamaño;
    }

    /**
     * Elimina todos los elementos de la lista.
     */
    public void limpiar() {
        cabeza = null;
        cola = null;
        tamaño = 0;
    }

    /**
     * Recorre e imprime la lista de cabeza a cola.
     */
    public void recorrerAdelante() {
        if (estaVacia()) {
            System.out.println("  (lista vacía)");
            return;
        }

        NodoLista<T> actual = cabeza;
        int posicion = 0;
        while (actual != null) {
            String marcador = "";
            if (actual == cabeza && actual == cola) {
                marcador = " ← cabeza y cola";
            } else if (actual == cabeza) {
                marcador = " ← cabeza";
            } else if (actual == cola) {
                marcador = " ← cola";
            }
            System.out.println("  [" + posicion + "] " + actual.dato + marcador);
            actual = actual.siguiente;
            posicion++;
        }
    }

    /**
     * Recorre e imprime la lista de cola a cabeza.
     */
    public void recorrerAtras() {
        if (estaVacia()) {
            System.out.println("  (lista vacía)");
            return;
        }

        NodoLista<T> actual = cola;
        int posicion = tamaño - 1;
        while (actual != null) {
            String marcador = "";
            if (actual == cabeza && actual == cola) {
                marcador = " ← cabeza y cola";
            } else if (actual == cola) {
                marcador = " ← cola";
            } else if (actual == cabeza) {
                marcador = " ← cabeza";
            }
            System.out.println("  [" + posicion + "] " + actual.dato + marcador);
            actual = actual.anterior;
            posicion--;
        }
    }

    // =================================================================
    //  MÉTODOS PRIVADOS AUXILIARES
    // =================================================================

    /**
     * Obtiene el NODO (no el dato) en una posición.
     * Optimización: si el índice está en la primera mitad, recorre desde cabeza;
     * si está en la segunda mitad, recorre desde cola.
     */
    private NodoLista<T> obtenerNodo(int indice) {
        NodoLista<T> actual;

        if (indice < tamaño / 2) {
            // Recorrer desde la cabeza (más cercano al inicio)
            actual = cabeza;
            for (int i = 0; i < indice; i++) {
                actual = actual.siguiente;
            }
        } else {
            // Recorrer desde la cola (más cercano al final)
            actual = cola;
            for (int i = tamaño - 1; i > indice; i--) {
                actual = actual.anterior;
            }
        }

        return actual;
    }

    /**
     * Desvincula un nodo de la lista y retorna su dato.
     * Maneja los 3 casos: cabeza, cola, o nodo intermedio.
     */
    private T desvincularNodo(NodoLista<T> nodo) {
        T dato = nodo.dato;

        NodoLista<T> nodoAnterior = nodo.anterior;
        NodoLista<T> nodoSiguiente = nodo.siguiente;

        // Actualizar enlace del nodo anterior
        if (nodoAnterior == null) {
            // Es la cabeza
            cabeza = nodoSiguiente;
        } else {
            nodoAnterior.siguiente = nodoSiguiente;
            nodo.anterior = null;
        }

        // Actualizar enlace del nodo siguiente
        if (nodoSiguiente == null) {
            // Es la cola
            cola = nodoAnterior;
        } else {
            nodoSiguiente.anterior = nodoAnterior;
            nodo.siguiente = null;
        }

        tamaño--;
        return dato;
    }

    /**
     * Valida que el índice esté en rango [0, tamaño-1].
     */
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fuera de rango. Tamaño: " + tamaño);
        }
    }

    /**
     * Valida que el índice esté en rango [0, tamaño] (para inserción).
     */
    private void validarIndiceParaInsercion(int indice) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fuera de rango para inserción. Tamaño: " + tamaño);
        }
    }

    /**
     * Representación en texto de la lista.
     */
    @Override
    public String toString() {
        if (estaVacia()) {
            return "[] (vacía)";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        NodoLista<T> actual = cabeza;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(" <-> ");
            }
            actual = actual.siguiente;
        }

        sb.append("]");
        return sb.toString();
    }
}

/**
 * Clase EstudianteLista para el ejemplo práctico.
 */
class EstudianteLista {

    String nombre;
    double nota;

    public EstudianteLista(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nombre + " (Nota: " + nota + ")";
    }
}
