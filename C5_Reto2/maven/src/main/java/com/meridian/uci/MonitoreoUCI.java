package com.meridian.uci;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;

public class MonitoreoUCI {
    public static void main(String[] args) {
        Random random = new Random();

        Flux.interval(Duration.ofMillis(300))
            .onBackpressureBuffer() // 👈 Evita error por exceso de ticks no consumidos
            .map(tick -> {
                int pacienteId = random.nextInt(3) + 1;
                int fc = 40 + random.nextInt(100);  // FC
                int spo2 = 85 + random.nextInt(20); // SpO2
                int paSistolica = 80 + random.nextInt(80);
                int paDiastolica = 50 + random.nextInt(50);
                return new EventoPaciente(pacienteId, fc, spo2, paSistolica, paDiastolica);
            })
            .filter(EventoPaciente::isCritico)
            .delayElements(Duration.ofSeconds(1)) // Simula procesamiento lento
            .subscribe(
                evento -> System.out.println(evento.getMensajeAlerta()),
                error -> System.out.println("❌ Error: " + error.getMessage())
            );

        // Mantener la app corriendo mientras observas el flujo
        try {
            Thread.sleep(15000); // Aumentado a 15s para ver más eventos críticos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class EventoPaciente {
        int id, fc, spo2, pas, pad;

        EventoPaciente(int id, int fc, int spo2, int pas, int pad) {
            this.id = id; this.fc = fc; this.spo2 = spo2; this.pas = pas; this.pad = pad;
        }

        boolean isCritico() {
            return fc < 50 || fc > 120 || spo2 < 90 || pas < 90 || pas > 140 || pad < 60 || pad > 90;
        }

        String getMensajeAlerta() {
            StringBuilder sb = new StringBuilder("⚠️ Paciente ").append(id).append(" - ");
            if (fc < 50 || fc > 120) sb.append("FC crítica: ").append(fc).append(" bpm ");
            if (spo2 < 90) sb.append("SpO2 baja: ").append(spo2).append("% ");
            if (pas < 90 || pas > 140 || pad < 60 || pad > 90)
                sb.append("PA crítica: ").append(pas).append("/").append(pad).append(" mmHg ");
            return sb.toString();
        }
    }
}
