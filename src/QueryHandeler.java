import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.JLabel;

import tf.idf.DocumentRank;
import tf.idf.Term;


public class QueryHandeler {
	private String[] tokenizedQuery;
	private HashMap<String, ArrayList<Double>> euclideanNormalizedTfValues;
	private TreeMap<String, Term> finalMap;
	private int numberOfDocuments;
	private ArrayList<String> documentNames;
	
	public QueryHandeler(TreeMap<String, Term> finalMap, TokenCreator tokenCreator) {
		// TODO Auto-generated constructor stub
		this.finalMap = finalMap;
		this.euclideanNormalizedTfValues = tokenCreator.getEuclideanNormalizedTfValues();
		this.numberOfDocuments = tokenCreator.getNumberOfDocuments();
		this.setDocumentNames(tokenCreator.getDocumentNames());
		
	}
	public ArrayList<DocumentRank> getQueryResults(String query) {
	// TODO Auto-generated method stub
		System.out.println("Method: getQueryResults()");
		tokenizedQuery = tokenizeQuery(query);
		HashMap<String, ArrayList<Double>> products = getProduct(tokenizedQuery);
		return getDocumentScores(products);
	}
	private ArrayList<DocumentRank> getDocumentScores(HashMap<String, ArrayList<Double>> products) {
		// TODO Auto-generated method stub
		System.out.println("Method: getDocumentScores(HashMap<String, ArrayList<Double>> products)");
		ArrayList<DocumentRank> documentRankL = getEmptyDocumentRank();
		String[] productsKeySet = products.keySet().toArray(new String[0]);
		int iLoopLength = products.get(productsKeySet[0]).size();
		for(int i=0;i<iLoopLength;i++){
			ArrayList<Double> value = null;
			DocumentRank documentRank = new DocumentRank();
			documentRank.setName(documentNames.get(i)+" ");
			documentRank.setId(i);
			
			for(int j=0;j<products.size();j++){
				value = products.get(productsKeySet[j]);
				//System.out.println(productsKeySet[j] + value.get(i) + " i: " + i);
				documentRank.setRank(documentRankL.get(i).getRank() + value.get(i));
				documentRank.setRank(Math.round(documentRank.getRank()*100.0)/100.0);
				documentRankL.set(i,documentRank);
				//System.out.println("Doc: "+ documentRank);
			}
		}
		
		documentRankL.sort(null);
		System.out.println("documentScores: "+ documentRankL);
		//System.out.println("" + documentNames);
		return documentRankL;
	}
	private HashMap<String, ArrayList<Double> > getProduct(String[] tQuery) {
		// TODO Auto-generated method stub
		System.out.println("Method: getProduct(String[] tQuery)");
		HashMap<String, ArrayList<Double>> eucNormTfValues = this.euclideanNormalizedTfValues;
		
		HashMap<String, Double> queryWeights = getQueryWeights();
		HashMap<String, ArrayList<Double> > products = new HashMap<String, ArrayList<Double> >();
		
		String[] queryWeightsKeySet = queryWeights.keySet().toArray(new String[0]);
		for(int i=0;i<queryWeightsKeySet.length;i++){
			Double qWtd = queryWeights.get(queryWeightsKeySet[i]);
			ArrayList<Double> dWtds = null;
			if(eucNormTfValues.containsKey(queryWeightsKeySet[i])){
				dWtds = eucNormTfValues.get(queryWeightsKeySet[i]);
			}else{
				dWtds = TokenCreator.createEmptyValuesForTfTable();
			}
			//System.out.println("dWtds: " + queryWeightsKeySet[i] + " " + dWtds);
			ArrayList<Double> prodLine = new ArrayList<Double>();
			
			//Go int the eucNormTable and calc products
			for(int j=0;j<dWtds.size();j++){
				double product = qWtd * dWtds.get(j);
				prodLine.add(product);
			}
			products.put(queryWeightsKeySet[i], prodLine);
		}
		//System.out.println("Products: "+products);
				
		return products;
		
	}
	
	private HashMap<String, Double> getQueryWeights() {
		// TODO Auto-generated method stub
		System.out.println("Method: getQueryWeights()");
		HashMap<String, Double> weightsQuery = new HashMap<String, Double>();
		//Check if the term exists
		
		for(int i=0;i<tokenizedQuery.length;i++){
			if(finalMap.containsKey(tokenizedQuery[i])){
				Term term = finalMap.get(tokenizedQuery[i]);
				weightsQuery.put(tokenizedQuery[i], term.getQuery().getIdf());
			}else
			{
				Term term = finalMap.get(tokenizedQuery[i]);
				weightsQuery.put(tokenizedQuery[i], 0.0);
			}
		}
		//System.out.println("Query Wt,d: " + weightsQuery);
		return weightsQuery;
		
	}
	private String[] tokenizeQuery(String query){
		System.out.println("Method: tokenizeQuery(String query)");
		String[] tokenizedQuery = query.split("\\W");
		System.out.print("\nQuery: ");
		for(int i=0;i<tokenizedQuery.length;i++){
			tokenizedQuery[i] = LinguisticModule.fixToken(tokenizedQuery[i]);
			System.out.print(tokenizedQuery[i] + " ");
		}
		
		System.out.println("\n");
		return tokenizedQuery;
	}
	private ArrayList<DocumentRank> getEmptyDocumentRank() {
		// TODO Auto-generated method stub
		System.out.println("Method: getEmptyDocumentRank()");
		ArrayList<DocumentRank> emptyValues = new ArrayList<DocumentRank>();
		DocumentRank documentRank = new DocumentRank();
		for (int h=0;h<this.numberOfDocuments;h++){
			emptyValues.add(documentRank);
		}
		return emptyValues;
	}
	public ArrayList<String> getDocumentNames() {
		return documentNames;
	}
	public void setDocumentNames(ArrayList<String> documentNames) {
		this.documentNames = documentNames;
	}
	
	
}