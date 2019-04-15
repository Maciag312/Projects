
public class UWRStudent extends Student {
	String indexNO;
	int[] scores;
	int maxScore; 
	UWRStudent(int age, String uni, String indexNo, int[] scores){
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
