package questão11;

import java.util.Scanner;

public class Questao11 {
	public static void main(String[] args) {
		int ini,fim = 0;
		while(true){
			System.out.println("Impressão entre faixa de valores.");
			System.out.print("Informe o ínicio:");System.out.println("");
			ini = new Scanner(System.in).nextInt();
			System.out.print("Informe o final:");System.out.println("");
			fim = new Scanner(System.in).nextInt();
			int soma = 0;
			while(ini <= fim){
				soma = soma + ini++;
			}
			System.out.println("Soma da Faixa de Números = "+soma);
		}
	}
}
