import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import mongodb.MongoDb;
import tf.idf.Document;
import tf.idf.Query;
import tf.idf.Term;

public class TokenCreator 
{
	static int docID;
	static int numberOfDocuments;
	
	static Map<String, Integer> tokensFrequency = new TreeMap<String, Integer>(); //tokens and their frequencies
	static Map<String, ArrayList<Integer>> tokensDocIDs = new TreeMap<String, ArrayList<Integer>>(); //tokens and their docIDs	
	
	static TreeMap<Integer, Map<String, Integer>> allDocuments = new TreeMap<Integer, Map<String, Integer>>();
	
	static MongoDb mongodb = new MongoDb();
	static DB db = mongodb.getDb();
	static DBCollection dc = db.getCollection("Term");
	static BasicDBObject dbo = new BasicDBObject();
	
	public TokenCreator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static void tokenize(String path, ArrayList<String> documentNames)
	{
		String fullPath;
		ArrayList<String> file = new ArrayList<String>();
		numberOfDocuments = documentNames.size();
		
		for(docID=0; docID<documentNames.size(); docID++)
		{			
			String fileName = documentNames.get(docID);
			fullPath  = path + fileName;

			readFile(fullPath);				
		}
	}
	public static void readFile(String fullPath)
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
		
		createPosting(file);
	}
	
	public static void createPosting(ArrayList<String> file)
	{
		Map<String, Integer> tokensFrequencyOneDocument  = new TreeMap<String, Integer>();
		//Integer frequencyOne = 0;
		
		for(int i=0; i<file.size(); i++)
		{
			String currentWord = LinguisticModule.fixToken(file.get(i));			
			
			if ((tokensFrequency.get(currentWord) != null)) //if the token exists then increase frequency and perhaps add a new docID
			{
				Integer frequency = tokensFrequency.get(currentWord);	//EXISTS	//gets empty every time accessed
				frequency++;			
				
				if(tokensFrequencyOneDocument.get(currentWord)!=null)
				{
					int freqOne = tokensFrequencyOneDocument.get(currentWord) + 1;
					tokensFrequencyOneDocument.put(currentWord, freqOne);
				}
				
				else
				{
					Integer freqOne = 1;
					tokensFrequencyOneDocument.put(currentWord, freqOne);
				}
				
				tokensFrequency.put(currentWord, frequency); //update frequency
				
				

				ArrayList<Integer> docIDs = tokensDocIDs.get(currentWord);  //EXISTS

				//System.out.println(docIDs.get(docIDs.size() - 1) + "   " + docID);

				if((docIDs.get(docIDs.size() - 1)!=docID))
				{
					docIDs.add(docID);

					//System.out.println(docIDs);

					tokensDocIDs.put(currentWord, docIDs);//update docIDs otherwise don't change anything
					
					//allDocuments.put(docID, )
				}
			}

			else // if there are no frequencies yet and no docIDs then create the needed values
			{		
				
				/*private String term;
				private int totalFrequency;
				private Query query;
				private ArrayList<Document> documents;
				private int product;
				*/
				
				//DB
				Term term = new Term(currentWord);				
				
				Integer frequency = 1;
				tokensFrequency.put(currentWord, frequency); 
				tokensFrequencyOneDocument.put(currentWord, frequency);
				//DB
				term.setTotalFrequency(frequency);

				ArrayList<Integer> docIDs = new ArrayList<Integer>();	
				docIDs.add(docID);
				tokensDocIDs.put(currentWord, docIDs);
				
				//DB
				ArrayList<Document> documentsIDs = new ArrayList<Document>();
				Document document = new Document();
				document.setDocID(docID);
				documentsIDs.add(document);
				term.setDocuments(documentsIDs);
				
				dbo.putAll(term);
				dc.insert(dbo);	
			}
		}
		//System.out.println("\n\nPRINTING FREQUENCY: " + docID + ": " + tokensFrequencyOneDocument);
		allDocuments.put(docID, tokensFrequencyOneDocument);
		

	}
	
	
}
