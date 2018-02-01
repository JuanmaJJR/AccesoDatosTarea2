package modelo;

import java.util.ArrayList;

public interface AccesoDatos {
	
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo);
	public void DelPlayer(int iddel);
	public void DelAll();
	public ArrayList<Jugador> Consul();
	public void escribeTodos (ArrayList<Jugador> jugadores);
	public void volcar(String tipo);
	public void AddEquipo(String nombre, String descripcion);
	public void actualizarJugador(int iddel, String nombre, String apellido, String posicion, String equipo);
	
}