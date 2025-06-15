package C4_Reto2;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AeropuertoControl {

    private Random random = new Random();

    public CompletableFuture<Boolean> verificarPista() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🛣️ Verificando pista...");
            esperar();
            boolean resultado = random.nextDouble() < 0.8; // 80% de probabilidad de éxito
            System.out.println("🛣️ Pista disponible: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarClima() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🌦️ Verificando clima...");
            esperar();
            boolean resultado = random.nextDouble() < 0.85; // 85% de éxito
            System.out.println("🌦️ Clima favorable: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarTraficoAereo() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("🚦 Verificando tráfico aéreo...");
            esperar();
            boolean resultado = random.nextDouble() < 0.9; // 90%
            System.out.println("🚦 Tráfico aéreo despejado: " + resultado);
            return resultado;
        });
    }

    public CompletableFuture<Boolean> verificarPersonalTierra() {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("👷‍♂️ Verificando personal de tierra...");
            esperar();
            boolean resultado = random.nextDouble() < 0.95; // 95%
            System.out.println("👷‍♂️ Personal disponible: " + resultado);
            return resultado;
        });
    }

    private void esperar() {
        try {
            TimeUnit.SECONDS.sleep(2 + random.nextInt(2)); // espera de 2 a 3 seg
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

