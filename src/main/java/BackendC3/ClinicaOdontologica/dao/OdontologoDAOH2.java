package BackendC3.ClinicaOdontologica.dao;

import BackendC3.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);

    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE_ONE="DELETE FROM ODONTOLOGOS WHERE ID = ?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID = ?";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * FROM ODONTOLOGOS WHERE MATRICULA=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("inicio del guardado o agregado de odontologos");
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            //levantar driver y conección
            connection= BD.getConnection();

            //crear sentencia
            preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2, odontologo.getNombre());
            preparedStatement.setString(3, odontologo.getApellido());

            //ejecutar una sentencia
            preparedStatement.execute();
            //preparedStatement.close();
            ResultSet clave = preparedStatement.getGeneratedKeys();

            while(clave.next()){
                odontologo.setId(clave.getInt(1));
            }

        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando la busqueda de un odontologo con id: "+id);
        Connection connection= null;
        Odontologo odontologo= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){
                odontologo= new Odontologo(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4));

            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando la eliminación de un odontologo con id: " + id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            BD.getConnection();

            preparedStatement = connection.prepareStatement(SQL_DELETE_ONE);
            preparedStatement.setInt(1,id);

            /*preparedStatement.executeUpdate();
            preparedStatement.close();*/

        }catch (Exception e){
            logger.error(e.getMessage());

        }

    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.info("iniciando la actualización de un odontologo con id: " + odontologo.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            BD.getConnection();

            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1,odontologo.getId());

            preparedStatement.executeUpdate();
            //preparedStatement.close();*/

        }catch (Exception e){
            logger.error(e.getMessage());

        }

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Iniciando la búsqueda de todos los odontologos");
        Connection connection = null;
        Statement statement = null;
        List<Odontologo> odontologos = new ArrayList<>();
        Odontologo odontologo = null;
        try{
            connection= BD.getConnection();
            //Statement statement= connection.createStatement();
            statement = connection.createStatement();

            ResultSet result = statement.executeQuery(SQL_SELECT_ALL);

            while(result.next()){
                Integer id = result.getInt("id");
                Integer matricula = result.getInt("matricula");
                String nombre = result.getString("nombre");
                String apellido = result.getString("apellido");


                odontologos.add(new Odontologo(id, matricula, nombre, apellido));

                System.out.println(id + matricula + nombre + apellido);
            }

        }catch(Exception e){
            logger.error(e.getMessage());

        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        return null;
    }
}
