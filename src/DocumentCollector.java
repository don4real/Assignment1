import java.io.File;
import java.util.ArrayList;

public class DocumentCollector 
{
	private static String documentPath;
	public static ArrayList<String> getDocNames()
	{
		//System.out.println(System.getProperty("user.dir"));
		documentPath = "src/documents/";
		File folder = new File(documentPath);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> files = new ArrayList<String>();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        //System.out.println(file.getName());
		    	files.add(file.getName());
		    }
		}
		return files;
	}
	public static String getDocumentPath() {
		return documentPath;
	}
	public static void setDocumentPath(String documentPath) {
		DocumentCollector.documentPath = documentPath;
	}
	
}
