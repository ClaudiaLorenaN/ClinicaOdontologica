package BackendC3.ClinicaOdontologica.service;
import BackendC3.ClinicaOdontologica.dao.DomicilioDAOH2;
import BackendC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackendC3.ClinicaOdontologica.dao.OdontologoDAOList;
import BackendC3.ClinicaOdontologica.dao.iDao;
import BackendC3.ClinicaOdontologica.model.Domicilio;

import java.util.List;

public class DomicilioService {
    private iDao<Domicilio> domicilioiDao;


    public DomicilioService() {domicilioiDao= new DomicilioDAOH2();}

    //metodos manuales

    //delega la responsabilidad de guardar al DAO
    public Domicilio guardarDomicilio(Domicilio domicilio){
        return domicilioiDao.guardar(domicilio);
    }

    //delega la responsabilidad de buscar al DAO
    public Domicilio buscarPorID(Integer id){
        return domicilioiDao.buscarPorId(id);
    }

    //delega la responsabilidad de eliminar al DAO
    public void eliminarDomicilio(Integer id){
        domicilioiDao.eliminar(id);
    }

    //delega la responsabilidad de actualizar al DAO
    public void actualizar(Domicilio domicilio){
        domicilioiDao.actualizar(domicilio);
    }

    //delega la responsabilidad de buscar todos al DAO
    public List<Domicilio> buscarTodos(){
        return domicilioiDao.buscarTodos();
    }
}
