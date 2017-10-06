package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static Connection conexion;
	private ModeloSQL modeloSQL;
	private String user;
	private String pwd;
	private String db;
	private String host;
	private String puerto;
	
	public DBConnection(){
		this.cargar();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
			System.out.println("error al buscar el driver mysql");
			e.printStackTrace();
		}
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+db, user,pwd);
			System.out.println("conexion realizada con exito");
		}catch(SQLException e){
			System.out.println("error en la conexion");
			e.printStackTrace();
			
		}
	}
	
	public void conexion() {
		this.cargar();
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		}catch(InstantiationException | IllegalAccessException | ClassNotFoundException e){
			System.out.println("error al buscar el driver mysql");
			e.printStackTrace();
		}
		try{
			conexion=DriverManager.getConnection("jdbc:mysql://"+host+":"+puerto+"/"+db, user,pwd);
			System.out.println("conexion realizada con exito");
		}catch(SQLException e){
			System.out.println("error en la conexion");
			e.printStackTrace();
			
		}
	}
	
	public void cargar() {
		FileInputStream fileIn = null;
		try {
			Properties configProperty = new Properties();
			File file = new File("conf/config.ini");
			fileIn = new FileInputStream(file);
			configProperty.load(fileIn);
			
			user=configProperty.getProperty("user");
			pwd=configProperty.getProperty("pwd");
			db=configProperty.getProperty("db");
			host=configProperty.getProperty("host");
			puerto=configProperty.getProperty("puerto");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ModeloSQL getModelo() {
		return modeloSQL;
	}
	public void setModelo(ModeloSQL modeloSQL) {
		this.modeloSQL = modeloSQL;
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public static void setConexion(Connection conexion) {
		DBConnection.conexion = conexion;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public String getDb() {
		return db;
	}

	public String getHost() {
		return host;
	}

	public String getPuerto() {
		return puerto;
	}
	
}