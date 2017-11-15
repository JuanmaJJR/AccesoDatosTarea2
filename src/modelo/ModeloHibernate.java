package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ModeloHibernate implements AccesoDatos {
	private DBConnection dbconnection;
	Session s;
	private SessionFactory sessionFactory;
	private String query;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	Equipo equipo1;
	Jugador jugador1;

	
	public ModeloHibernate() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
		s = sessionFactory.openSession();
	}

	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		s.beginTransaction();
		System.out.println(equipo);
		if(equipo.equals("Real Madrid")) {
			 this.equipo1 = new Equipo(1,equipo);
		}
		else if(equipo.equals("Barcelona")) {
			 this.equipo1 = new Equipo(2,equipo);
			
		}
		else if(equipo.equals("Oporto")) {
			 this.equipo1 = new Equipo(3,equipo);
			
		}
		
		 this.jugador1 = new Jugador(-1,nombre, apellido, posicion, equipo1);
		s.save(jugador1);
		s.getTransaction().commit();
		
	}

	@Override
	public void DelPlayer(int iddel) {
		// TODO Auto-generated method stub
		s.beginTransaction();
		Query q= s.createQuery("delete from Jugador WHERE id ='"+iddel+"'");
		q.executeUpdate();
		s.getTransaction().commit();
		System.out.println("borrado uno con exito");
		
	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub
		s.beginTransaction();
		Query q= s.createQuery("delete from Jugador ");
		q.executeUpdate();
		s.getTransaction().commit();
		System.out.println("borrado todo con exito");
		
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
	public void volcar(String tipo) {
		// TODO Auto-generated method stub
		if(tipo.equals("SQL")) {
			dbconnection = new DBConnection();
			dbconnection.conexion();

			// this.setPrincipal(principal);
			
			Query q = s.createQuery("SELECT j FROM Jugador j");
			List results = q.list();
			
			
			Iterator consulta = results.iterator();

			while (consulta.hasNext()) {
				Jugador jugador = (Jugador) consulta.next();
				equipo1 = jugador.getEquipo();
				query = "INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `ID_Equipo`) VALUES (NULL, '" + jugador.getNombre()
						+ "', '" + jugador.getApellido() + "', '" + jugador.getPosicion() + "', '" + equipo1.getID() + "')";

				try {
					Statement stmt = dbconnection.getConexion().createStatement();
					stmt.executeUpdate(query);
					System.out.println("Jugador añadido correctamente");
					stmt.close();
					// principal.refreshTabla();

				} catch (SQLException s) {
					s.printStackTrace();
				}
			}
			
		}
		
		
		
		
		else if(tipo.equals("Fichero")) {
			
		}
	}

	@Override
	public void AddEquipo(String nombre, String descripcion) {
		// TODO Auto-generated method stub
		s.beginTransaction();
		this.equipo1 = new Equipo(1,nombre);
		s.save(equipo1);
		s.getTransaction().commit();
		System.out.println("todo guay");
		
		
	}

	@Override
	public void escribeTodos(ArrayList<Jugador> jugadores) {
		
		s.beginTransaction();

		
		
		for(int x=0;x<jugadores.size();x++) {
			  s.save(jugadores.get(x));
		}
		
		s.getTransaction().commit();

		
	}

}
