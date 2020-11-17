package qtx.test;

import qtx.entidades.Persona;
import qtx.persistencia.GestorPersistencia;

public class TestActualizacion {

	public static void main(String[] args) {
		GestorPersistencia gp = new GestorPersistencia();
		Persona persona = gp.getPersonaXID(1);
		System.out.println(persona);
		persona.setProfesion("Nueva direcci�n");
		if(gp.actualizarPersona(persona)==true)
			System.out.println("Actualizaci�n exitosa");
		else
			System.out.println("Ha fallado la actualizaci�n");
	}

}
