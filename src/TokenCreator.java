import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import mongodb.MongoDb;
import tf.idf.Document;
import tf.idf.Query;
import tf.idf.Term;

public class TokenCreator 
{
	static int docID;
	static int numberOfDocuments;

	//EVERYTHING THAT WILL BE PUT IN MONGODB LATER
	static TreeMap<String, Term> allTerms = new TreeMap<String, Term>();

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

		TreeMap<String, Term> finalMap = null;

		for(docID=0; docID<documentNames.size(); docID++)
		{			
			String fileName = documentNames.get(docID);
			fullPath  = path + fileName;


			finalMap = readFile(fullPath);				
		}
		
		putInMongo(finalMap);

		//System.out.println(finalMap);

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
		//Integer frequencyOne = 0;

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

				Term term = new Term(currentWord);
				term.setTerm(currentWord);
				term.setTotalFrequency(frequency);
				term.setDocuments(documents);

				allTerms.put(currentWord, term);

			}
		}

		return allTerms;


	}

	public static void putInMongo(TreeMap<String, Term> allTerms)
	{
		//System.out.println(allTerms);
//dc.remove(dbo);
		/*
		BasicDBObject query = new BasicDBObject("Term", 1);
		dc.ensureIndex(query, "Term", true);
		
		for(Map.Entry<String, Term> entry : allTerms.entrySet()) 
		{
			String key = entry.getKey();
			Term value = entry.getValue();
			
			//System.out.println(key);
			
			//dbo.put(key, value);
			dbo.putAll(value);
			dc.insert(dbo);	

		}*/
		
		DBCursor cursor = dc.find();
		try {
			while(cursor.hasNext()) {
				System.out.println(cursor.next());
			}
		} finally {
			cursor.close();
		}

	}


}
