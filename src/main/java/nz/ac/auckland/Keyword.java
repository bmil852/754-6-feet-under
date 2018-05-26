package nz.ac.auckland;


public class Keyword {
    int weight;
    public String word;

    public Keyword(String word){
        this.word = word;
        this.weight = 1;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

	public int getWeight() {
		return this.weight;
	}
}
