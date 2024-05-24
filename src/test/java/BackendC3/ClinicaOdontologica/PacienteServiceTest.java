package BackendC3.ClinicaOdontologica;

import BackendC3.ClinicaOdontologica.dao.BD;
import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PacienteServiceTest {
    @Test
    public void buscarPacientePorID(){
        BD.crearTablas();
        PacienteService pacienteService = new PacienteService();
        Integer id =2;
        Paciente paciente = pacienteService.buscarPorID(id);
        Assertions.assertTrue(paciente!=null);
    }
}
