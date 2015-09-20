
public class Posting {
	private int docID;
	private int frequency;
	
	public Posting(int docID, int frequency){
		this.docID = docID;
		this.frequency = frequency;
	}

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
}
