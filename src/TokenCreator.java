import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import tf.idf.Document;
import tf.idf.Query;
import tf.idf.Term;

public class TokenCreator 
{
	private static int docID;
	private static int numberOfDocuments;	
	private ArrayList<String> documentNames;
	private static TreeMap<String, Term> allTerms = new TreeMap<String, Term>();
	private static HashMap<String, ArrayList<Double>>  euclideanNormalizedTfValues;
	public TokenCreator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TreeMap<String, Term> tokenize(String path, ArrayList<String> documentNames)
	{
		String fullPath;
		ArrayList<String> file = new ArrayList<String>();
		numberOfDocuments = documentNames.size();
		this.setDocumentNames(documentNames);
		TreeMap<String, Term> finalMap = null;

		for(docID=0; docID<documentNames.size(); docID++)
		{			
			String fileName = documentNames.get(docID);
			fullPath  = path + fileName;
			finalMap = readFile(fullPath);	
			
		}

		setEuclideanNormalizedTfValues(createEuclideanNormalizedTfValues(createTfValuesTable(finalMap)));
		
		return finalMap;

	}
	public static HashMap<String, ArrayList<Double>> createEuclideanNormalizedTfValues(HashMap<String, ArrayList<Double>> tfValuesTable){
		
		HashMap<String, ArrayList<Double>> euclideanNormalizedTfValues = tfValuesTable;
		ArrayList<Double> sqrSumOfDocs = createsqrSumOfDocs(tfValuesTable);
				
		String[] tfValuesTableKeys = euclideanNormalizedTfValues.keySet().toArray(new String[0]);
		for(int i=0;i<tfValuesTableKeys.length;i++){
			 ArrayList<Double> values = euclideanNormalizedTfValues.get(tfValuesTableKeys[i]);
			 for(int j=0;j<values.size();j++){
				 double entf =  values.get(j) / Math.sqrt(sqrSumOfDocs.get(j)); //System.out.println(x + " = " + values.get(j) +" / Sqrt:" +  sqrSumOfDocs.get(j) );
				 values.set(j, entf);
				 euclideanNormalizedTfValues.put(tfValuesTableKeys[i], values);
				 
			 }
		}
		
		System.out.println("\n\n***\n" +"Euclidean Normalized Tf Values Table: \n" + euclideanNormalizedTfValues + "\n***\n\n"); 
		return euclideanNormalizedTfValues;
	}

	private static ArrayList<Double> createsqrSumOfDocs(HashMap<String, ArrayList<Double>> tfValuesTable) {
		// TODO Auto-generated method stub
		String[] tfValuesTableKeys = tfValuesTable.keySet().toArray(new String[0]);
		ArrayList<Double> sqrSumOfDocs = createEmptyValuesForTfTable();
		 
		
		for(int i=0;i<tfValuesTableKeys.length;i++){
			 ArrayList<Double> values = tfValuesTable.get(tfValuesTableKeys[i]);
			 
			 for(int j=0;j<values.size();j++){
				 Double temp = sqrSumOfDocs.get(j);
				 sqrSumOfDocs.set(j, temp + Math.pow(values.get(j),2) );
			 }
		}
		for(int i=0;i<tfValuesTableKeys.length;i++){
			 ArrayList<Double> values = tfValuesTable.get(tfValuesTableKeys[i]);
			 
		}
		
		System.out.println("sqrSumOfDocs: " + sqrSumOfDocs);
		
		return sqrSumOfDocs;
	}

	private static HashMap<String, ArrayList<Double>> createTfValuesTable(TreeMap<String, Term> finalMap) {
		// TODO Auto-generated method stub
		HashMap<String, ArrayList<Double>> tfValuesTable = new HashMap<String,ArrayList<Double>>();
		String[] finalMapKeys = finalMap.keySet().toArray(new String[0]);
		for(int i=0;i<finalMapKeys.length;i++){
			Term term = finalMap.get(finalMapKeys[i]);
			ArrayList<Document> documents = term.getDocuments();
			
			//***
			ArrayList<Double> values = null;
			ArrayList<Double> emptyValues = createEmptyValuesForTfTable();
			//***
			//System.out.println("Documents.Size: "  + documents);
			
			//get the documents and place them in the order of docID in value
			for (int j=0;j<documents.size();j++){
				values = emptyValues;
				Document document = documents.get(j);
				values.set(document.getDocID(), (double) document.getTf());
			}
			
				
			tfValuesTable.put(term.getTerm(), values);
			
		}
		System.out.println("\n\n***\n" +"Tf Values Table: \n" +tfValuesTable + "\n***\n\n");
		return tfValuesTable;
	}

