package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;


public class ModeloMongo implements AccesoDatos {
	MongoClient mongoClient;
	MongoCollection<Document> collection;
	MongoDatabase db;
	ArrayList<Jugador> jugadoresfin = new ArrayList<Jugador>();
	int contador=0;


	public ModeloMongo() {
		try {
			// PASO 1: Conexión al Server de MongoDB Pasandole el host y el
			// puerto
			mongoClient = new MongoClient("localhost", 27017);

			// PASO 2: Conexión a la base de datos
			db = mongoClient.getDatabase("equipos");
			System.out.println("Conectado a BD MONGO");

		} catch (Exception e) {
			System.out.println("Error leyendo la BD MONGO: " + e.getMessage());
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}
	}

	@Override
	public void AddPlayer(String nombre, String apellido, String posicion, String equipo) {
		// TODO Auto-generated method stub
		BasicDBObject match = new BasicDBObject();

		match.put("nombre", equipo);

		BasicDBObject addressSpec = new BasicDBObject();

		for (int i = 0; i < jugadoresfin.size(); i++) {
			if(contador<jugadoresfin.get(i).getID()) {
				contador = jugadoresfin.get(i).getID();
				System.out.println("contador ahora es: "+contador);
			}
		}	
		contador = contador + 1;
		addressSpec.put("id", contador);
		addressSpec.put("nombre", nombre);
		addressSpec.put("apellido", apellido);

		addressSpec.put("posicion",posicion);

		BasicDBObject update = new BasicDBObject();

		update.put("$push", new BasicDBObject("jugadores", addressSpec));
		collection.updateOne(match, update);
	}

	@Override
	public void DelPlayer(int iddel) {
		collection = db.getCollection("equipos");
		// TODO Auto-generated method stub
		for (int i = 0; i < jugadoresfin.size(); i++) {
			if(iddel==jugadoresfin.get(i).getID()) {
				System.out.println("El jugador es: "+jugadoresfin.get(i).getNombre());
				System.out.println(jugadoresfin);	
				
				
				Document match = new Document();

				match.put("nombre", jugadoresfin.get(i).getEquipo().getNombre());

				Document addressSpec = new Document();
				addressSpec.put("id", iddel);

				Document delete = new Document();
				delete.put("$pull", new BasicDBObject("jugadores", addressSpec));

				collection.updateOne(match, delete);
			}
		}		
	}

	@Override
	public void DelAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Jugador> Consul() {
		try {
			// TODO Auto-generated method stub
			collection = db.getCollection("equipos");

			// PASO 4.2.1: "READ" -> Leemos todos los documentos de la base de
			// datos
			int numDocumentos = (int) collection.count();
			System.out.println("Número de documentos (registros) en la colección depositos: " + numDocumentos + "\n");

			// Busco todos los documentos de la colección, creo el objeto
			// deposito y lo almaceno en el hashmap
			MongoCursor<Document> cursor = collection.find().iterator();

			while (cursor.hasNext()) {
				Document rs = cursor.next();
				List<Document> jugadores = (List<Document>) rs.get("jugadores");
				System.out.println(rs.getString("nombre"));
				Equipo equipotemp = new Equipo(rs.getInteger("id"), rs.getString("nombre"));

				for (Document jugadoresdoc : jugadores) {
					Jugador jugadortemp = new Jugador(jugadoresdoc.getInteger("id"), jugadoresdoc.getString("nombre"),
							jugadoresdoc.getString("apellido"), jugadoresdoc.getString("posicion"), equipotemp);
					jugadoresfin.add(jugadortemp);

				}

				// Una vez creado el deposito con valor de la moneda y cantidad
				// lo metemos en el hashmap
				// System.out.println(cursor.next().toString());
			}
		} catch (Exception ex) {
			System.out.println("Error leyendo la coleccion: no se ha podido acceder a los datos");
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			System.out.println("Fin de la ejecucion del programa");
			System.exit(1);
		}

		System.out.println("Leidos datos de la coleccion de Depositos");

		return jugadoresfin;
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
