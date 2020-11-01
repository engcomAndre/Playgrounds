package questão01;

import java.util.Scanner;

public class Questao01 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int numA,numB = 0;
		while (true){
			System.out.println("Informe um numero :");
			numA = scan.nextInt();
			System.out.println("Informe outro numero :");
			numB = scan.nextInt();
			if (numA > numB)System.out.println("Maior dos numeros informados é "+numA);
			else System.out.println("Maior dos numeros informados é "+numB);
		}
		
	}
}
