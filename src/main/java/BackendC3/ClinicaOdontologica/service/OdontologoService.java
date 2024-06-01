package BackendC3.ClinicaOdontologica.service;

import BackendC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackendC3.ClinicaOdontologica.dao.OdontologoDAOList;
import BackendC3.ClinicaOdontologica.dao.iDao;
import BackendC3.ClinicaOdontologica.model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    /*public OdontologoService(String origenDatos) {
        if(origenDatos.equals("h2")){
            odontologoiDao= new OdontologoDAOH2();
        }else{
            odontologoiDao= new OdontologoDAOList();
        }
    }*/

    public OdontologoService() {
        odontologoiDao= new OdontologoDAOH2();
    }

    //metodos manuales

    //delega la responsabilidad de guardar al DAO
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }

    //delega la responsabilidad de buscar al DAO
    public Odontologo buscarPorID(Integer id){
        return odontologoiDao.buscarPorId(id);
    }

    //delega la responsabilidad de eliminar al DAO
    public void eliminarOdontologo(Integer id){
        odontologoiDao.eliminar(id);
    }

    //delega la responsabilidad de actualizar al DAO
    public void actualizar(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }

    //delega la responsabilidad de buscar todos al DAO
    public List<Odontologo> buscarTodos(){
        return odontologoiDao.buscarTodos();
    }
}
