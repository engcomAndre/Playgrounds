package questão02;

import java.util.Scanner;

public class Questao02 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int numA = 0;
		while (true){
			System.out.println("\nInforme um numero :");
			numA = scan.nextInt();			
			if (numA > 0)System.out.printf("O %d é positivo",numA);
			if (numA < 0)System.out.printf("O %d é negativo",numA);
			if (numA == 0)System.out.printf("O %d é nulo",numA);
		}		
	}
}
