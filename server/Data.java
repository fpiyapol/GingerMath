public class Data implements Comparable<Data>{
	
	private String name;
	private int score = 0;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public int compareTo(Data data) {
		return Integer.compare(score, data.getScore());
	}
	
	public String toString() {
		return "name:" + name + "-score:" + score;
	}
	
}
