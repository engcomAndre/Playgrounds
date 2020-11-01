package questão05;

import java.util.Scanner;

public class Questao05 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		float medida = 0;
		while (true) {
			System.out.println("Digite a medida em Metros");
			medida = scan.nextFloat();
			System.out.printf("A medida informada em centimetros e igual %.2f centimetros \n\n",medida*100);
		}		
	}
}
