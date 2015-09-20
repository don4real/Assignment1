import java.io.File;
import java.util.ArrayList;

public class DocumentCollector 
{
	public static ArrayList<String> getDocNames()
	{
		//System.out.println(System.getProperty("user.dir"));
		
		File folder = new File("src/documents/");
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
	
}
