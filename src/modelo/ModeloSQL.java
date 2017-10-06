package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vista.Principal;
import vista.VistaAnadir;

public class  ModeloSQL implements AccesoDatos {
	private DBConnection dbconnection;
	private Principal principal;
	private Jugador jugador;
	private String query;
	private String user;
	private String pwd;
	private String db;
	private String host;
	private String puerto;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	
	public void cargarConfiguracion(String user, String pwd, String db, String host, String puerto) {
		this.user = user;
		this.pwd = pwd;
		this.db = db;
		this.host = host;
		this.puerto = puerto;
	}

	
	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {
		System.out.println("LLLLLLLLLLLOL");
		query="INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `Equipo`) VALUES (NULL, '"
				+ nombre + "', '" + apellido + "', '" + posicion + "', '" + equipo + "')";
		
		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador a�adido correctamente");
			stmt.close();
			
			
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
	
		
		
	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub
		query="DELETE FROM `Jugadores`";
		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Todos los jugadores borrados correctamente");
			stmt.close();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		
		
	}










	@Override
	public void DelPlayer(String iddel) {

		query="DELETE FROM `Jugadores` WHERE ID ="+iddel;

		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador borrado correctamente");
			stmt.close();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		
		
	}



	






	




	public void insertar(String nombre, String apellido, String posicion, String equipo) {
		

		query="INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `Equipo`) VALUES (NULL, '"
				+ nombre + "', '" + apellido + "', '" + posicion + "', '" + equipo + "')";
		
		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador a�adido correctamente");
			stmt.close();
			//principal.refreshTabla();
			
		} catch (SQLException s) {
			s.printStackTrace();
		}
	
		
	}




	public void delJug(String iddel) {
		query="DELETE FROM `Jugadores` WHERE ID ="+iddel;

		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador borrado correctamente");
			stmt.close();
			//principal.refreshTabla();
		} catch (SQLException s) {
			s.printStackTrace();
		}
		
		
		
	}






	public void delTodo() {
		
	}
	
	
	
	
	public String getUser() {
		return user;
	}






	public void setUser(String user) {
		this.user = user;
	}






	public String getPwd() {
		return pwd;
	}






	public void setPwd(String pwd) {
		this.pwd = pwd;
	}






	public String getDb() {
		return db;
	}






	public void setDb(String db) {
		this.db = db;
	}






	public String getHost() {
		return host;
	}






	public void setHost(String host) {
		this.host = host;
	}






	public String getPuerto() {
		return puerto;
	}






	public void setPuerto(String puerto) {
		this.puerto = puerto;
	}













	public Principal getPrincipal() {
		return principal;
	}






	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}






	public Jugador getJugador() {
		return jugador;
	}






	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}






	public String getQuery() {
		return query;
	}






	public void setQuery(String query) {
		this.query = query;
	}
	





	public DBConnection getDbconnection() {
		return dbconnection;
	}






	public void setDbconnection(DBConnection dbconnection) {
		this.dbconnection = dbconnection;
	}


	@Override
	public ArrayList<Jugador> Consul() {
		System.out.println("dsad23");
		dbconnection=new DBConnection();
		dbconnection.conexion();
		
		
	//	this.setPrincipal(principal);
		
		query = "SELECT * FROM idd.jugadores";
		try {
			
			if(dbconnection !=null) {

				Statement stmt = dbconnection.getConexion().createStatement();
				ResultSet rset = stmt.executeQuery(query);
				
				while (rset.next()) {
					jugador=new Jugador(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5));
					System.out.println(jugador);
					jugadores.add(jugador);
					
				}
				rset.close();
				stmt.close();
				
			} else {
				System.out.println("NULO");
			}
			

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return jugadores;
	}


	@Override
	public void volcar() {
		// TODO Auto-generated method stub
		
	}


	











	
	
	
}

	
