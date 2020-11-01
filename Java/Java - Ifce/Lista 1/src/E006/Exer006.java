package E006;

import java.util.Scanner;

public class Exer006 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int num = 0;
		while(true){
			System.out.println("Informe um numero:");
			num = scan.nextInt();
			if (num % 5 == 0){
				System.out.printf("Numero informado = %d e divisivel por 5\n",num);
			}	
			else{
				System.out.printf("Numero informado = %d nao e divisivel por 5\n",num);
			}
		}
	}

}
