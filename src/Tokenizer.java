import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;


public class Tokenizer {
	private HashMap<String, ArrayList<Posting>> index;
	private int docID;
	public void readDocuments(String[] documents) throws IOException {
		// TODO Auto-generated method stub
		index = new HashMap<String, ArrayList<Posting>>();
		FileReader fr = new FileReader("FileReaderDemo.java"); 
		Scanner scanner = new Scanner(fr);
		scanner.useDelimiter("[^a-zA-Z0-9.\\-\']+");
		String token;
		ArrayList<Posting> postings = new ArrayList<Posting>();
		int frequencyOfPost;
		docID = 0;
		for(String s : documents){
			
			while(scanner.hasNext()) { 
				token = scanner.next();
				token = tokenize(token);			
				//Check if the word already exists and increment frequency of the document
				//then replace the value with the new value
				if(index.containsKey(token)){
					postings = index.get(token);
					for	(Posting posting : postings){
						if (posting.getDocID() == docID){
							frequencyOfPost = posting.getFrequency();
							postings.remove(posting);
							posting.setFrequency(frequencyOfPost + 1);
							postings.add(posting);
						}
					}
					index.replace(token, postings);
				}

				postings.add(new Posting(docID, 1));
				index.put(token, postings);
			}
			docID++;
		}
		scanner.close();
		fr.close();
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
