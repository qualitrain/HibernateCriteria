package qtx.test;

import java.util.List;

import qtx.entidades.Mascota;
import qtx.persistencia.GestorPersistencia;

public class TestPrevioConsultaJPQLSimple {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		
		List<Mascota> mascotasBD = gp.getMascotasOrdenadas();
		for(Mascota mascotaI : mascotasBD) {
			System.out.println(mascotaI.getNombre() + " cuyo amo es " + mascotaI.getAmo().getNombre());
		}
		gp.cerrar();
	}

}
