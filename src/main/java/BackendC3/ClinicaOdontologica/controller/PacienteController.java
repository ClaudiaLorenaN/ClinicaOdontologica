package BackendC3.ClinicaOdontologica.controller;

import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController //para trabajar sin tecnologia de vista
// es controller porque vamos a usar una tecnología vista
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService =  new PacienteService();
    }

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    /*@GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
        Paciente paciente = pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        return "index";
    }*/

    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){

        Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizar(paciente);
            return "paciente actualizado con exito";
        }else{
            return "paciente no encontrado";
        }
    }

    @GetMapping("/{id}")
    public Paciente buscarPorPaciente(@PathVariable Integer id){
        return pacienteService.buscarPorID(id);
    }






}
