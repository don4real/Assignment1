
public class Posting implements Comparable<Integer>{
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

	@Override
	public int compareTo(Integer o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "DocID: " + this.docID + " - Feq: " + this.frequency;
	}
	
	
}
