package BackendC3.ClinicaOdontologica.controller;

import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.model.Turno;
import BackendC3.ClinicaOdontologica.service.OdontologoService;
import BackendC3.ClinicaOdontologica.service.PacienteService;
import BackendC3.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService = new TurnoService();
        pacienteService = new PacienteService();
        odontologoService = new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorID(turno.getOdontologo().getId());
        if(pacienteBuscado!=null && odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
