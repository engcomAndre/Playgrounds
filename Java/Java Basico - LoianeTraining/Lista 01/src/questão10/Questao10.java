package questão10;

import java.util.Scanner;

public class Questao10 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float Cel = 0;
		while(true){
			System.out.println("Digite a Temperatura em Celsius: - Negativo Para Sair");
			Cel = scan.nextFloat();
			if (Cel < 0){System.out.println("Programa Encerrado");System.exit(0);}
			
			System.out.printf("A temperatura de %.2f em Celsius e igual a %.2f Farenheit.\n\n\n",Cel,(9*(Cel+32)/5));
		}
	}

}
