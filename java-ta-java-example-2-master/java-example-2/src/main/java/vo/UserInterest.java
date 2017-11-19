package vo;

public class UserInterest {
	private int interest[]=new int [60];
	public int GetInterest(int index) {
		return interest[index];
	}
	public void SetInterest(int mun,int index) {
		interest [index]=mun;
	}
}
