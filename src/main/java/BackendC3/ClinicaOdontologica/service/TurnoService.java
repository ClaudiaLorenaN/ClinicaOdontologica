package BackendC3.ClinicaOdontologica.service;

import BackendC3.ClinicaOdontologica.dao.TurnoDAOLIst;
import BackendC3.ClinicaOdontologica.dao.iDao;
import BackendC3.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao = new TurnoDAOLIst();
    }

    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }

    public List<Turno> buscarTodos(){
        return turnoiDao.buscarTodos();
    }

    public Turno buscarPorId(Integer id){
        return turnoiDao.buscarPorId(id);

    }
}
