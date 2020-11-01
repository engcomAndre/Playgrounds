package questão17;

import java.util.Scanner;

public class Questao17 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int ano = 0;
		while(true){
			System.out.println("Informe um Ano:");
			ano = scan.nextInt();
			if (ano % 4 == 0){
				if(ano > 1582 && ano % 400 == 0)System.out.println("Ano informado nao Bissexto.");
				else System.out.println("Ano informado e Bissexto.");
				}
			else System.out.println("Ano informado nao e Bissexto.");
						
		}
	}
}
