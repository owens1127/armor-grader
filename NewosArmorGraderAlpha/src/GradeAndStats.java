
public class GradeAndStats {
	
	private String letterGrade;
	private Score score;

	public GradeAndStats(String lg, Score scoreObj) {
		letterGrade = lg;
		score =  scoreObj;
	}

	public String getLetterGrade() {
		return letterGrade;
	}
	
	public Score getScore() {
		return score;
	}

}
