package tf.idf;

import com.mongodb.BasicDBObject;
import com.mongodb.ReflectionDBObject;

public class Document extends ReflectionDBObject  {
	private int tf;
	private int wf;
	private int weightq;
	private int docID;
	//private int frequency;

	public int getTf() {
		return tf;
	}
	public void setTf(int tf) {
		this.tf = tf;
	}
	public int getWf() {
		return wf;
	}
	public void setWf(int wf) {
		this.wf = wf;
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
	/*public int getFrequency() {
		return frequency;
	}
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}*/
	
	@Override
	public String toString() {
		return "DocIDs: "  + String.valueOf(docID) + " Fq: " + String.valueOf(tf) + "||";

	}
}
