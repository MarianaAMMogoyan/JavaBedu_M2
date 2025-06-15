package C2_Reto1;

import java.util.concurrent.*;

public class SimuladorMisionEspacial {
    public static void main(String[] args) throws Exception {
        System.out.println("🚀 Simulación de misión espacial iniciada...");

        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Enviar tareas
        Future<String> nav = executor.submit(new SistemaNavegacion());
        Future<String> com = executor.submit(new SistemaComunicaciones());
        Future<String> soporte = executor.submit(new SistemaSoporteVital());
        Future<String> termico = executor.submit(new SistemaControlTermico());

        // Obtener resultados (el orden de impresión puede variar)
        System.out.println(com.get());
        System.out.println(soporte.get());
        System.out.println(termico.get());
        System.out.println(nav.get());

        // Cerrar el executor
        executor.shutdown();

        System.out.println("✅ Todos los sistemas reportan estado operativo.");
    }
}

