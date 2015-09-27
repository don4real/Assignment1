
public class LinguisticModule {

	public static boolean isValidToken(String s) {
		// TODO Auto-generated method stub
		//Check if word is valid to be a token
		//if not return false
		return false;
	}

	public static String fixToken(String currentWord) {

		currentWord = currentWord.replaceAll("[^a-zA-Z\\d ]", "").toLowerCase(); //"clean" the word, so it has no additional characters and is lowercase

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

		return currentWord;
	}

}
