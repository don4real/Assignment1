import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
			if(termMap.containsKey(terms[currentTerm])){
				term = termMap.get(terms[currentTerm]);
				term.getQuery().setTf(term.getQuery().getTf() + 1);
				termMap.put(terms[currentTerm], term);
			}else{
				term.getQuery().setTf(1);
			}
			//get Doc term feq
			if(tokens.get(terms[currentTerm])!=null){
				int df = tokens.get(terms[currentTerm]).size();
				//Query
				term.getQuery().setDf(df);
				term.getQuery().setIdf(fr2.getNumberOfDocuments(), df); //N=Total Docs //log N/df
				//Doc
				term.getDocument().setTf();
				
				termMap.put(terms[currentTerm], term);
			}
			else
			{
				//Query
				term.getQuery().setDf(0);
				term.getQuery().setIdf(fr2.getNumberOfDocuments(), 0);
				//Doc
				term.getDocument().setTf(fr2.);
				//fr.readFile(documentNames.get(i), path, i);
				
				termMap.put(terms[currentTerm], term);
			}
			
			
			
			
		}
		System.out.println(termMap);
	}
}
