package BackendC3.ClinicaOdontologica.dao;

import BackendC3.ClinicaOdontologica.model.Domicilio;
import BackendC3.ClinicaOdontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DomicilioDAOH2 implements iDao<Domicilio>{
    private static final Logger logger= Logger.getLogger(DomicilioDAOH2.class);

    private static final String SQL_INSERT="INSERT INTO DOMICILIOS(CALLE, NUMERO, LOCALIDAD, PROVINCIA) VALUES(?,?,?,?)";
    private static final String SQL_SELECT_ALL="SELECT * FROM DOMICILIOS";
    private static final String SQL_SELECT_ONE="SELECT * FROM DOMICILIOS WHERE ID=?";
    private static final String SQL_DELETE_ONE="DELETE FROM DOMICILIOS WHERE ID = ?";
    private static final String SQL_UPDATE="UPDATE FROM DOMICILIOS WHERE ID = ?";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        logger.info("inicio del Guardado o agregado de domicilios");
        Connection connection = null;
        //PreparedStatement preparedStatement = null;

        try{
            //levantar driver y conección
            connection= BD.getConnection();

            //crear sentencia
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());

            //ejecutar una sentencia
            psInsert.execute();

            ResultSet clave = psInsert.getGeneratedKeys();
            while(clave.next()){
                domicilio.setId(clave.getInt(1));
            }
            logger.info("domicilio guardado");

        }catch(Exception e){
            logger.error(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarPorId(Integer id) {
        logger.info("iniciando la busqueda de un domicilio con id: "+id);
        Connection connection= null;
        Domicilio domicilio= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while (rs.next()){
                domicilio= new Domicilio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando la eliminación del domicilio con id: "+ id);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            BD.getConnection();

            preparedStatement = connection.prepareStatement(SQL_DELETE_ONE);
            preparedStatement.setInt(1,id);

            preparedStatement.executeUpdate();
            /*preparedStatement.close();*/

        }catch (Exception e){
            logger.error(e.getMessage());

        }

    }

    @Override
    public void actualizar(Domicilio domicilio) {
        logger.info("Iniciando la actualización del domicilio con id: "+ domicilio.getId());
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try{
            BD.getConnection();

            preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setInt(1,domicilio.getId());

            preparedStatement.executeUpdate();
            //preparedStatement.close();*/

        }catch (Exception e){
            logger.error(e.getMessage());

        }

    }

    @Override
    public List<Domicilio> buscarTodos() {
        logger.info("Iniciando la búsqueda de todos los domicilios");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<Domicilio> domicilios = new ArrayList<>();
        try{
            connection= BD.getConnection();
            //Statement statement= connection.createStatement();
            preparedStatement = connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet result = preparedStatement.executeQuery();

            while(result.next()){
                Integer id = result.getInt("id");
                String calle = result.getString("calle");
                Integer numero = result.getInt("numero");
                String localidad = result.getString("localidad");
                String provincia = result.getString("provincia");

                domicilios.add(new Domicilio(id, calle, numero, localidad, provincia));

                System.out.println(id + calle + numero + localidad + provincia);
            }

        }catch(Exception e){
            logger.error(e.getMessage());

        }
        return domicilios;
    }

    @Override
    public Domicilio buscarPorString(String string) {
        return null;
    }
}
