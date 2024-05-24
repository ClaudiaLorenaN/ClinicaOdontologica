package BackendC3.ClinicaOdontologica.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BD_OD {
    private static final Logger logger= Logger.getLogger(BD_OD.class);
    private static final String SQL_DROP_CREATE_ODO="DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS (" +
            "ID INT AUTO_INCREMENT PRIMARY KEY, NUMERO_MATRICULA INT NOT NULL, NOMBRE VARCHAR(100) NOT NULL, APELLIDO VARCHAR(100) NOT NULL)";
    private static final String SQL_PRUEBA="INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES (1111, 'Jorgito','Pereyra'), (2222, 'Beatriz','Giraldo'), (3333, 'Claudia','Naranjo')";

    public static void crearTablas(){
        Connection connection= null;
        try{
            connection= getConnection();
            Statement statement= connection.createStatement();
            statement.execute(SQL_DROP_CREATE_ODO);
            statement.execute(SQL_PRUEBA);
            logger.info("tabla creada con exito");


        }catch (Exception e){
            logger.warn(e.getMessage());
        }

    }
    public static Connection getConnection() throws Exception{
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:mem:~/clinicaOdontologica","sa","sa");
    }
}
