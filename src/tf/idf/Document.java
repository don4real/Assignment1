package tf.idf;

public class Document {
	private int tf;
	private int df;
	private int weightq;
	private int docID;
	private int frequency;

	public int getTf() {
		return tf;
	}
	public void setTf(int tf) {
		this.tf = tf;
	}
	public int getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
	}
	public int getWeightq() {
		return weightq;
	}
	public void setWeightq(int weightq) {
		this.weightq = weightq;
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
	public String toString() {
		return String.valueOf(docID);

	}
}
