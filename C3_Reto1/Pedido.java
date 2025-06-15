package C3_Reto1;

import java.util.Optional;

public class Pedido {
    private String cliente;
    private String tipoEntrega; // "domicilio" o "local"
    private String telefono;    // puede ser null

    public Pedido(String cliente, String tipoEntrega, String telefono) {
        this.setCliente(cliente);
        this.tipoEntrega = tipoEntrega;
        this.telefono = telefono;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    // Devolvemos un Optional para manejar el posible null
    public Optional<String> getTelefono() {
        return Optional.ofNullable(telefono);
    }

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
}
