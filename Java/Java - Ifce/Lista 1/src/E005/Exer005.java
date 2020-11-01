package E005;

import java.util.Scanner;

public class Exer005 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int num = 0;
		while(true){
			System.out.println("Informe um numero:");
			num = scan.nextInt();
			if (num % 2 > 0){
				System.out.printf("Numero informado = %d e impar\n",num);
			}	
			else{
				System.out.printf("Numero informado = %d e par\n",num);
			}
		}
	}

}
