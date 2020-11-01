package questão06;

import java.util.Scanner;

public class Questao06 {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		double raio = 0;
		double pi = 3.1415;
		while(true){
			System.out.println("Digite o raio do Circulo :");
			raio = scan.nextFloat();
			System.out.printf("A area do circulo de raio %.2g e igual a %.2g.\n\n",raio,raio*raio*pi);
		}
	}
}
