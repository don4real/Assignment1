import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Tokenizer {
	private HashMap<String, ArrayList<Posting>> index;
	private int docID;
	
	public void readDocuments(ArrayList<String> documentNames){
		// TODO Auto-generated method stub
		index = new HashMap<String, ArrayList<Posting>>();
		FileReader fr; 
		Scanner scanner;		
		String token;
		ArrayList<Posting> postings;
		int frequencyOfPost;
		docID = 0;
		
		try {
			for(int i=0;i<documentNames.size();i++){

				fr = new FileReader(DocumentCollector.getDocumentPath() + documentNames.get(i));
				System.out.println("DocName: " + documentNames.get(i));
				scanner = new Scanner(fr);
				scanner.useDelimiter("[^a-zA-Z0-9.\\-\']+");
				while(scanner.hasNext()) { 
					token = scanner.next().toLowerCase();
					token = tokenize(token);
					//System.out.println("Token: " + token);
					//Check if the word already exists and increment frequency of the document
					//then replace the value with the new value
					/*if(index.containsKey(token)){
					//System.out.println("Yes");
						postings = index.get(token);
						
						for	(int j = 0;j<postings.size();j++){
							Posting posting = postings.get(j);
							if (posting.getDocID() == i){
								frequencyOfPost = posting.getFrequency();
								postings.remove(j);
								posting.setFrequency(frequencyOfPost + 1);
								posting.setDocID(i);
								postings.add(posting);
							}
							
						}
						index.put(token, postings);
					}*/
					postings = new ArrayList<Posting>();
					postings.add(new Posting(i,1));
					
					//System.out.println("Token: " + token + " Postings: " +postings);
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
		if(LinguisticModule.isValidToken(s)){
			return s;
		}
		
		//fix the token by changing it
		s = LinguisticModule.fixToken(s);
		
		return s;
	} 
} 

