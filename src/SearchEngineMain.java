import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import mongodb.MongoDb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import tf.idf.Term;


public class SearchEngineMain {
	private static ArrayList<String> documentNames;
	private static TreeMap<String, Term> finalMap;
	private static TokenCreator tokenCreator;
	private static Gui gui;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoDb mongodb = new MongoDb();
		DB db = mongodb.getDb();
		//initialize();	
		DBCollection coll = db.getCollection("test");
        DBObject record = new BasicDBObject();
        List<HashMap<String,Integer>> persons= new ArrayList<HashMap<String,Integer>>();
        persons.add(new HashMap<String,Integer>());
        record.put("person", persons);
        coll.save(record);
        
        coll = db.getCollection("test");
        
        
	}
	public static void initialize(){
		documentNames = DocumentCollector.getDocNames();
		String path = DocumentCollector.getDocumentPath();
		tokenCreator = new TokenCreator();		
		finalMap = tokenCreator.tokenize(path, documentNames);
		gui = new Gui(finalMap,tokenCreator); 
	}
}
