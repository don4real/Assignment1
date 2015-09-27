package tf.idf;

public class Term {
	private String term;
	private Query query;
	private Document document;
	private int product;
	
	public Term(String term){
		query = new Query();
		document = new Document();
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
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
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
				+ ", document="
				+ document + ", product=" + product + "]";
	}
	
	
}
