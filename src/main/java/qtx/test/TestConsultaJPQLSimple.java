package qtx.test;

import java.util.List;

import qtx.entidades.Mascota;
import qtx.entidades.Persona;
import qtx.persistencia.GestorPersistencia;

public class TestConsultaJPQLSimple {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		List<Mascota> mascotasBD = gp.getMascotasTodas();
		for(Mascota mascotaI : mascotasBD) {
			System.out.println(mascotaI.getNombre() + " cuyo dueño es " + mascotaI.getAmo().getNombre());
		}
		gp.cerrar();
	}

}
