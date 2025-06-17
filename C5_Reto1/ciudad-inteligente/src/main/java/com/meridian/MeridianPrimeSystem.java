package com.meridian;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MeridianPrimeSystem {

    private static final Random random = new Random();
    private static final AtomicInteger eventosCriticos = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        // 🚗 Sensores de tráfico
        Flux.interval(Duration.ofMillis(500))
                .map(i -> random.nextInt(101)) // 0 a 100
                .filter(congestion -> congestion > 70)
                .doOnNext(c -> {
                    eventosCriticos.incrementAndGet();
                    System.out.println("🚗 Alerta: Congestión del " + c + "%");
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

        // 🌫️ Contaminación del aire
        Flux.interval(Duration.ofMillis(600))
                .map(i -> random.nextInt(101)) // PM2.5 entre 0 y 100
                .filter(pm -> pm > 50)
                .doOnNext(pm -> {
                    eventosCriticos.incrementAndGet();
                    System.out.println("🌫️ Alerta: Contaminación alta (PM2.5: " + pm + " ug/m3)");
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

        // 🚑 Accidentes viales
        Flux.interval(Duration.ofMillis(800))
                .map(i -> {
                    String[] prioridades = {"Baja", "Media", "Alta"};
                    return prioridades[random.nextInt(3)];
                })
                .filter(p -> p.equals("Alta"))
                .doOnNext(p -> {
                    eventosCriticos.incrementAndGet();
                    System.out.println("🚑 Emergencia vial: Accidente con prioridad Alta");
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

        // 🚝 Trenes maglev con backpressure
        Flux.interval(Duration.ofMillis(700))
                .onBackpressureBuffer() // Simula carga alta
                .map(i -> random.nextInt(11)) // 0 a 10 min
                .filter(min -> min > 5)
                .doOnNext(min -> {
                    eventosCriticos.incrementAndGet();
                    System.out.println("🚝 Tren maglev con retraso crítico: " + min + " minutos");
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

        // 🚦 Semáforos inteligentes
        AtomicInteger contadorRojos = new AtomicInteger(0);

        Flux.interval(Duration.ofMillis(400))
                .map(i -> {
                    String[] estados = {"Verde", "Amarillo", "Rojo"};
                    return estados[random.nextInt(3)];
                })
                .doOnNext(estado -> {
                    if (estado.equals("Rojo")) {
                        if (contadorRojos.incrementAndGet() >= 3) {
                            eventosCriticos.incrementAndGet();
                            System.out.println("🚦 Semáforo en Rojo detectado 3 veces seguidas");
                            contadorRojos.set(0);
                        }
                    } else {
                        contadorRojos.set(0);
                    }
                })
                .subscribeOn(Schedulers.parallel())
                .subscribe();

        // 🚨 Verificar si ocurren 3 o más eventos críticos cada segundo
        Flux.interval(Duration.ofSeconds(1))
                .doOnNext(i -> {
                    if (eventosCriticos.getAndSet(0) >= 3) {
                        System.out.println("🚨 Alerta global: Múltiples eventos críticos detectados en Meridian Prime");
                    }
                })
                .subscribe();

        // 💤 Programa corriendo (simulación en tiempo real)
        Thread.sleep(20000); // Simula 20 segundos, se puede aumentar el tiempo
    }
}

