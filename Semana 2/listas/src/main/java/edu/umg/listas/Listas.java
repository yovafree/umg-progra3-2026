package edu.umg.listas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Ejemplo del uso de Listas (List) en Java.
 *
 * Una lista es una colección ordenada que permite elementos duplicados.
 * Se accede a los elementos por índice (posición).
 *
 * Implementaciones principales:
 *   - ArrayList:  acceso rápido por índice, inserción/eliminación lenta en el medio.
 *   - LinkedList: inserción/eliminación rápida, acceso por índice más lento.
 *
 * @author erwin
 */
public class Listas {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear una lista y agregar elementos (add)
        // =============================================
        List<String> frutas = new ArrayList<>();

        System.out.println("=== Agregando elementos (add) ===");
        frutas.add("Manzana");
        frutas.add("Banana");
        frutas.add("Cereza");
        frutas.add("Durazno");
        frutas.add("Banana");  // permite duplicados

        System.out.println("Lista: " + frutas);

        // =============================================
        // 2. Insertar en una posición específica
        // =============================================
        System.out.println("\n=== Insertar en posición específica ===");
        frutas.add(2, "Fresa");  // inserta en índice 2
        System.out.println("Después de add(2, \"Fresa\"): " + frutas);

        // =============================================
        // 3. Acceder a elementos por índice (get)
        // =============================================
        System.out.println("\n=== Acceso por índice (get) ===");
        System.out.println("Elemento en índice 0: " + frutas.get(0));
        System.out.println("Elemento en índice 3: " + frutas.get(3));

        // =============================================
        // 4. Modificar un elemento (set)
        // =============================================
        System.out.println("\n=== Modificar elemento (set) ===");
        String anterior = frutas.set(1, "Mango");
        System.out.println("Se reemplazó \"" + anterior + "\" por \"Mango\"");
        System.out.println("Lista: " + frutas);

        // =============================================
        // 5. Eliminar elementos (remove)
        // =============================================
        System.out.println("\n=== Eliminar elementos (remove) ===");
        // Por índice
        String eliminado = frutas.remove(0);
        System.out.println("Eliminado por índice 0: " + eliminado);

        // Por objeto (elimina la primera ocurrencia)
        boolean fueEliminado = frutas.remove("Banana");
        System.out.println("Eliminado \"Banana\": " + fueEliminado);
        System.out.println("Lista: " + frutas);

        // =============================================
        // 6. Buscar elementos
        // =============================================
        System.out.println("\n=== Buscar elementos ===");
        System.out.println("¿Contiene \"Fresa\"? " + frutas.contains("Fresa"));
        System.out.println("Índice de \"Cereza\": " + frutas.indexOf("Cereza"));
        System.out.println("Índice de \"Papaya\" (no existe): " + frutas.indexOf("Papaya"));

        // =============================================
        // 7. Tamaño y verificar vacía
        // =============================================
        System.out.println("\n=== Tamaño de la lista ===");
        System.out.println("Tamaño: " + frutas.size());
        System.out.println("¿Está vacía? " + frutas.isEmpty());

        // =============================================
        // 8. Formas de recorrer una lista
        // =============================================
        System.out.println("\n=== Formas de recorrer una lista ===");

        // 8.1 For clásico
        System.out.println("Con for clásico:");
        for (int i = 0; i < frutas.size(); i++) {
            System.out.println("  [" + i + "] " + frutas.get(i));
        }

        // 8.2 For-each (enhanced for)
        System.out.println("Con for-each:");
        for (String fruta : frutas) {
            System.out.println("  - " + fruta);
        }

        // 8.3 Iterator
        System.out.println("Con Iterator:");
        Iterator<String> iterador = frutas.iterator();
        while (iterador.hasNext()) {
            System.out.println("  > " + iterador.next());
        }

        // 8.4 forEach con lambda
        System.out.println("Con forEach y lambda:");
        frutas.forEach(fruta -> System.out.println("  * " + fruta));

        // =============================================
        // 9. Ordenar la lista (sort)
        // =============================================
        System.out.println("\n=== Ordenar la lista ===");
        System.out.println("Antes de ordenar: " + frutas);
        Collections.sort(frutas);
        System.out.println("Orden natural (A-Z): " + frutas);

        Collections.sort(frutas, Collections.reverseOrder());
        System.out.println("Orden inverso (Z-A): " + frutas);

        // =============================================
        // 10. Sublista (subList)
        // =============================================
        System.out.println("\n=== Sublista (subList) ===");
        List<String> subLista = frutas.subList(1, 3); // índice 1 al 2
        System.out.println("Sublista (1,3): " + subLista);

