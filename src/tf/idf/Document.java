package tf.idf;

import java.util.ArrayList;

public class Document {
	private int tf;
	private int df;
	private int weightq;
	private int docID;
	private ArrayList<Integer> frequencies;

	public ArrayList<Integer> getFrequencies() {
		return frequencies;
	}
	public void setFrequencies(ArrayList<Integer> frequencies) {
		this.frequencies = frequencies;
	}
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

	@Override
	public String toString() {
		return String.valueOf(docID);

	}
}
