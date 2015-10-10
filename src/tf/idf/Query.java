package tf.idf;

import com.mongodb.BasicDBObject;
import com.mongodb.ReflectionDBObject;

public class Query extends ReflectionDBObject {
	private int tf;
	private int df;
	private double idf;
	private double weightq;
	
	public int getTf() {
		return tf;
	}
	public void setTf(int tf) {
		this.tf = tf;
		this.weightq = this.tf * this.idf;
	}
	public double getDf() {
		return df;
	}
	public void setDf(int df) {
		this.df = df;
		
	}
	public double getIdf() {
		
		return idf;
	}
	public void setIdf(int n, int df) {
		if (df == 0){
			this.idf = 0.0;
		}else{
			this.idf = Math.log(n * df);
		}		
		this.weightq = this.tf * this.idf;
	}
	public double getWeightq() {
		this.weightq = this.tf * this.idf;
		return this.weightq;
	}
	public void setWeightq(int weightq) {
		this.weightq = weightq;
	}	
}
