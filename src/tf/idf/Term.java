package tf.idf;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.ReflectionDBObject;

public class Term extends ReflectionDBObject {
	private String term;
	private int totalFrequency;
	private Query query;
	private ArrayList<Document> documents;
	private int product;


	public Term(String term){
	//	query = new Query();
		//documents = new ArrayList<Document>();
		this.term = term;
	}

	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Query getQuery() {
		return query;
	}
	public void setQuery(Query query) {
		this.query = query;
	}

	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}

	public int getTotalFrequency() {
		return totalFrequency;
	}

	public void setTotalFrequency(int totalFrequency) {
		this.totalFrequency = totalFrequency;
	}

	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}

	/*@Override
	public String toString() {
		return "Term [term=" + term + ", "
				+ "query=TF: " + query.getTf() + "; DF: " + query.getDf() + "; IDF: " + query.getIdf() + "; W: " + query.getWeightq() 
				+ ", document= " + documents.toString() 
				+ ", product=" + product + "]";

	}*/
	
	@Override
	public String toString() {	
		return "Term [term=" + term + ", TotalFrequency " + totalFrequency + ", Documents " + documents +"]\n\n";

	}


}
