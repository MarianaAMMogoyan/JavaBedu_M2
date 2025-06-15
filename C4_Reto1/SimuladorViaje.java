package C4_Reto1;

import java.util.concurrent.CompletableFuture;

public class SimuladorViaje {
    public static void main(String[] args) {
        MovilidadApp app = new MovilidadApp();

        CompletableFuture<String> rutaFuture = app.calcularRuta();
        CompletableFuture<Double> tarifaFuture = app.estimarTarifa();

        rutaFuture
            .thenCombine(tarifaFuture, (ruta, tarifa) ->
                "✅ 🚗 Ruta calculada: " + ruta + " | Tarifa estimada: $" + tarifa)
            .exceptionally(ex -> "❌ Error en el proceso: " + ex.getMessage())
            .thenAccept(System.out::println);

        // Esperar lo suficiente para que los hilos terminen antes de que el main finalice
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
