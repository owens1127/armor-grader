
public class Score {
	private double score;
	private double primitiveScore;
	private double yourRarity;
	private double avgRarity;
	private double ratio;

	public Score(double score, double primitiveScore, double yourRarity, double avgRarity, double ratio) {
		this.score = score;
		this.primitiveScore = score;
		this.yourRarity = yourRarity;
		this.avgRarity = avgRarity;
		this.ratio = ratio;
	}

	public double getPrimitiveScore() {
		return primitiveScore;
	}

	public double getYourRarity() {
		return yourRarity;
	}

	public double getAvgRarity() {
		return avgRarity;
	}

	public double getRatio() {
		return ratio;
	}

	public double getScore() {
		return score;
	}

}
