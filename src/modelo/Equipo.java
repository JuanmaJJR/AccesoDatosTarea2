package modelo;

public class Equipo {
	private String ID;
	private String nombre;
	
	
	
	public Equipo(String iD, String nombre) {
		ID = iD;
		this.nombre = nombre;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
