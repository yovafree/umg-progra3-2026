package edu.umg.colas;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Ejemplo del uso de Colas (Queue) en Java.
 *
 * Una cola es una estructura de datos FIFO (First In, First Out):
 * el primer elemento en entrar es el primero en salir.
 *
 * @author erwin
 */
public class Colas {

    public static void main(String[] args) {

        // =============================================
        // 1. Crear una cola y agregar elementos (offer / add)
        // =============================================
        Queue<String> cola = new LinkedList<>();

        System.out.println("=== Agregando elementos a la cola (offer) ===");
        cola.offer("Cliente 1");
        cola.offer("Cliente 2");
        cola.offer("Cliente 3");
        cola.offer("Cliente 4");

        System.out.println("Cola después de offer: " + cola);
        // Salida: [Cliente 1, Cliente 2, Cliente 3, Cliente 4]
        //  (el frente es "Cliente 1")

        // =============================================
        // 2. Consultar el frente de la cola (peek / element)
        //    peek() retorna null si la cola está vacía
        //    element() lanza NoSuchElementException si está vacía
        // =============================================
        System.out.println("\n=== Consultando el frente de la cola (peek) ===");
        String frente = cola.peek();
        System.out.println("Elemento al frente: " + frente);
        System.out.println("La cola NO se modifica: " + cola);

        // =============================================
        // 3. Eliminar elementos del frente (poll / remove)
        //    poll() retorna null si la cola está vacía
        //    remove() lanza NoSuchElementException si está vacía
        // =============================================
        System.out.println("\n=== Atendiendo clientes (poll) ===");
        String atendido = cola.poll();
        System.out.println("Cliente atendido: " + atendido);
        System.out.println("Cola después de poll: " + cola);

        // =============================================
        // 4. Tamaño y verificar si está vacía
        // =============================================
        System.out.println("\n=== Tamaño de la cola ===");
        System.out.println("Tamaño: " + cola.size());
        System.out.println("¿Está vacía? " + cola.isEmpty());

        // =============================================
        // 5. Verificar si contiene un elemento
        // =============================================
        System.out.println("\n=== Buscar un elemento (contains) ===");
        System.out.println("¿Contiene 'Cliente 3'? " + cola.contains("Cliente 3"));
        System.out.println("¿Contiene 'Cliente 1'? " + cola.contains("Cliente 1"));

        // =============================================
        // 6. Recorrer y vaciar la cola
        // =============================================
        System.out.println("\n=== Vaciando la cola (atendiendo a todos) ===");
        while (!cola.isEmpty()) {
            System.out.println("  Atendido -> " + cola.poll());
        }
        System.out.println("¿Está vacía ahora? " + cola.isEmpty());

        // =============================================
        // 7. Ejemplo práctico: simulación de fila en un banco
        // =============================================
        System.out.println("\n=== Simulación: Fila en un banco ===");
        simularFilaBanco();

        // =============================================
        // 8. Ejemplo práctico: cola de prioridad
        //    Los elementos se ordenan automáticamente
        // =============================================
        System.out.println("\n=== Cola de prioridad (PriorityQueue) ===");
        ejemploColaPrioridad();

        // =============================================
        // 9. Ejemplo práctico: sistema de tickets de soporte
        // =============================================
        System.out.println("\n=== Sistema de tickets de soporte con prioridad ===");
        sistemaTickets();
    }

    /**
     * Simula una fila de atención en un banco.
     * Los clientes llegan y se atienden en orden de llegada (FIFO).
     */
    public static void simularFilaBanco() {
        Queue<String> filaBanco = new LinkedList<>();

        // Llegan clientes
        String[] clientes = {"Ana", "Roberto", "María", "Carlos", "Lucía"};
        for (String cliente : clientes) {
            filaBanco.offer(cliente);
            System.out.println("  Llega: " + cliente + " | Fila: " + filaBanco);
        }

        System.out.println();

        // Se atienden los primeros 3
        for (int i = 0; i < 3; i++) {
            String clienteAtendido = filaBanco.poll();
            System.out.println("  Ventanilla atiende a: " + clienteAtendido
                    + " | Fila restante: " + filaBanco);
        }

        // Llegan 2 clientes más
        filaBanco.offer("Pedro");
        filaBanco.offer("Sofía");
        System.out.println("  Llegan más clientes. Fila actual: " + filaBanco);

        // Se atiende al resto
        System.out.println("  Atendiendo al resto:");
        while (!filaBanco.isEmpty()) {
            System.out.println("    Atendido -> " + filaBanco.poll());
        }
    }

    /**
     * Demuestra el uso de PriorityQueue.
     * Los elementos se extraen en orden natural (menor a mayor para números).
     */
    public static void ejemploColaPrioridad() {
        PriorityQueue<Integer> colaPrioridad = new PriorityQueue<>();

        // Insertar en orden desordenado
        colaPrioridad.offer(42);
        colaPrioridad.offer(10);
        colaPrioridad.offer(85);
        colaPrioridad.offer(3);
        colaPrioridad.offer(57);

        System.out.println("Cola de prioridad: " + colaPrioridad);
        System.out.println("Extrayendo en orden de prioridad (menor primero):");

        while (!colaPrioridad.isEmpty()) {
            System.out.println("  Poll -> " + colaPrioridad.poll());
        }
    }

    /**
     * Simula un sistema de tickets de soporte técnico con prioridad.
     * Los tickets con mayor urgencia (menor número) se atienden primero.
     */
    public static void sistemaTickets() {
        // Cola de prioridad usando Comparable (por nivel de urgencia)
        PriorityQueue<Ticket> colaTickets = new PriorityQueue<>();

        colaTickets.offer(new Ticket(3, "Pregunta sobre facturación"));
        colaTickets.offer(new Ticket(1, "¡Servidor caído!"));
        colaTickets.offer(new Ticket(2, "Error en login de usuario"));
        colaTickets.offer(new Ticket(3, "Solicitud de nueva funcionalidad"));
        colaTickets.offer(new Ticket(1, "Base de datos no responde"));

        System.out.println("Atendiendo tickets por prioridad (1=urgente, 3=baja):");
        while (!colaTickets.isEmpty()) {
            Ticket ticket = colaTickets.poll();
            System.out.println("  [Prioridad " + ticket.prioridad + "] " + ticket.descripcion);
        }
    }
}

/**
 * Clase Ticket que implementa Comparable para ordenar por prioridad.
 */
class Ticket implements Comparable<Ticket> {

    int prioridad;        // 1 = urgente, 2 = media, 3 = baja
    String descripcion;

    public Ticket(int prioridad, String descripcion) {
        this.prioridad = prioridad;
        this.descripcion = descripcion;
    }

    @Override
    public int compareTo(Ticket otro) {
        return Integer.compare(this.prioridad, otro.prioridad);
    }

    @Override
    public String toString() {
        return "[P" + prioridad + "] " + descripcion;
    }
}
