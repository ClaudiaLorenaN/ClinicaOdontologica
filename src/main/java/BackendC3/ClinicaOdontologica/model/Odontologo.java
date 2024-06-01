package BackendC3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odontologo {
    private Integer id;
    private int matricula;
    private String nombre;
    private String apellido;

    public Odontologo(int numeroMatricula, String nombre, String apellido) {
        this.matricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer id, int numeroMatricula, String nombre, String apellido) {
        this.id = id;
        this.matricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo() {
    }

    @Override
    public String toString(){
        return "Id: " + getId() + " número matricula: " + getMatricula() + " nombre: " + getNombre() + " apellido: " + getApellido();
    }
}
