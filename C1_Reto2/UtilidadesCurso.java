package C1_Reto2;

import java.util.List;

public class UtilidadesCurso {

    // Muestra todos los materiales
    public static void mostrarMateriales(List<? extends MaterialCurso> lista) {
        System.out.println("📚 Materiales del curso:");
        for (MaterialCurso material : lista) {
            material.mostrarDetalle();
        }
    }

    // Suma duración de videos
    public static void contarDuracionVideos(List<? extends Video> lista) {
        int total = 0;
        for (Video video : lista) {
            total += video.getDuracion();
        }
        System.out.println("\n🎥 Duración total de videos: " + total + " minutos");
    }

    // Marca ejercicios como revisados
    public static void marcarEjerciciosRevisados(List<? super Ejercicio> lista) {
        for (Object obj : lista) {
            if (obj instanceof Ejercicio) {
                Ejercicio ejercicio = (Ejercicio) obj;
                ejercicio.marcarRevisado();
                System.out.println("✅ Ejercicio '" + ejercicio.titulo + "' marcado como revisado.");
            }
        }
    }

    // Filtra por autor
    public static void filtrarPorAutor(List<? extends MaterialCurso> lista, String autorBuscado) {
        System.out.println("\n🔍 Filtrando materiales por autor:");
        for (MaterialCurso material : lista) {
            if (material.autor.equalsIgnoreCase(autorBuscado)) {
                material.mostrarDetalle();
            }
        }
    }
}