        // =============================================
        // 11. Convertir entre array y lista
        // =============================================
        System.out.println("\n=== Conversión array <-> lista ===");
        // Lista a array
        String[] arreglo = frutas.toArray(new String[0]);
        System.out.print("Array: ");
        for (String s : arreglo) {
            System.out.print(s + " ");
        }
        System.out.println();

        // Array a lista
        List<String> desdArray = List.of("Uva", "Kiwi", "Limón");
        System.out.println("Lista desde array: " + desdArray);

        // =============================================
        // 12. Ejemplo práctico: lista de estudiantes
        // =============================================
        System.out.println("\n=== Ejemplo práctico: lista de estudiantes ===");
        ejemploEstudiantes();

        // =============================================
        // 13. ArrayList vs LinkedList
        // =============================================
        System.out.println("\n=== ArrayList vs LinkedList: rendimiento ===");
        compararRendimiento();
    }

    /**
     * Ejemplo práctico con una lista de objetos Estudiante.
     * Demuestra ordenamiento personalizado, filtrado y búsqueda.
     */
    public static void ejemploEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();

        estudiantes.add(new Estudiante("Ana", 85));
        estudiantes.add(new Estudiante("Carlos", 92));
        estudiantes.add(new Estudiante("María", 78));
        estudiantes.add(new Estudiante("Roberto", 95));
        estudiantes.add(new Estudiante("Lucía", 88));

        // Ordenar por nota (mayor a menor)
        estudiantes.sort((e1, e2) -> Double.compare(e2.nota, e1.nota));
        System.out.println("Ranking por nota (mayor a menor):");
        for (int i = 0; i < estudiantes.size(); i++) {
            Estudiante e = estudiantes.get(i);
            System.out.println("  " + (i + 1) + ". " + e.nombre + " - Nota: " + e.nota);
        }

        // Filtrar aprobados (nota >= 80)
        System.out.println("\nEstudiantes aprobados (nota >= 80):");
        estudiantes.stream()
                .filter(e -> e.nota >= 80)
                .forEach(e -> System.out.println("  ✓ " + e.nombre + " (" + e.nota + ")"));

        // Promedio de notas
        double promedio = estudiantes.stream()
                .mapToDouble(e -> e.nota)
                .average()
                .orElse(0);
        System.out.printf("\nPromedio general: %.2f%n", promedio);

        // Buscar mejor estudiante
        estudiantes.stream()
                .max((e1, e2) -> Double.compare(e1.nota, e2.nota))
                .ifPresent(e -> System.out.println("Mejor estudiante: " + e.nombre + " (" + e.nota + ")"));
    }

    /**
     * Compara el rendimiento de ArrayList vs LinkedList
     * para operaciones de inserción al inicio y acceso por índice.
     */
    public static void compararRendimiento() {
        int cantidad = 100_000;

        // --- Inserción al inicio ---
        List<Integer> arrayList = new ArrayList<>();
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < cantidad; i++) {
            arrayList.add(0, i); // insertar al inicio
        }
        long tiempoArrayList = System.currentTimeMillis() - inicio;

        List<Integer> linkedList = new LinkedList<>();
        inicio = System.currentTimeMillis();
        for (int i = 0; i < cantidad; i++) {
            linkedList.add(0, i); // insertar al inicio
        }
        long tiempoLinkedList = System.currentTimeMillis() - inicio;

        System.out.println("Inserción al inicio (" + cantidad + " elementos):");
        System.out.println("  ArrayList:  " + tiempoArrayList + " ms");
        System.out.println("  LinkedList: " + tiempoLinkedList + " ms");

        // --- Acceso por índice ---
        inicio = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            arrayList.get(i);
        }
        tiempoArrayList = System.currentTimeMillis() - inicio;

        inicio = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            linkedList.get(i);
        }
        tiempoLinkedList = System.currentTimeMillis() - inicio;

        System.out.println("\nAcceso por índice (10,000 lecturas):");
        System.out.println("  ArrayList:  " + tiempoArrayList + " ms");
        System.out.println("  LinkedList: " + tiempoLinkedList + " ms");

        System.out.println("\nConclusión:");
        System.out.println("  → ArrayList es mejor para acceso aleatorio por índice.");
        System.out.println("  → LinkedList es mejor para inserciones/eliminaciones frecuentes al inicio o medio.");
    }
}

/**
 * Clase Estudiante para el ejemplo práctico.
 */
class Estudiante {

    String nombre;
    double nota;

    public Estudiante(String nombre, double nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return nombre + " (Nota: " + nota + ")";
    }
}
