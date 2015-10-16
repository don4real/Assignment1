import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import mongodb.MongoDb;
import tf.idf.Term;

public class Main2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> documentNames = DocumentCollector.getDocNames();
		String path = DocumentCollector.getDocumentPath();

		Term term = new Term("test");
		term.setTotalFrequency(500);
		term.setProduct(20);


		MongoDb mongodb = new MongoDb();

		DB db = mongodb.getDb();
		
		DBCollection dc = db.getCollection("Term");
		//UNCOMMENT
		//BasicDBObject dbo = new BasicDBObject();
		
		
		//BasicDBObject query = new BasicDBObject("Term", 1).append("unique",  "true");
		//dc.ensureIndex(query);
		
		///// UNCOMMENT TO CREATE THE INDEX ONCE
		//BasicDBObject query = new BasicDBObject("Term", 1);
		//dc.ensureIndex(query, "Term", true);
		//////
		
		
		//dc.createIndex(query);
		
		//dbo.put(term.getTerm(), term);
		
		////// UNCOMMENT //
		//dbo.putAll(term);
		//dc.insert(dbo);	
		//////
		
		//REMOVE EVERYTHING
		//dc.remove(dbo);
		//dc.dropIndex(query);
		
		DBCursor cursor = dc.find();
		try {
		   while(cursor.hasNext()) {
		       System.out.println(cursor.next());
		   }
		} finally {
		   cursor.close();
		}

		//FileReader_VersionTwo fr2 = new FileReader_VersionTwo();

		//fr2.tokenize(path, documentNames);
		//System.out.println(fr2.getTokensDocIDs());
		//System.out.println(fr2.getTokensFrequency());

		//SearchQuery sq = new SearchQuery("a dog a actor", fr2);



	}
}
