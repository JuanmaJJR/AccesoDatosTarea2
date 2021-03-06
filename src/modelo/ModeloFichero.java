package modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ModeloFichero implements AccesoDatos {
	private Jugador jugador;
	private Equipo equipo;
	private DBConnection dbconnection;
	private int eq;

	public DBConnection getDbconnection() {
		return dbconnection;
	}

	public void setDbconnection(DBConnection dbconnection) {
		this.dbconnection = dbconnection;
	}

	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	@Override
	public ArrayList<Jugador> Consul() {
		dbconnection = new DBConnection();
		dbconnection.conexion();

		try (BufferedReader br = new BufferedReader(new FileReader("fichero/jugadores.txt"));
				BufferedReader br2 = new BufferedReader(new FileReader("fichero/equipos.txt"))) {
			StringTokenizer st = null;
			StringTokenizer st2 = null;
			String line;
			String line2;

			while ((line = br.readLine()) != null) {
				ArrayList<String> datos = new ArrayList<String>();
				ArrayList<String> datos2 = new ArrayList<String>();

				int count;
				st = new StringTokenizer(line, "-");
				while (st.hasMoreTokens()) {

					datos.add(String.valueOf(st.nextToken()));

					System.out.println("hola: " + datos);
				}

				while ((line2 = br2.readLine()) != null) {

					st2 = new StringTokenizer(line2, "-");
					while (st2.hasMoreTokens()) {

						datos2.add(String.valueOf(st2.nextToken()));

						System.out.println(datos2);

					}

					if (datos2.get(0).equals(datos.get(4))) {
						System.out.println("entra???");
						equipo = new Equipo(-1, datos2.get(1));
						System.out.println("DENTRO" + equipo);

					}
					System.out.println("eso---->" + datos.get(4));
					
					jugador = new Jugador( Integer.parseInt(datos.get(0)), datos.get(1), datos.get(2), datos.get(3), equipo);
					
					System.out.println("el jugador: " + jugador);
					jugadores.add(jugador);

				}

			}

			System.out.println("arrayJugadores modelo fichero " + jugadores);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jugadores;
	}

	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {

		FileWriter fw;
		try {
			fw = new FileWriter("fichero/jugadores.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("0" + "-" + nombre + "-" + apellido + "-" + posicion + "-" + equipo);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	@Override
	public void DelPlayer(int iddel) {
		// TODO Auto-generated method stub

	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void volcar(String tipo) {

		try (BufferedReader br = new BufferedReader(new FileReader("fichero/jugadores.txt"))) {
			StringTokenizer st = null;
			String line;

			while ((line = br.readLine()) != null) {
				ArrayList<String> datos = new ArrayList<String>();

				int count;
				st = new StringTokenizer(line, "-");
				while (st.hasMoreTokens()) {

					datos.add(String.valueOf(st.nextToken()));

					System.out.println(datos);
				}

				String query = "INSERT INTO `Jugadores` (`ID`, `Nombre`, `Apellido`, `Posicion`, `Equipo`) VALUES (NULL, '"
						+ datos.get(1) + "', '" + datos.get(2) + "', '" + datos.get(3) + "', '" + datos.get(4) + "')";
				String query2 = "DELETE FROM `jugadores`";
				try {
					Statement stmt = dbconnection.getConexion().createStatement();
					// stmt.executeUpdate(query2);
					stmt.executeUpdate(query);
					System.out.println("Jugador a�adido correctamente");
					stmt.close();
					// principal.refreshTabla();

				} catch (SQLException s) {
					s.printStackTrace();
				}

			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	@Override
	public void AddEquipo(String nombre, String descripcion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void escribeTodos(ArrayList<Jugador> jugadores) {
		// TODO Auto-generated method stub
		FileWriter fw;
		for(int x=0;x<jugadores.size();x++) {
			  jugador = jugadores.get(x);
			  equipo = jugador.getEquipo();
			  if(equipo.getNombre().equals("Real Madrid")) {
				  eq = 1;
			  }
			else if(equipo.getNombre().equals("Barcelona")) {
				 eq = 2;
				
				}
			else if(equipo.getNombre().equals("Oporto")) {
				 eq = 3;
				}
		
		try {
			fw = new FileWriter("fichero/jugadores.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println("0" + "-" + jugador.getNombre() + "-" + jugador.getApellido() + "-" + jugador.getPosicion() + "-" + eq);
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}


	@Override
	public void actualizarJugador(int iddel, String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		
	}

}
