import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DocumentParser 
{
	static Map<String, Integer> tokensFrequency = new TreeMap<String, Integer>(); //tokens and their frequencies
	static Map<String, ArrayList<Integer>> tokensDocIDs = new TreeMap<String, ArrayList<Integer>>(); //tokens and their docIDs
	
	
	static TreeMap<Integer, Map<String, Integer>> allDocuments = new TreeMap<Integer, Map<String, Integer>>();
	
	static int docID;
	static int numberOfDocuments;
	//string
	
	public DocumentParser() {
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
			
			/*
			 String currentWord = file.get(i).replaceAll("[^a-zA-Z\\d ]", "").toLowerCase(); //"clean" the word, so it has no additional characters and is lowercase
			 
			
			//Stemmer being a little big bugged...?
			Stemmer stemmer = new Stemmer();
			char currentLetter;
			
			for(int y=0; y<currentWord.length(); y++)
			{
				currentLetter = currentWord.charAt(y);
				stemmer.add(currentLetter);
			}
			
			stemmer.stem();
			currentWord = stemmer.toString();	
			
			*/	

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
				Integer frequency = 1;
				tokensFrequency.put(currentWord, frequency); 
				tokensFrequencyOneDocument.put(currentWord, frequency);

				ArrayList<Integer> docIDs = new ArrayList<Integer>();										
				docIDs.add(docID);
				tokensDocIDs.put(currentWord, docIDs);
			}
		}
		//System.out.println("\n\nPRINTING FREQUENCY: " + docID + ": " + tokensFrequencyOneDocument);
		allDocuments.put(docID, tokensFrequencyOneDocument);

	}


	public static Map<String, Integer> getTokensFrequency() {
		return tokensFrequency;
	}


	public static void setTokensFrequency(Map<String, Integer> tokensFrequency) {
		DocumentParser.tokensFrequency = tokensFrequency;
	}


	public static Map<String, ArrayList<Integer>> getTokensDocIDs() {
		return tokensDocIDs;
	}


	public static void setTokensDocIDs(Map<String, ArrayList<Integer>> tokensDocIDs) {
		DocumentParser.tokensDocIDs = tokensDocIDs;
	}


	public static int getDocID() {
		return docID;
	}


	public static void setDocID(int docID) {
		DocumentParser.docID = docID;
	}
	public int getNumberOfDocuments(){
		return numberOfDocuments;
	}

	public static TreeMap<Integer, Map<String, Integer>> getAllDocuments() {
		return allDocuments;
	}

	public static void setAllDocuments(
			TreeMap<Integer, Map<String, Integer>> allDocuments) {
		DocumentParser.allDocuments = allDocuments;
	}

}
