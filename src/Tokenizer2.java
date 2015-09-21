import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Tokenizer2 {
	
	private HashMap<String, HashMap<Integer, Integer>> index;
	private int docID;
	private int freq;
	
	public void readDocuments(ArrayList<String> documentNames){
		// TODO Auto-generated method stub
		
		index = new HashMap<String, HashMap<Integer, Integer>>();
		FileReader fr; 
		Scanner scanner;		
		String token;
			
		HashMap<Integer, Integer> postings;
		int frequencyOfPost;
		//docID = 0;
		freq = 0;
		
		try {
			for(int docID=0; docID<documentNames.size(); docID++){

				fr = new FileReader(DocumentCollector.getDocumentPath() + documentNames.get(docID));
				System.out.println("\n\nDocName: " + documentNames.get(docID));
				scanner = new Scanner(fr);
				scanner.useDelimiter("[^a-zA-Z0-9.\\-\']+");
				
				while(scanner.hasNext()) 
				{ 
					token = scanner.next();
					token = tokenize(token);
					postings = new HashMap<Integer, Integer>();
					
					
					
					//System.out.println("Token: " + token + " Postings: " +postings);
					if(index.containsKey(token))
					{
						HashMap<Integer, Integer> posting = index.get(token);
						Integer frequency = 1;
						if( posting.get(docID) != null){
							frequency = posting.get(docID);
							posting.put(docID, frequency++);
							
						}else{
							posting.put(docID, frequency);
						}
						
						postings.put(docID, frequency);
						
					}
					
					else
					{
						postings.put(docID, 1);
					}
					
					
					
					//System.out.println("Token: " + token + " DocID: " + docID + " Fq: " + frequency);
					
					
					
					index.put(token, postings);
				}
				
				scanner.close();
				fr.close();
				System.out.println(index);

			}
			docID++;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(index);

	}

	private static String tokenize(String s) {
		// TODO Auto-generated method stub
		//Check to see if the word is valid to be a token... e.g Countrymen to CountryMan
		
		s= s.toLowerCase();
		
		if(LinguisticModule.isValidToken(s)){
			return s;
		}
		
		//fix the token by changing it
		s = LinguisticModule.fixToken(s);
		
		return s;
	} 
} 

