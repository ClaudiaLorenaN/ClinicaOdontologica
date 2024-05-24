package BackendC3.ClinicaOdontologica.dao;

import BackendC3.ClinicaOdontologica.model.Odontologo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOList implements iDao<Odontologo>{
    List<Odontologo> odontologos = new ArrayList<>();
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        odontologos.add(odontologo);
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        Odontologo odontologoBuscado = null;
        int i = 0;
        while(i < odontologos.size() && odontologoBuscado == null){
            Odontologo odontologo = odontologos.get(i);
            if(odontologo.getId() == id) {
                odontologoBuscado = odontologo;
            }
            i++;
        }
        return odontologoBuscado;
    }

    @Override
    public void eliminar(Integer id) {
        Odontologo odontologo = buscarPorId(id);
        odontologos.remove(odontologo);

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }
}
