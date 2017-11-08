package test;



import controlador.Controlador;
import modelo.DBConnection;
import modelo.Equipo;
import modelo.Jugador;
import modelo.ModeloAux;
import modelo.ModeloFichero;
import modelo.ModeloSQL;
import modelo.Modelobbdd;
import vista.Principal;

public class Test {
	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Jugador jugador = new Jugador(-1, null, null, null, null);
		Equipo equipo = new Equipo(-1,null);
		ModeloSQL modeloSQL = new ModeloSQL();
		DBConnection conexion = new DBConnection();
		ModeloAux modeloaux = new ModeloAux();
		ModeloFichero modelofichero = new ModeloFichero();
		modeloSQL.cargarConfiguracion(conexion.getUser(), conexion.getPwd(), conexion.getDb(), conexion.getHost(), conexion.getPuerto());
		modeloSQL.setDbconnection(conexion);
		modelofichero.setDbconnection(conexion);
		conexion.setModelo(modeloSQL);
		
		
		
		controlador.setModelo(modeloaux);
		modeloSQL.setJugador(jugador);
		modeloSQL.setEquipo(equipo);
		modelofichero.setJugador(jugador);
		modelofichero.setEquipo(equipo);
		jugador.setModelo(modeloSQL);
		
	
		
		
		Principal principal = new Principal(controlador);
		
		
		modeloaux.setVista(principal);

		
		
		
		
		principal.setModelo(modeloSQL);
		principal.setJugador(jugador);
		// duda principal.setEquipo(equipo);
		
		
		controlador.setVista(principal);
		
		principal.seleccion();
		principal.cargarDatos();
		
		
		principal.setVisible(true);
	
	}

}
