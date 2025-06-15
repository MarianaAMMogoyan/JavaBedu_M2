package C2_Reto2;

import java.util.concurrent.locks.ReentrantLock;

public class RecursoMedico {
    private String nombre;
    private final ReentrantLock lock = new ReentrantLock();

    public RecursoMedico(String nombre) {
        this.nombre = nombre;
    }

    public void usar(String profesional) {
        System.out.println("🏥 " + profesional + " está intentando ingresar a " + nombre + "...");
        lock.lock(); // Pide acceso exclusivo
        try {
            System.out.println("👩‍⚕️ " + profesional + " ha ingresado a " + nombre);
            Thread.sleep(2000); // Simula el tiempo de uso
            System.out.println("✅ " + profesional + " ha salido de " + nombre);
        } catch (InterruptedException e) {
            System.out.println("❌ Error durante el uso del recurso por " + profesional);
        } finally {
            lock.unlock(); // Libera el recurso
        }
    }
}

