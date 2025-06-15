package C2_Reto1;

import java.util.concurrent.Callable;

public class SistemaNavegacion implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(1000); // Simula procesamiento
        return "🛰️ Navegación: trayectoria corregida con éxito.";
    }
}
