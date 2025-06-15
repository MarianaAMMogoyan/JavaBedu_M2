package C2_Reto2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulacionHospital {
    public static void main(String[] args) {
        RecursoMedico salaCirugia = new RecursoMedico("Sala de cirugía");

        // Crear un pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Crear y ejecutar tareas
        executor.execute(new ProfesionalMedico("Dra. Sánchez", salaCirugia));
        executor.execute(new ProfesionalMedico("Dr. Gómez", salaCirugia));
        executor.execute(new ProfesionalMedico("Enf. Ramírez", salaCirugia));
        executor.execute(new ProfesionalMedico("Dra. Ortega", salaCirugia));
        executor.execute(new ProfesionalMedico("Dr. Torres", salaCirugia));

        // Apagar el ejecutor cuando termine
        executor.shutdown();
    }
}
