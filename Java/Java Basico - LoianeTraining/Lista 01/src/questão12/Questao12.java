package questão12;

import java.util.Scanner;

public class Questao12 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float altura = 0;
		while(true){
			System.out.println("Informe a Altura :");
			altura = scan.nextFloat();
			System.out.printf("Peso Ideal = %.2f\n",((72.7*altura)-58));
		}
	}
}
