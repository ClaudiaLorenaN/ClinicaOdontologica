package BackendC3.ClinicaOdontologica;

import BackendC3.ClinicaOdontologica.dao.BD;
import BackendC3.ClinicaOdontologica.dao.BD_OD;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		BD.crearTablas();
		//BD_OD.crearTablas();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}


}
