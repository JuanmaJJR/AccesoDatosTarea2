package test;



import controlador.Controlador;
import modelo.DBConnection;
import modelo.Jugador;
import modelo.ModeloAux;
import modelo.ModeloFichero;
import modelo.ModeloSQL;
import modelo.Modelobbdd;
import vista.Principal;

public class Test {
	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Jugador jugador = new Jugador(null, null, null, null, null);
		ModeloSQL modeloSQL = new ModeloSQL();
		DBConnection conexion = new DBConnection();
		ModeloAux modeloaux = new ModeloAux();
		ModeloFichero modelofichero = new ModeloFichero();
		modeloSQL.cargarConfiguracion(conexion.getUser(), conexion.getPwd(), conexion.getDb(), conexion.getHost(), conexion.getPuerto());
		modeloSQL.setDbconnection(conexion);
		conexion.setModelo(modeloSQL);
		
		
		
		controlador.setModelo(modeloaux);
		modeloSQL.setJugador(jugador);
		modelofichero.setJugador(jugador);
		jugador.setModelo(modeloSQL);
		
	
		
		
		Principal principal = new Principal(controlador);
		
		
		modeloaux.setVista(principal);

		
		
		
		
		principal.setModelo(modeloSQL);
		principal.setJugador(jugador);
		
		
		controlador.setVista(principal);
		
		principal.seleccion();
		principal.cargarDatos();
		
		
		principal.setVisible(true);
	
	}

}
