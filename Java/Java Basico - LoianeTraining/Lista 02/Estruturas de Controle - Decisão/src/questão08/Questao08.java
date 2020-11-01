package questão08;

import java.util.Scanner;

public class Questao08 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float prod1,prod2,prod3 = 0;
		while (true){
			System.out.println("Digite o valor primeiro produto:");
			prod1 = scan.nextFloat();
			System.out.println("Digite o valor segundo produto:");
			prod2 = scan.nextFloat();
			System.out.println("Digite o valor terceiro produto:");
			prod3 = scan.nextFloat();			
			if (prod1 <= prod2 && prod1 <= prod3)System.out.printf("Produto comprado de valor %.2f\n",prod1);
			else{
				if (prod2 <= prod3)System.out.printf("Produto comprado de valor %.2f\n",prod2);
				else System.out.printf("Produto comprado de valor %.2f\n",prod3);
			}			

		}

	}

}
