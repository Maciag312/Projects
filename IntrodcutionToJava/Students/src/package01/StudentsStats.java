package package01;

public class StudentsStats {
	public int countStudens;
	public double averageScore; 
	public int highestscore;
	public int lowestIndexNo;
	
	public int mcountStudens(Students[] AStud) {
		this.countStudens = AStud.length;
		return AStud.length;
	}
	public double maverageScore(Students[] AStud) {
		int hold = 0; 
		for(int i = 0; i<AStud.length; i++) {
			hold += AStud[i].score;
		}
		this.averageScore =	(double)hold/AStud.length;
		return (double)hold/AStud.length;
	}
	public int mhighestscore(Students[] AStud) {
		int hold = 0;
		for(int i = 0; i<(AStud.length); i++) {
			hold = Math.max(AStud[i].score, hold);
		}
		this.highestscore = hold;
		return hold;
	}
	public int mlowestIndexNo(Students[] AStud) {
		int hold = 999999;
		for(int i = 0; i<(AStud.length); i++) {
			hold = Math.min(AStud[i].indexNo, hold);
		}
		return hold;
	}

}
