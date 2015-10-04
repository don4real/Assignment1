import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import tf.idf.Document;
import tf.idf.Term;

public class SearchQuery {
	String[] terms;
	public SearchQuery(String query,  FileReader_VersionTwo fr2){

		Map<String, Integer> frequencyMap = fr2.getTokensFrequency();
		Map<String, ArrayList<Integer>> tokens = fr2.getTokensDocIDs();
		terms = query.split(" ");
		for (int i=0;i<terms.length;i++){
			terms[i] = LinguisticModule.fixToken(terms[i]);
		}
		HashMap<String, Term> termMap = new HashMap<String, Term>();
		for (int currentTerm = 0;currentTerm<terms.length;currentTerm++){			

			Term term = new Term(terms[currentTerm]);
			//Increase the frequency in the query if the term is already there
			if(termMap.containsKey(terms[currentTerm])){
				term = termMap.get(terms[currentTerm]);
				term.getQuery().setTf(term.getQuery().getTf() + 1);
				termMap.put(terms[currentTerm], term);
			}else{
				//Otherwise set it to 1 
				term.getQuery().setTf(1);
			}
			//get Doc term feq
			//Get DocumentsIDs from a current  term if it exists in the documents, and if it exists only once in the query
			if((tokens.get(terms[currentTerm])!=null)){
				int df = tokens.get(terms[currentTerm]).size();
				//Query Set the Document Frequency and IDF
				term.getQuery().setDf(df);
				term.getQuery().setIdf(fr2.getNumberOfDocuments(), df); //N=Total Docs //log N/df

				//Get the documentIDs to look into
				ArrayList<Integer> documentIDs = tokens.get(terms[currentTerm]);

				//Create Array of Document Objects
				ArrayList<Document> documentIDArray = new ArrayList<Document>();
				//Loop to go through the documents that the term exists in
				int frequency = 0;
				for(int documentNumber = 0; documentNumber<documentIDs.size(); documentNumber++)
				{
					//Create a new document with an ID
					Document document = new Document();
					document.setDocID(documentIDs.get(documentNumber));

					//Get frequencies of current word from current document
					frequency = fr2.getAllDocuments().get(documentIDs.get(documentNumber)).get(terms[currentTerm]);
					
					//Set TF for Document
					document.setTf(frequency);

					//Add the documents to an array
					documentIDArray.add(document);

				}

				//Add array of document IDs to the Term object
				term.setDocument(documentIDArray);
			
				termMap.put(terms[currentTerm], term);
			}
			else
			{
				//Query
				term.getQuery().setDf(0);
				term.getQuery().setIdf(fr2.getNumberOfDocuments(), 0);
				//Doc
				//term.getDocument().setTf(fr2.);
				//fr.readFile(documentNames.get(i), path, i);

				termMap.put(terms[currentTerm], term);
			}

		}
		System.out.println(termMap);
	}
}
