package questão11;

import java.util.Scanner;

public class Questao11 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numA,numB = 0;
		float numC = 0;
		while(true){
			System.out.println("Digite o 1º numero inteiro");
			numA = scan.nextInt();
			System.out.println("Digite o 2º numero inteiro");
			numB = scan.nextInt();
			System.out.println("Digite um numero real");
			numC = scan.nextFloat();			
			System.out.printf("A)"+ numA*numB+"\n");
			System.out.printf("B)"+(3*numA+numC)+"\n");
			System.out.printf("C)"+(numC*numC*numC)+"\n");
		}
	}
}
