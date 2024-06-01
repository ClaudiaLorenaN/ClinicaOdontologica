package BackendC3.ClinicaOdontologica.controller;

import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.service.OdontologoService;
import BackendC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // es controller porque vamos a usar una tecnología vista
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;
    /*public OdontologoController() {
        odontologoService =  new OdontologoService("h2");
    }*/
    public OdontologoController() {
        odontologoService =  new OdontologoService();
    }

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    /*@GetMapping
    public String buscarOdontologoPorID(Model model, @RequestParam("id") Integer id){
        Odontologo odontologo = odontologoService.buscarPorID(id);
        model.addAttribute("numero_matricula", odontologo.getNumeroMatricula());
        return "index";
    }*/

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);

    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){

        Odontologo odontologoBuscado= odontologoService.buscarPorID(odontologo.getId());

        if(odontologoBuscado!=null){
            odontologoService.actualizar(odontologo);
            return "odontologo actualizado con exito";
        }else{
            return "odontologo no encontrado";
        }

    }
    @GetMapping("/{id}")
    public Odontologo buscarPorOdontologo(@PathVariable Integer id){
        return odontologoService.buscarPorID(id);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @DeleteMapping
    public String eliminarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado= odontologoService.buscarPorID(odontologo.getId());

        if(odontologoBuscado!=null){
            odontologoService.eliminarOdontologo(odontologo.getId());
            return "odontologo eliminado con exito";
        }else{
            return "odontologo no eliminado";
        }

    }
}
