package modelo;

import java.util.ArrayList;

public interface AccesoDatos {
	
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo);
	public void DelPlayer(String iddel);
	public void DelAll();
	public ArrayList<Jugador> Consul();
	public void volcar();
	public void AddEquipo(String nombre, String descripcion);
	
}