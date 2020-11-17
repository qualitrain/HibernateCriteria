package qtx.test;

import java.util.List;

import qtx.entidades.Mascota;
import qtx.persistencia.GestorPersistencia;

public class TestConsultaCriteriaConWhere {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		List<Mascota> mascotasBD = gp.getCriteriaGatosTodos();
		for(Mascota mascotaI : mascotasBD) {
			System.out.println(mascotaI.getNombre() + " que es un " + mascotaI.getTipo() + " cuyo dueño es " + mascotaI.getAmo().getNombre());
		}
		gp.cerrar();
	}

}
