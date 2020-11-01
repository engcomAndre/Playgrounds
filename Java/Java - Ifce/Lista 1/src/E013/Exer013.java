package E013;

public class Exer013 {

	public static void main(String[] args) {
		for (int i = 100;i > 0;i--){
			System.out.print(i+",");
			if (i != 100 && i%10 == 0)System.out.println();
		}		
	}
}
