package qtx.test;

import java.util.List;

import qtx.entidades.Mascota;
import qtx.entidades.Persona;
import qtx.entidades.TipoMascota;
import qtx.persistencia.GestorPersistencia;

public class TestConsultaJPQLConParametro {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		List<Mascota> mascotasBD = gp.getMascotasDeTipo(TipoMascota.PERRO);
		for(Mascota mascotaI : mascotasBD) {
			System.out.println(mascotaI.getNombre() + " cuyo dueño es " + mascotaI.getAmo().getNombre());
		}
		gp.cerrar();
	}

}
