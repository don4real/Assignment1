package mongodb;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class MongoDb {
	private MongoClient mongo;
	private DB db;

	public MongoDb(){

		try {
			mongo = new MongoClient( "localhost" , 27017 );
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		setDb(mongo.getDB("SearchEngine"));
		getDatabaseNames();

	}
	public List<String> getDatabaseNames(){
		List<String> dbs = mongo.getDatabaseNames();
		for(String db : dbs){
			System.out.println(db);
		}
		return dbs;
	}
	public DB getDb() {
		return db;
	}
	public void setDb(DB db) {
		this.db = db;
	}
}
