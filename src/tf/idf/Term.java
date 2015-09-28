package tf.idf;

import java.util.ArrayList;

public class Term {
	private String term;
	private Query query;
	private ArrayList<Document> documents;
	private int product;
	
	public Term(String term){
		query = new Query();
		documents = new ArrayList<Document>();
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
	public ArrayList<Document> getDocument() {
		return documents;
	}
	public void setDocument(ArrayList<Document> documents) {
		this.documents = documents;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Term [term=" + term + ", "
				+ "query=TF: " + query.getTf() + "; DF: " + query.getDf() + "; IDF: " + query.getIdf() + "; W: " + query.getWeightq() 
				+ ", document= " + documents 
				+ ", product=" + product + "]";
	}
	
	
}
