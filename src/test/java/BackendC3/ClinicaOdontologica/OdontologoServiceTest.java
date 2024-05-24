package BackendC3.ClinicaOdontologica;

import BackendC3.ClinicaOdontologica.dao.BD_OD;
import BackendC3.ClinicaOdontologica.model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import BackendC3.ClinicaOdontologica.service.OdontologoService;

import java.util.List;

public class OdontologoServiceTest {
    //@Test
    public void buscarOdontologos(){
        BD_OD.crearTablas();
        OdontologoService odontologoService= new OdontologoService("h2");
        Integer id=2;
        List<Odontologo> odontologo= odontologoService.buscarTodos();

        //Assertions.assertTrue(odontologo !=null);
        Assertions.assertTrue(odontologo !=null);

    }

    @Test
    public void buscarOdontologosList(){
        OdontologoService odontologoService= new OdontologoService("list");
        Odontologo odontologo1 = new Odontologo(1, 1212, "Lina", "Lopez");
        Odontologo odontologo2 = new Odontologo(2, 2558, "Tatiana", "Jaramillo");

        odontologoService.guardarOdontologo(odontologo1);
        odontologoService.guardarOdontologo(odontologo2);

        List<Odontologo> odontologos = odontologoService.buscarTodos();

        odontologos.forEach(elemento -> System.out.println(elemento));

        Assertions.assertTrue(odontologos !=null);

    }

}
