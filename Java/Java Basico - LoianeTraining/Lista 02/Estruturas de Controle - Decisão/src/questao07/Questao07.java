package questao07;

import java.util.Scanner;

public class Questao07 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num1,num2,num3 = 0;
		while (true){
			System.out.println("Digite o primeiro numero");
			num1 = scan.nextInt();
			System.out.println("Digite o primeiro numero");
			num2 = scan.nextInt();
			System.out.println("Digite o primeiro numero");
			num3 = scan.nextInt();
			if (num1 >= num2 && num1 >= num3)System.out.printf("O maior e %d\n",num1);
			else{
				if (num2 >= num3)System.out.printf("O maior e %d\n",num2);
				else System.out.printf("O maior e %d\n",num3);
			}
			if (num1 <= num2 && num1 <= num3)System.out.printf("O menor e %d\n",num1);
			else{
				if (num2 <= num3)System.out.printf("O menor e %d\n",num2);
				else System.out.printf("O menor e %d\n",num3);
			}			

		}

	}

}
