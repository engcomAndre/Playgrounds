package questão18;
import java.lang.Math;

public class Questao18 {
	public static void main(String[] args) {
		int num = 0;
		while(num < 100){
			System.out.println(num+" =");
			Ehprimo(num);
			num++;			
		}
	}
	static int Ehprimo(int num){
		if(num == 1 || num == 0){
			System.out.println("não primo");
			return 0;
		}
		if(num == 2){
			System.out.println("primo");return 1;
		}
		else{
			if(num % 2 == 0){
				System.out.println("não primo");return 0;
			}
			else{
				int div = 3;
				while(num % div != 0 && div <= Math.sqrt((double)num))div +=2;
				if(div <= Math.sqrt((double)num)){
					System.out.println("não primo");return 0;
				}
				else {
					System.out.println("primo"); return 1;
				}
			}
		}
	}
}
