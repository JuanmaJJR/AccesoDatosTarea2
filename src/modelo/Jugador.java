package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Jugador {
	ModeloSQL modeloSQL;
	private String ID;
	private String nombre;
	private String apellido;
	private String posicion;
	private Equipo equipo;
	
	
	
	public Jugador(String ID, String nombre, String apellido, String posicion, Equipo equipo) {
		
		this.ID= ID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.posicion = posicion;
		this.equipo = equipo;
		
	}



	



	public ModeloSQL getModelo() {
		return modeloSQL;
	}







	public void setModelo(ModeloSQL modeloSQL) {
		this.modeloSQL = modeloSQL;
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



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
	}



	public String getPosicion() {
		return posicion;
	}



	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}







	public Equipo getEquipo() {
		return equipo;
	}







	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}



	




	
	
}


