
public class PWRStudent extends Student{
	protected String indexNO;
	protected int[] scores;
	protected int maxScore; 
	private double dGrade; 
	public double calculateGrade(){
		double dRGrade = 0;
		int a = 0;
		for(int i: scores) {
			a++;
			dRGrade += i;
		}
		dRGrade /= a;
		return dRGrade;
	}
	public PWRStudent(int age, String uni, String indexNo, int[] scores){
		super(age, uni);
		this.indexNO = indexNo;
		this.scores = scores;
		int aScore = scores[0];
		
		for(int i: scores) {
			if(i>aScore) {
				aScore = i;
			}
		}
		maxScore = aScore;
		
	}

}
