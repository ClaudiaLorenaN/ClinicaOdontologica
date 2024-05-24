package BackendC3.ClinicaOdontologica.controller;

import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.service.OdontologoService;
import BackendC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // es controller porque vamos a usar una tecnología vista
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;
    public OdontologoController() {
        odontologoService =  new OdontologoService("h2");
    }

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    @GetMapping
    public String buscarOdontologoPorID(Model model, @RequestParam("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorID(id);
        model.addAttribute("numero_matricula", odontologo.getNumeroMatricula());
        return "index";
    }
}
