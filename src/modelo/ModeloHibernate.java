package modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class ModeloHibernate implements AccesoDatos {
	Session s;
	private SessionFactory sessionFactory;
	private String query;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	
	public ModeloHibernate() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		s = sessionFactory.openSession();
	}

	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DelPlayer(String iddel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Jugador> Consul() {
		// TODO Auto-generated method stub
		
		Query q = s.createQuery("SELECT j FROM Jugador j");
		List results = q.list();
		
		
		Iterator consulta = results.iterator();

		while (consulta.hasNext()) {
			Jugador jugador = (Jugador) consulta.next();
			jugadores.add(jugador);
		}
		
		return jugadores;
	}

	@Override
	public void volcar() {
		// TODO Auto-generated method stub
		
	}

}
