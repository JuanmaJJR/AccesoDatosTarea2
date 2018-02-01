package modelo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import modelo.ApiRequests;


public class ModeloJSON implements AccesoDatos {
	
	private String SERVER_PATH, GET,EquipoID,EquipoNombre;
	private int Team,id,eq;
	private Equipo equipo;
	ApiRequests encargadoPeticiones;
	public ModeloJSON() {
		SERVER_PATH = "http://localhost/pruebasJSON/";
		encargadoPeticiones = new ApiRequests();
		
		
	}
	

	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		System.out.println("go");
        JSONObject jsonObj = new JSONObject();
		if(equipo.equals("Real Madrid")) {
			  eq = 1;
		}
		else if(equipo.equals("Barcelona")) {
			 eq = 2;
		}
		else if(equipo.equals("Oporto")) {
			 eq = 3;
		}

		jsonObj.put("nombre",nombre);
		jsonObj.put("apellidos", apellido);
		jsonObj.put("posicion", posicion);
		jsonObj.put("equipo", eq);

		
		//Creamos una lista para almacenar el JSON
        List  l = new LinkedList();
        l.addAll(Arrays.asList(jsonObj));
        //Generamos el String JSON
        String jsonString = JSONValue.toJSONString(l);
        System.out.println("JSON GENERADO:");
        System.out.println(jsonString);
        System.out.println("");

        try {
            //Codificar el json a URL
            jsonString = URLEncoder.encode(jsonString, "UTF-8");
            //Generar la URL
            String url = SERVER_PATH+"listenPost.php";
            //Creamos un nuevo objeto URL con la url donde queremos enviar el JSON
            URL obj = new URL(url);
            //Creamos un objeto de conexión
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //Añadimos la cabecera
            con.setRequestMethod("POST");
           //on.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            //Creamos los parametros para enviar
            String urlParameters = "json="+jsonString;
            // Enviamos los datos por POST
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            //Capturamos la respuesta del servidor
            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            //Mostramos la respuesta del servidor por consola
            System.out.println(response);
            //cerramos la conexión
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void DelPlayer(int iddel) {
		System.out.println("BORRAR JUGADOR "+iddel);
		// TODO Auto-generated method stub
		 try {
	            //Codificar el json a URL
	            //Generar la URL
	            String url = SERVER_PATH+"deletePost.php";
	            //Creamos un nuevo objeto URL con la url donde queremos enviar el JSON
	            URL obj = new URL(url);
	            //Creamos un objeto de conexión
	            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            //Añadimos la cabecera
	            con.setRequestMethod("POST");
	           //on.setRequestProperty("User-Agent", USER_AGENT);
	            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	            //Creamos los parametros para enviar
	            String id = String.valueOf(iddel);
	            String urlParameters = "id="+id;
	            // Enviamos los datos por POST
	            con.setDoOutput(true);
	            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	            wr.writeBytes(urlParameters);
	            wr.flush();
	            wr.close();
	            //Capturamos la respuesta del servidor
	            int responseCode = con.getResponseCode();
	            System.out.println("\nSending 'POST' request to URL : " + url);
	            System.out.println("Post parameters : " + urlParameters);
	            System.out.println("Response Code : " + responseCode);

	            BufferedReader in = new BufferedReader(
	                    new InputStreamReader(con.getInputStream()));
	            String inputLine;
	            StringBuffer response = new StringBuffer();

	            while ((inputLine = in.readLine()) != null) {
	                response.append(inputLine);
	            }
	            //Mostramos la respuesta del servidor por consola
	            System.out.println(response);
	            //cerramos la conexión
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}

	@Override
	public void DelAll() {
		GET = "deleteAll.php";
		String url = SERVER_PATH + GET; // Sacadas de configuracion
		
		System.out.println("La url a la que lanzamos la petición es " + url);
		
		try {
			String response = encargadoPeticiones.getRequest(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Jugador> Consul() {
		// TODO Auto-generated method stub
		GET = "leeJugadores.php";
		ArrayList<Jugador>Jugadores = new ArrayList<Jugador>();


		
		HashMap<Integer, Equipo> equipos = new HashMap<Integer, Equipo>();
		
		try{
			
		    System.out.println("---------- Leemos datos de JSON --------------------");	
		    
			System.out.println("Lanzamos peticion JSON para jugadores");
			
			String url = SERVER_PATH + GET; // Sacadas de configuracion
			
			System.out.println("La url a la que lanzamos la petición es " + url);
			
			String response = encargadoPeticiones.getRequest(url);
	
			System.out.println(response); // Traza para pruebas
			
			JSONObject respuesta = (JSONObject) JSONValue.parse(response);
			JSONArray jsonArray = (JSONArray) respuesta.get("jugadores");
			
			int i=0;
			 
			for (Object object : jsonArray) {
				JSONObject j = (JSONObject)(object);
				EquipoNombre = j.get("nombreEquipo").toString(); 
				EquipoID = j.get("idEquipo").toString();
				if(equipos.containsKey(Integer.parseInt(EquipoID))) {
					System.out.println("esta pasando por el if");
					this.equipo = equipos.get(Integer.parseInt(EquipoID));
				}
				else {
					this.equipo = new Equipo(Integer.parseInt(EquipoID),EquipoNombre);
					equipos.put(Integer.parseInt(EquipoID), equipo);
				}
				Jugador jugador = new Jugador(Integer.parseInt(j.get("id").toString()),j.get("nombre").toString(),j.get("apellido").toString(),j.get("posicion").toString(),equipo);
				Jugadores.add( jugador);
				i++;
			}
			
			
			
			
			
		}catch(Exception e){
			System.out.println("Ha ocurrido un error en la busqueda de datos");
			
			e.printStackTrace();
			
			System.exit(-1);
		}		
		
		
		return Jugadores;
	}

	@Override
	public void escribeTodos(ArrayList<Jugador> jugadores) {
		// TODO Auto-generated method stub

	}

	@Override
	public void volcar(String tipo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AddEquipo(String nombre, String descripcion) {
		// TODO Auto-generated method stub

	}

}
