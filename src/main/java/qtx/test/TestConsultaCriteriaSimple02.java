package qtx.test;

import java.util.List;

import qtx.persistencia.GestorPersistencia;

public class TestConsultaCriteriaSimple02 {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		List<String> nombresmascotasBD = gp.getCriteriaNombresMascotasTodas();
		System.out.println(nombresmascotasBD);
		gp.cerrar();
	}

}
