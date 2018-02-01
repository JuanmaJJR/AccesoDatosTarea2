package controlador;

import java.util.ArrayList;

import modelo.AccesoDatos;
import modelo.Jugador;
import modelo.ModeloAux;
import modelo.ModeloSQL;
import vista.Principal;
import vista.VistaActualizar;
import vista.VistaAnadir;

public class Controlador {
	
	
	private VistaAnadir vistaAnadir;
	private Principal principal;
	private ModeloAux modeloaux;
	private VistaActualizar vistaActualizar;
	

	public VistaAnadir getVistaAnadir() {
		return vistaAnadir;
	}

	public void setVistaAnadir(VistaAnadir vistaAnadir) {
		this.vistaAnadir = vistaAnadir;
	}

	public void cargarDatos(String selec) {
		modeloaux.ini(selec);
	}

	public ModeloAux getModelo() {
		return modeloaux;
	}

	public void setModelo(ModeloAux modeloaux2) {
		this.modeloaux = modeloaux2;
	}

	public Principal getVista() {
		return principal;
	}

	public void setVista(Principal vista) {
		this.principal = vista;
	}

	public void anadirJugador() {
		vistaAnadir = new VistaAnadir();
		vistaAnadir.setModeloaux(modeloaux);
		vistaAnadir.setControlador(this);
		vistaAnadir.cargaEquipos();
		modeloaux.setVista(principal);
		vistaAnadir.setVisible(true);
	}

	public void guardarDatos(String nombre, String apellido, String posicion, String equipo) {
		modeloaux.guard(nombre,apellido,posicion,equipo);
	}

	public void delJug(int iddel) {
		modeloaux.DelPlayer(iddel);
		
	}

	public void delTodo() {
		// TODO Auto-generated method stub
		modeloaux.DelAll();
		
	}

	public void volcar(String tipo) {
		// TODO Auto-generated method stub
		
		modeloaux.volcar(tipo);
		
	}

	public ArrayList<String> comboEquipos() {
		System.out.println("1");
		return modeloaux.comboEquipos();
	}

	public void anadirEquipo(String nombre,String descripcion) {
		// TODO Auto-generated method stub
		modeloaux.anadirEquipo(nombre,descripcion);
		
		
	}

	public void actualizar(int iddel, ArrayList<Jugador> jugadoresfin) {
		// TODO Auto-generated method stub
		vistaActualizar = new VistaActualizar(iddel,jugadoresfin);
		vistaActualizar.setModeloaux(modeloaux);
		vistaActualizar.setControlador(this);
		vistaActualizar.cargaEquipos();
		modeloaux.setVista(principal);
		vistaActualizar.setVisible(true);
		
	}

	public void actualizarDatos(int iddel, String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		modeloaux.actualizarEquipo(iddel,nombre,apellido,posicion,equipo);
		
		
	}
	
}
