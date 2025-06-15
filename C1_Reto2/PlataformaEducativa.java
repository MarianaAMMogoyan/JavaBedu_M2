package C1_Reto2;

import java.util.ArrayList;
import java.util.List;

public class PlataformaEducativa {
    public static void main(String[] args) {
        List<MaterialCurso> materiales = new ArrayList<>();

        materiales.add(new Video("Introducción a Java", "Mario", 15));
        materiales.add(new Video("POO en Java", "Carlos", 20));
        materiales.add(new Articulo("Historia de Java", "Ana", 1200));
        materiales.add(new Articulo("Tipos de datos", "Luis", 800));
        materiales.add(new Ejercicio("Variables y tipos", "Luis"));
        materiales.add(new Ejercicio("Condicionales", "Mario"));

        // Mostrar todos los materiales
        UtilidadesCurso.mostrarMateriales(materiales);

        // Filtrar y contar duración de videos
        List<Video> soloVideos = new ArrayList<>();
        for (MaterialCurso m : materiales) {
            if (m instanceof Video) {
                soloVideos.add((Video) m);
            }
        }
        UtilidadesCurso.contarDuracionVideos(soloVideos);

        // Marcar ejercicios como revisados
        UtilidadesCurso.marcarEjerciciosRevisados(materiales);

        // Filtrar por autor
        UtilidadesCurso.filtrarPorAutor(materiales, "Mario");
    }
}
