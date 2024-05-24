package BackendC3.ClinicaOdontologica.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Odontologo {
    private Integer id;
    private int numeroMatricula;
    private String nombre;
    private String apellido;

    public Odontologo(int numeroMatricula, String nombre, String apellido) {
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Odontologo(Integer id, int numeroMatricula, String nombre, String apellido) {
        this.id = id;
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public String toString(){
        return "Id: " + getId() + " número matricula: " + getNumeroMatricula() + " nombre: " + getNombre() + " apellido: " + getApellido();
    }
}
