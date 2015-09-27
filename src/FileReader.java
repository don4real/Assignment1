import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class FileReader 
{
	static Map<String, Integer> tokensFrequency = new TreeMap<String, Integer>(); //tokens and their frequencies
	static Map<String, ArrayList<Integer>> tokensDocIDs = new TreeMap<String, ArrayList<Integer>>(); //tokens and their docIDs
	
	
	// Everything
	//static Map<String, Integer> allTokesFrequency = new TreeMap<String, Integer>();
	//static Map<String, ArrayList<Integer>> allTokensDocIDs = new TreeMap<String, ArrayList<Integer>>();
	
	public FileReader() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void readFile(String fileName, String path, Integer docID)
	{	
		
		String fullPath  = path + fileName;
		
		ArrayList<String> file = new ArrayList<String>();
		
		//ArrayList<String> docIDs = new ArrayList<String>();
		//HashMap<Integer, ArrayList<String>> postings = new HashMap<Integer, ArrayList<String>>();
		
				
		Scanner d = null;
		
		try
		{
			//Read the file
			d = new Scanner(new File(fullPath));

			int i=0;
			//Enter the loop as long as the file is not empty
			while(d.hasNext())
			{
				file.add(d.next()); //put the whole txt file in a array list, so that it keeps all the positions in order

				String currentWord = file.get(i).replaceAll("[^a-zA-Z\\d ]", "").toLowerCase(); //"clean" the word, so it has no additional characters and is lowercase				

				if (tokensFrequency.get(currentWord) != null) //if the token exists then increase frequency and perhaps add a new docID
				{
					Integer frequency = tokensFrequency.get(currentWord);	//EXISTS	//gets empty every time accessed
					frequency++;					
					tokensFrequency.put(currentWord, frequency); //update frequency
					
					ArrayList<Integer> docIDs = tokensDocIDs.get(currentWord);  //EXISTS
					
					//System.out.println(docIDs.get(docIDs.size() - 1) + "   " + docID);
					
					if((docIDs.get(docIDs.size() - 1)!=docID))
					{
						docIDs.add(docID);
						
						//System.out.println(docIDs);
						
						tokensDocIDs.put(currentWord, docIDs);//update docIDs otherwise don't change anything
					}
				}

				else // if there are no frequencies yet and no docIDs then create the needed values
				{		
					Integer frequency = 1;
					tokensFrequency.put(currentWord, frequency); 
					
					ArrayList<Integer> docIDs = new ArrayList<Integer>();										
					docIDs.add(docID);
					tokensDocIDs.put(currentWord, docIDs);
				}

				i++;
			}
			
			d.close();

		}

		catch(FileNotFoundException e)
		{
			System.err.println("File has not been found!");			
		} 

		//System.out.println(tokensFrequency);
		//System.out.println(tokensDocIDs);
		
	}

	public static Map<String, Integer> getTokensFrequency() {
		return tokensFrequency;
	}

	public static void setTokensFrequency(Map<String, Integer> tokensFrequency) {
		FileReader.tokensFrequency = tokensFrequency;
	}

	public static Map<String, ArrayList<Integer>> getTokensDocIDs() {
		return tokensDocIDs;
	}

	public static void setTokensDocIDs(Map<String, ArrayList<Integer>> tokensDocIDs) {
		FileReader.tokensDocIDs = tokensDocIDs;
	}
	
	
}