	public static ArrayList<Double> createEmptyValuesForTfTable() {
		// TODO Auto-generated method stub
		ArrayList<Double> emptyValues = new ArrayList<Double>();
		for (int h=0;h<numberOfDocuments;h++){
			emptyValues.add(0.0);
		}
		return emptyValues;
	}

	public static TreeMap<String, Term> readFile(String fullPath)
	{
		ArrayList<String> file = new ArrayList<String>();		

		Scanner d = null;

		try
		{
			//Read the file
			d = new Scanner(new File(fullPath));

			//int i=0;

			//Enter the loop as long as the file is not empty
			while(d.hasNext())
			{
				file.add(d.next()); //put the whole txt file in a array list, so that it keeps all the positions in order
			}

			d.close();

		}

		catch(FileNotFoundException e)
		{
			System.err.println("File has not been found!");			
		} 

		return createPosting(file);
	}

	public static TreeMap<String, Term> createPosting(ArrayList<String> file)
	{
		Map<String, Integer> tokensFrequencyOneDocument  = new TreeMap<String, Integer>();

		for(int i=0; i<file.size(); i++)
		{
			String currentWord = LinguisticModule.fixToken(file.get(i));			

			if ((allTerms.get(currentWord) != null)) //if the token exists then increase frequency and perhaps add a new docID
			{
				//			Integer frequency = tokensFrequency.get(currentWord);	//EXISTS	//gets empty every time accessed
				//			frequency++;	

				//INCREASE THE TOTAL FREQUENCY
				Term term = allTerms.get(currentWord);
				Integer frequency = term.getTotalFrequency();
				frequency++;
				term.setTotalFrequency(frequency);

				//TAKE CARE OF THE DOCUMENT FREQUENCY ETC
				ArrayList<Document> documents = term.getDocuments();

				boolean addNewOne = true;

				for(int y=0; y<documents.size(); y++)
				{
					Document document = documents.get(y);

					//Check if the arraylist contains docID
					if(document.getDocID()==docID)
					{
						int freq = document.getTf();
						freq++;
						document.setTf(freq);

						addNewOne = false;

						documents.set(y, document);
					}
				}

				if(addNewOne==true)
				{
					Document document = new Document();
					document.setDocID(docID);
					document.setTf(1);

					documents.add(document);
				}

				term.setDocuments(documents);
				
				Query query = term.getQuery();
				query.setDf(documents.size());
				query.setIdf(numberOfDocuments, documents.size());		
				term.setQuery(query);
			}

			else // if there are no frequencies yet and no docIDs then create the needed values
			{		

				//CREATE TOTAL FREQUENCY, DOCID AND TF
				Integer frequency = 1;

				ArrayList<Document> documents = new ArrayList<Document>();
				Document document = new Document();
				document.setDocID(docID);
				document.setTf(1);
				documents.add(document);
				
				Query query = new Query();
				query.setDf(documents.size());
				query.setIdf(numberOfDocuments, documents.size());

				Term term = new Term(currentWord);
				term.setTerm(currentWord);
				term.setTotalFrequency(frequency);
				term.setDocuments(documents);
				
				term.setQuery(query);

				allTerms.put(currentWord, term);
			}
		}

		return allTerms;
	}
	
	public HashMap<String, ArrayList<Double>> getEuclideanNormalizedTfValues() {
		return euclideanNormalizedTfValues;
	}

	public static void setEuclideanNormalizedTfValues(
			HashMap<String, ArrayList<Double>> euclideanNormalizedTfValues) {
		TokenCreator.euclideanNormalizedTfValues = euclideanNormalizedTfValues;
	}
	public int getNumberOfDocuments(){
		return numberOfDocuments;
	}

	public ArrayList<String> getDocumentNames() {
		return documentNames;
	}

	public void setDocumentNames(ArrayList<String> documentNames) {
		this.documentNames = documentNames;
	}
	

}
