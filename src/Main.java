import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> documentNames = DocumentCollector.getDocNames();
		//DocumentCollector.getDocNames();
		Tokenizer tokenizer = new Tokenizer();
		tokenizer.readDocuments(documentNames);
		
		
		//Search word, document etc
		//HashMap<String, List<Posting>> invertedIndex = new HashMap<String, List<Postring>>();
		
		
	}
}
