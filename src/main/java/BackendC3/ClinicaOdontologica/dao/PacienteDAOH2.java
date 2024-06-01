package BackendC3.ClinicaOdontologica.dao;

import BackendC3.ClinicaOdontologica.model.Domicilio;
import BackendC3.ClinicaOdontologica.model.Odontologo;
import BackendC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAOH2 implements iDao<Paciente> {
    private static final Logger logger= Logger.getLogger(PacienteDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO PACIENTES(NOMBRE, APELLIDO, CEDULA, FECHA_INGRESO, DOMICILIO_ID, EMAIL) VALUES(?,?,?,?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM PACIENTES WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM PACIENTES";

    //private static final String SQL_UPDATE="UPDATE FROM PACIENTES WHERE ID = ?";
    private static final String SQL_UPDATE_ALL="UPDATE PACIENTES SET NOMBRE=?, APELLIDO=?, CEDULA=?, FECHA_INGRESO=?, DOMICILIO_ID=?, EMAIL=?, WHERE ID=?";
    private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM PACIENTES WHERE EMAIL=?";
    private static final String SQL_DELETE_ONE="DELETE FROM PACIENTES WHERE ID = ?";

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("iniciando el guardado o agregado de paciente: " + paciente.getNombre());
        Connection connection = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();
        Domicilio domicilio= daoAux.guardar(paciente.getDomicilio());
        //PreparedStatement preparedStatement = null;

        try{
            //levantar driver y conección
            connection= BD.getConnection();

            //crear sentencia
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());

            /*LocalDate localDate = paciente.getFechaIngreso();
            Date sqlDate = Date.valueOf(localDate);
            psInsert.setDate(4,sqlDate);*/
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, domicilio.getId());
            //psInsert.setInt(5, paciente.getDomicilio().getId());

            psInsert.setString(6, paciente.getEmail());

            //ejecutar una sentencia
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();
            while (clave.next()){
                paciente.setId(clave.getInt(1));
            }
            logger.info("paciente guardado con exito");

        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        logger.info("iniciando la operacion de buscado de un paciente con id : "+id);
        Connection connection= null;
        Paciente paciente= null;
        Domicilio domicilio= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();
            while(rs.next()){
                domicilio= daoAux.buscarPorId(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }

        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("Iniciando la eliminación del paciente con id: " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            BD.getConnection();

            preparedStatement = connection.prepareStatement(SQL_DELETE_ONE);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            //preparedStatement.close();

        }catch (Exception e){
            logger.error(e.getMessage());

        }

    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Iniciando la actualización del paciente con id: " + paciente.getId());
        Connection connection = null;
        PreparedStatement psUpdate = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();
        try{
            BD.getConnection();

            daoAux.actualizar(paciente.getDomicilio());

            psUpdate = connection.prepareStatement(SQL_UPDATE_ALL);
            //psUpdate.setInt(1,paciente.getId());
            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getCedula());
            psUpdate.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.setString(6, paciente.getEmail());

            psUpdate.setInt(7, paciente.getId());

            psUpdate.execute();
            //psUpdate.close();*/

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("Iniciando la búsqueda de todos los pacientes");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Paciente> pacientes = new ArrayList<>();
        Paciente paciente = null;
        Domicilio domicilio = null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            //preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);

            //ResultSet result = preparedStatement.executeQuery();
            DomicilioDAOH2 daoAux= new DomicilioDAOH2();

            while(rs.next()){
                Integer id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String cedula = rs.getString("cedula");
                Date sqlDate = rs.getDate("fecha_ingreso");
                LocalDate fechaIngreso = ((java.sql.Date) sqlDate).toLocalDate();

                //Domicilio domicilio= daoAux.buscarPorId(rs.getInt(6));
                String email = rs.getString("email");

                pacientes.add(new Paciente(id, nombre, apellido, cedula, fechaIngreso, domicilio, email));

                System.out.println(id + nombre + apellido + cedula + fechaIngreso + domicilio + email);
            }

        }catch(Exception e){
            logger.error(e.getMessage());

        }
        return pacientes;
    }

    @Override
    public Paciente buscarPorString(String string) {
        logger.info("iniciando la búsqueda por email: " + string);
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        DomicilioDAOH2 daoAux = new DomicilioDAOH2();
        try{
            connection=BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_BY_EMAIL);
            psSelect.setString(1,string);
            ResultSet rs =  psSelect.executeQuery();
            while (rs.next()){
                domicilio=daoAux.buscarPorId(rs.getInt(6));
                paciente= new Paciente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toLocalDate(),domicilio, rs.getString(7));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return paciente;
    }


}
