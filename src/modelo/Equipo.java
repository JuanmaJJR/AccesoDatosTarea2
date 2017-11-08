package modelo;

public class Equipo {
	private int ID;
	private String nombre;
	
	
	
	public Equipo(int iD, String nombre) {
		ID = iD;
		this.nombre = nombre;
	}
	
	public Equipo() {

	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
