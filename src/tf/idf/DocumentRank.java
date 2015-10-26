package tf.idf;

public class DocumentRank implements Comparable<DocumentRank>{
	private int id;
	private String name;
	private double rank;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getRank() {
		return rank;
	}


	public void setRank(double rank) {
		this.rank = rank;
	}
	
	@Override
	public int compareTo(DocumentRank o) {
		// TODO Auto-generated method stub
		
		if(o.getRank() == this.getRank()){
			return 0;
		}
		
		if(o.getRank()>this.getRank()){
			return 1;
		}
		
		return -1;
	}


	@Override
	public String toString() {
		return "DocumentRank [id=" + id + ", name=" + name + ", rank=" + rank
				+ "]\n\t\t";
	}
	
}
