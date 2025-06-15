package C3_Reto1;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ConfirmacionPedidos {
    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
            new Pedido("Ana", "domicilio", "555-1234"),
            new Pedido("Luis", "local", null),
            new Pedido("Pedro", "domicilio", "555-5678"),
            new Pedido("Marta", "domicilio", null),
            new Pedido("Carlos", "local", "555-9999")
        );

        pedidos.stream()
            .filter(p -> p.getTipoEntrega().equalsIgnoreCase("domicilio")) // Solo pedidos a domicilio
            .map(Pedido::getTelefono)                                      // Obtener Optional<String>
            .flatMap(Optional::stream)                                     // Dejar pasar solo los que tienen valor
            .map(tel -> "📞 Confirmación enviada al número: " + tel)      // Mensaje personalizado
            .forEach(System.out::println);                                 // Mostrar en consola
    }
}

