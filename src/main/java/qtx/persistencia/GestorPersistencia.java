package qtx.persistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import qtx.entidades.LugarEstacionamiento;
import qtx.entidades.Mascota;
import qtx.entidades.Persona;
import qtx.entidades.TipoMascota;

public class GestorPersistencia {
	EntityManagerFactory fabrica;
	
	public GestorPersistencia() {
		this.fabrica = Persistence.createEntityManagerFactory("miBaseDeDatos");
	}
	public Persona getPersonaXID(int id){
		EntityManager em = this.fabrica.createEntityManager();
		Persona personaI = em.find(Persona.class, id);
		personaI.getMascotas().size();
		em.close();
		return personaI;
	}
	public LugarEstacionamiento getEstacionamientoXID(int id){
		EntityManager em = this.fabrica.createEntityManager();
		LugarEstacionamiento estacionamientoI = em.find(LugarEstacionamiento.class, id);
//		em.close();
		return estacionamientoI;
	}
	public boolean insertarPersona(Persona p) {
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		em.persist(p);
		try {
			transaccion.commit();
		}
		catch(Exception ex) {
			return false;
		}
		finally { em.close(); }
		return true;
	}
	public boolean actualizarPersona(Persona p) {
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		try {
			Persona personaMerge = em.merge(p);
			em.persist(personaMerge);
			transaccion.commit();
		}
		catch(Exception ex) {
			return false;
		}
		finally { em.close(); }
		
		return true;
	}
	public boolean eliminarPersona(Persona p) {
		EntityManager em = this.fabrica.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		try {
			Persona personaMerge = em.merge(p);
			em.remove(personaMerge);
			transaccion.commit();
		}
		catch(Exception ex) {
			return false;
		}
		finally { em.close(); }
		
		return true;
	}
	public List<Mascota> getMascotasOrdenadas(){
		EntityManager em = this.fabrica.createEntityManager();
		String CONSULTA = "SELECT m FROM Mascota m ORDER BY m.amo.nombre";
		@SuppressWarnings("unchecked")
		List<Mascota> mascotas = em.createQuery(CONSULTA).getResultList();
		em.close();
		return mascotas;		
	}
	public List<Mascota> getMascotasTodas(){
		EntityManager em = this.fabrica.createEntityManager();
		String consulta = "SELECT m FROM Mascota m";
		@SuppressWarnings("unchecked")
		List<Mascota> mascotas = em.createQuery(consulta)
							       .getResultList();
		em.close();
		return mascotas;		
	}
	public List<Mascota> getGatosTodos(){
		EntityManager em = this.fabrica.createEntityManager();
		String consulta = "SELECT m FROM Mascota m WHERE m.tipo = 'GATO' ORDER BY m.amo.nombre ";
		@SuppressWarnings("unchecked")
		List<Mascota> mascotas = em.createQuery(consulta)
							       .getResultList();
		em.close();
		return mascotas;		
	}
	public List<Mascota> getMascotasDeTipo(TipoMascota tipo){
		EntityManager em = this.fabrica.createEntityManager();
		String consulta = "SELECT m FROM Mascota m WHERE m.tipo = :tipoMascota ORDER BY m.amo.nombre ";
		@SuppressWarnings("unchecked")
		List<Mascota> mascotas = em.createQuery(consulta)
								   .setParameter("tipoMascota", tipo)
							       .getResultList();
		em.close();
		return mascotas;		
	}
	public List<Persona> getPersonasConMascotas(){
		EntityManager em = this.fabrica.createEntityManager();
		String consulta = "SELECT DISTINCT p FROM Persona p JOIN p.mascotas m ORDER BY p.nombreCompleto.apPaterno ";
		@SuppressWarnings("unchecked")
		List<Persona> personas = em.createQuery(consulta)
							       .getResultList();
		em.close();
		return personas;
	}
	public List<Persona> getPersonasConGatos(){
		EntityManager em = this.fabrica.createEntityManager();
		String consulta = "SELECT DISTINCT p FROM Persona p JOIN p.mascotas m WHERE m.tipo = 'GATO' ORDER BY p.nombreCompleto.apPaterno ";
		@SuppressWarnings("unchecked")
		List<Persona> personas = em.createQuery(consulta)
							       .getResultList();
		em.close();
		return personas;
	}
	public void cerrar() {
		this.fabrica.close();
	}
	public List<Mascota> getCriteriaMascotasTodas(){
		EntityManager em = this.fabrica.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Mascota> configConsulta = cb.createQuery(Mascota.class);
		Root<Mascota> raizMascota = configConsulta.from(Mascota.class);
		configConsulta.select(raizMascota);
		
		List<Mascota> mascotas = em.createQuery(configConsulta).getResultList();
		
		em.close();
		return mascotas;		
	}
	public List<String> getCriteriaNombresMascotasTodas(){
		EntityManager em = this.fabrica.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<String> configConsulta = cb.createQuery(String.class);
		Root<Mascota> raizMascota = configConsulta.from(Mascota.class);
		configConsulta.select(raizMascota.get("nombre"));
		
		List<String> nombresMascotas = em.createQuery(configConsulta).getResultList();
		
		em.close();
		return nombresMascotas;		
	}
	public List<Mascota> getCriteriaGatosTodos(){
		EntityManager em = this.fabrica.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Mascota> configConsulta = cb.createQuery(Mascota.class);
		Root<Mascota> raizMascota = configConsulta.from(Mascota.class);
		configConsulta.select(raizMascota);
		configConsulta.where(cb.equal(raizMascota.get("tipo"), TipoMascota.GATO));
		configConsulta.orderBy(cb.desc(raizMascota.get("amo").get("nombreCompleto").get("apPaterno")));
		
		List<Mascota> mascotas = em.createQuery(configConsulta).getResultList();
		
		em.close();
		return mascotas;		
	}
	public List<Persona> getCriteriaPersonasConGatos(){
		EntityManager em = this.fabrica.createEntityManager();
//		String consulta = "SELECT DISTINCT p FROM Persona p JOIN p.mascotas m WHERE m.tipo = 'GATO'"
//				+ " ORDER BY p.nombreCompleto.apPaterno ";
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Persona> configConsulta = cb.createQuery(Persona.class);		
		Root<Persona> raizPersona = configConsulta.from(Persona.class);
		Join<Object, Object> joinMascotas = raizPersona.join("mascotas");
		
		configConsulta.select(raizPersona)
		              .distinct(true)
		              .where(cb.equal(joinMascotas.get("tipo"), TipoMascota.GATO))
		              .orderBy(cb.asc(raizPersona.get("nombreCompleto").get("apPaterno")));

		
		List<Persona> personas = em.createQuery(configConsulta)
							       .getResultList();
		em.close();
		return personas;
	}

}
