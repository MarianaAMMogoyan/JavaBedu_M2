package C4_Reto2;

import java.util.concurrent.CompletableFuture;

public class SimulacionAterrizaje {
    public static void main(String[] args) {
        AeropuertoControl control = new AeropuertoControl();

        System.out.println("🛫 Verificando condiciones para aterrizaje...\n");

        CompletableFuture<Boolean> pista = control.verificarPista();
        CompletableFuture<Boolean> clima = control.verificarClima();
        CompletableFuture<Boolean> trafico = control.verificarTraficoAereo();
        CompletableFuture<Boolean> personal = control.verificarPersonalTierra();

        CompletableFuture<Void> resultadoFinal = CompletableFuture
            .allOf(pista, clima, trafico, personal)
            .thenRun(() -> {
                try {
                    boolean condicionesOk = pista.get() && clima.get() && trafico.get() && personal.get();
                    if (condicionesOk) {
                        System.out.println("\n🛬 Aterrizaje autorizado: todas las condiciones óptimas.");
                    } else {
                        System.out.println("\n🚫 Aterrizaje denegado: condiciones no óptimas.");
                    }
                } catch (Exception e) {
                    System.out.println("\n❌ Error en las verificaciones: " + e.getMessage());
                }
            })
            .exceptionally(ex -> {
                System.out.println("\n❌ Falló el proceso: " + ex.getMessage());
                return null;
            });

        // Esperar para ver la salida completa
        resultadoFinal.join();
    }
}
