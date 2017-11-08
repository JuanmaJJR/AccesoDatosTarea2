package modelo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vista.Principal;
import vista.VistaAnadir;

public class ModeloSQL implements AccesoDatos {
	private DBConnection dbconnection;
	private Principal principal;
	private Jugador jugador;
	private Equipo equipo;
	private String query;
	private String query2;
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
		query = "INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `Equipo`) VALUES (NULL, '" + nombre
				+ "', '" + apellido + "', '" + posicion + "', '" + equipo + "')";

		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador añadido correctamente");
			stmt.close();

		} catch (SQLException s) {
			s.printStackTrace();
		}

	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub
		query = "DELETE FROM `Jugadores`";
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

		query = "DELETE FROM `Jugadores` WHERE ID =" + iddel;

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

		query = "INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `Equipo`) VALUES (NULL, '" + nombre
				+ "', '" + apellido + "', '" + posicion + "', '" + equipo + "')";

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

	public void delJug(String iddel) {
		query = "DELETE FROM `Jugadores` WHERE ID =" + iddel;

		try {
			Statement stmt = dbconnection.getConexion().createStatement();
			stmt.executeUpdate(query);
			System.out.println("Jugador borrado correctamente");
			stmt.close();
			// principal.refreshTabla();
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
		dbconnection = new DBConnection();
		dbconnection.conexion();

		// this.setPrincipal(principal);

		query = "SELECT * FROM jugadores JOIN equipos on jugadores.ID_Equipo = equipos.ID";
		try {

			if (dbconnection != null) {

				Statement stmt = dbconnection.getConexion().createStatement();

				ResultSet rset = stmt.executeQuery(query);
				//System.out.println(rset2.getString("ID"));
				while (rset.next()) {
					equipo = new Equipo(Integer.parseInt(rset.getString(6)),rset.getString(7));
					jugador = new Jugador(Integer.parseInt(rset.getString(1)), rset.getString(2), rset.getString(3), rset.getString(4),
							equipo);
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

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public void volcar() {

		dbconnection = new DBConnection();
		dbconnection.conexion();

		// this.setPrincipal(principal);

		query = "SELECT * FROM idd.jugadores";

		FileWriter fw;
		try {
			fw = new FileWriter("fichero/jugadores.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			try {

				if (dbconnection != null) {

					Statement stmt = dbconnection.getConexion().createStatement();
					ResultSet rset = stmt.executeQuery(query);

					while (rset.next()) {
						pw.println((rset.getString(1) + "-" +  rset.getString(2) + "-" + rset.getString(3) + "-" + rset.getString(4) + "-" + rset.getString(5)));

					}
					rset.close();
					stmt.close();
					pw.close();

				} else {
					System.out.println("NULO");
				}
			} catch (SQLException s) {
				s.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		

		

	}

}
