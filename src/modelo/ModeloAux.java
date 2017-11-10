package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vista.Principal;

public class ModeloAux {

	private Principal principal;
	private ModeloAux ModeloAux;
	private AccesoDatos modelo;
	private ArrayList<Jugador> jugador;
	private String dec;
	private DBConnection dbconnection;
	private ArrayList<String> equipos = new ArrayList<String>();


	public void ini(String selec) {

		if (selec.equals("SQL")) {
			modelo = new ModeloSQL();
			
			
			jugador = modelo.Consul();

			for (int i = 0; i < jugador.size(); i++) {
				principal.pintarTabla(jugador.get(i));
			}

		}

		else if(selec.equals("Fichero")) {

			modelo = new ModeloFichero();

			jugador = modelo.Consul();
			System.out.println("el array de jugador: " + jugador);

			for (int i = 0; i < jugador.size(); i++) {
				System.out.println("tuvilllaaaaa");
				principal.pintarTabla(jugador.get(i));
				
			}

		}
		else {
			modelo = new ModeloHibernate();

			jugador = modelo.Consul();
			System.out.println("el array de jugador: " + jugador);

			for (int i = 0; i < jugador.size(); i++) {
				System.out.println("tuvilllaaaaa");
				principal.pintarTabla(jugador.get(i));
				
			}
			
		}
	}

	public void guard(String nombre, String apellido, String posicion, String equipo) {

		dec = principal.getResp();
		System.out.println(dec);
		if (dec.equals("SQL")) {
			modelo.AddPlayer(nombre, apellido, posicion, equipo);
			principal.refreshTabla();
		} else {
			modelo.AddPlayer(nombre, apellido, posicion, equipo);
			principal.refreshTabla();
		}

	}

	public void DelPlayer(String iddel) {
		dec = principal.getResp();
		System.out.println(dec);
		if (dec.equals("SQL")) {
			modelo.DelPlayer(iddel);
			principal.refreshTabla();
		}

	}

	public void DelAll() {

		dec = principal.getResp();
		System.out.println(dec);
		if (dec.equals("SQL")) {
			modelo.DelAll();
			principal.refreshTabla();
		}

	}

	public Principal getVista() {
		return principal;
	}

	public void setVista(Principal vista) {
		this.principal = vista;
	}

	public AccesoDatos getModelo() {
		return modelo;
	}

	public void setModelo(AccesoDatos modelo) {
		this.modelo = modelo;
	}

	public ModeloAux getModeloAux() {
		return ModeloAux;
	}

	public void setModeloAux(ModeloAux modeloAux) {
		ModeloAux = modeloAux;
	}

	public void volcar() {
		modelo.volcar();

	}

	public ArrayList<String> comboEquipos() {
		System.out.println("2");
		dbconnection = new DBConnection();
		dbconnection.conexion();
		String query = "SELECT nombre FROM `equipos`";
	
		try {

			if (dbconnection != null) {

				Statement stmt = dbconnection.getConexion().createStatement();

				ResultSet rset = stmt.executeQuery(query);
				//System.out.println(rset2.getString("ID"));
			   
			    int i=0;
				while (rset.next()) {	
					equipos.add(rset.getString(1));
				i++;
				}
				rset.close();

				stmt.close();

			} else {
				System.out.println("NULO");
			}

		} catch (SQLException s) {
			s.printStackTrace();
		}
		return equipos;
	}
	

}
