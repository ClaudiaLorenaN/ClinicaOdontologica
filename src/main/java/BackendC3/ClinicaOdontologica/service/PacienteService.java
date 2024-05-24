package BackendC3.ClinicaOdontologica.service;

import BackendC3.ClinicaOdontologica.dao.PacienteDAOH2;
import BackendC3.ClinicaOdontologica.dao.iDao;
import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    //metodos manuales
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscarPorId(id);
    }

    //delega la responsabilidad de eliminar al DAO
    public void eliminarPaciente(Integer id){
        pacienteiDao.eliminar(id);
    }

    //delega la responsabilidad de actualizar al DAO
    public void actualizar(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }

    //delega la responsabilidad de buscar todos al DAO
    public List<Paciente> buscarTodos(){
        return pacienteiDao.buscarTodos();
    }

    public Paciente buscarPorEmail(String email){
        return pacienteiDao.buscarPorString(email);
    }
}
