package qtx.test;

import java.util.List;

import qtx.entidades.Mascota;
import qtx.entidades.Persona;
import qtx.persistencia.GestorPersistencia;

public class TestConsultaCriteriaJoin {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		List<Persona> personas = gp.getCriteriaPersonasConGatos();
		System.out.println("Las personas que tienen gatos son:");
		for(Persona personaI : personas) {
			System.out.println(personaI.getNombre());
		}
		gp.cerrar();
	}

}
