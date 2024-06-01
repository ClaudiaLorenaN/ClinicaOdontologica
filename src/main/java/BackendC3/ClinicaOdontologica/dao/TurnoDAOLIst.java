package BackendC3.ClinicaOdontologica.dao;

import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;
import BackendC3.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TurnoDAOLIst implements iDao<Turno> {
    private Logger logger= Logger.getLogger(TurnoDAOLIst.class);
    private List<Turno> turnos= new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        logger.info("iniciando las operaciones de guardado de un turno");
        PacienteDAOH2 pacienteDAOH2 = new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2 = new OdontologoDAOH2();
        turno.setPaciente(pacienteDAOH2.buscarPorId(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAOH2.buscarPorId(turno.getOdontologo().getId()));
        turnos.add(turno);
        logger.info("turno guardado con éxito");
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno : turnos) {
            if(turno.getId().equals(id)){
                return turno;
            }else {
                System.out.println("turno no encontrado");
            }

        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("inicia el eliminado del turno");
        Turno turno = buscarPorId(id);
        turnos.remove(turno);
        logger.info("turno eliminado");
    }


    @Override
    public void actualizar(Turno turno) {
        logger.info("inicia la actualización del turno");
        Turno turno1 = buscarPorId(turno.getId());
        turno1.setPaciente(turno.getPaciente());
        turno1.setOdontologo(turno.getOdontologo());
        turno1.setFecha(turno.getFecha());

    }

    @Override
    public List<Turno> buscarTodos() {
        logger.info("iniciando las operacion de mostrar todos los turnos");
        return turnos;
    }

    @Override
    public Turno buscarPorString(String string) {
        return null;
    }
}
