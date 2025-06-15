package C3_Reto2;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProcesadorEncuestas {
    public static void main(String[] args) {
        // Crear encuestas para sucursales
        Sucursal centro = new Sucursal("Centro", Arrays.asList(
            new Encuesta("Ana", "El tiempo de espera fue largo", 2),
            new Encuesta("Luis", null, 3),
            new Encuesta("Carlos", "Todo bien", 5)
        ));

        Sucursal norte = new Sucursal("Norte", Arrays.asList(
            new Encuesta("Pedro", "La atención fue buena, pero la limpieza puede mejorar", 3),
            new Encuesta("Laura", null, 2),
            new Encuesta("Sofía", "Excelente servicio", 5)
        ));

        List<Sucursal> sucursales = Arrays.asList(centro, norte);

        // Procesamiento funcional
        sucursales.stream()
            .flatMap(sucursal -> // recorrer cada sucursal
                sucursal.getEncuestas().stream()
                    .filter(e -> e.getCalificacion() <= 3) // solo insatisfechos
                    .map(encuesta -> encuesta.getComentario()
                        .map(com -> "Sucursal " + sucursal.getNombre() +
                                    ": Seguimiento a paciente con comentario: \"" + com + "\""))
                    .flatMap(Optional::stream) // solo los comentarios no nulos
            )
            .forEach(System.out::println); // imprimir mensajes
    }
}
