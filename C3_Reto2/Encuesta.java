package C3_Reto2;

import java.util.Optional;

public class Encuesta {
    private String paciente;
    private String comentario; // Puede ser null
    private int calificacion;  // 1 a 5

    public Encuesta(String paciente, String comentario, int calificacion) {
        this.setPaciente(paciente);
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public Optional<String> getComentario() {
        return Optional.ofNullable(comentario);
    }

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
}

