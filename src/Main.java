import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sun.org.apache.bcel.internal.generic.DCONST;
import mongodb.MongoDb;
import tf.idf.Term;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> documentNames = DocumentCollector.getDocNames();
		String path = DocumentCollector.getDocumentPath();

		Term term = new Term("dog");
		//	term.setTotalFrequency();
		//	term.setProduct();


		MongoDb mongodb = new MongoDb();

		DB db = mongodb.getDb();

		DBCollection dc = db.getCollection("Term");
		//UNCOMMENT
		BasicDBObject dbo = new BasicDBObject();
		
		TokenCreator fr2 = new TokenCreator();
		
		fr2.tokenize(path, documentNames);


		///// UNCOMMENT TO CREATE THE INDEX ONCE
		//	BasicDBObject query = new BasicDBObject("Term", 1);
		//	dc.ensureIndex(query, "Term", true);
		//////

		////// UNCOMMENT //
	//		dbo.putAll(term);
	//		dc.insert(dbo);	
		//////

		//REMOVE EVERYTHING
	//		dc.remove(dbo);
	//		dc.dropIndex(query);

		DBCursor cursor = dc.find();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

		//FINDING ONE DOCUMENT
		/*BasicDBObject queryy = new BasicDBObject();
		queryy.put("Term", "cat");

		DBCursor cur = dc.find(queryy);
		while(cur.hasNext()) {
		    System.out.println(cur.next());
		}*/
		
		//FINDING OUT IF IT EXISTS...
		/*BasicDBObject queryy = new BasicDBObject();
		queryy.put("Term", "cat");

		DBCursor cur = dc.find(queryy);
		if(cur.hasNext()) {
		    System.out.println(1);
		}*/
		 

		//UPDATING A QUERY

		/*BasicDBObject termQuery = new BasicDBObject();		
		termQuery.put("Term", "cat");
		BasicDBObject frequencyQuery = new BasicDBObject();
		frequencyQuery.append("$set", new BasicDBObject().append("TotalFrequency", 160));
		dc.update(termQuery, frequencyQuery);*/


		//INCREMENTING A QUERY
		/*	BasicDBObject termQuery = new BasicDBObject();		
		termQuery.put("Term", "cat");		
		BasicDBObject frequencyQuery = new BasicDBObject();
		frequencyQuery.append("$inc", new BasicDBObject().append("TotalFrequency", 1));
		dc.update(termQuery, frequencyQuery);*/

		
		//FileReader_VersionTwo fr2 = new FileReader_VersionTwo();

		//fr2.tokenize(path, documentNames);
		//System.out.println(fr2.getTokensDocIDs());
		//System.out.println(fr2.getTokensFrequency());

		//SearchQuery sq = new SearchQuery("a dog a actor", fr2);



	}
}
