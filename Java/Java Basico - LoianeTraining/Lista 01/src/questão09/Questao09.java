package questão09;

import java.util.Scanner;

public class Questao09 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float Fare = 0;
		while(true){
			System.out.println("Digite a Temperatura em Farenheit: - Negativo Para Sair");
			Fare = scan.nextFloat();
			if (Fare < 0){System.out.println("Programa Encerrado");System.exit(0);}
			
			System.out.printf("A temperatura de %.2f em Farenheit e igual a %.2f Celsius.\n\n\n",Fare,(5*(Fare-32)/9));
		}
	}
}
