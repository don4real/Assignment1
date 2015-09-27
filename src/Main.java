import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> documentNames = DocumentCollector.getDocNames();
		String path = DocumentCollector.getDocumentPath();
		
		//FileReader fr = new FileReader();
	
	/*	for(int i=0; i<documentNames.size(); i++)
		{
			fr.readFile(documentNames.get(i), path, i);
		}
		
		System.out.println(fr.getTokensDocIDs());
		System.out.println(fr.getTokensFrequency());*/
		
		FileReader_VersionTwo fr2 = new FileReader_VersionTwo();
		
		fr2.tokenize(path, documentNames);
		System.out.println(fr2.getTokensDocIDs());
		System.out.println(fr2.getTokensFrequency());
		
	

		SearchQuery sq = new SearchQuery("a cat", fr2);
		//System.out.println(fullPath+documentNames.get(0));
		
		//DocumentCollector.getDocNames();
		//Tokenizer tokenizer = new Tokenizer();
		//tokenizer.readDocuments(documentNames);
		
		
		//Search word, document etc
		//HashMap<String, List<Posting>> invertedIndex = new HashMap<String, List<Postring>>();
		
		
	}
}
