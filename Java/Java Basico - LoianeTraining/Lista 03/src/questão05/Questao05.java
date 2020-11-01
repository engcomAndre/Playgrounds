package questão05;

import java.util.Scanner;

public class Questao05 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		double popPaisA = 0;
		double popPaisB = 0;
		int ano = 0;
		while(true){
			while(popPaisA <= 0){
				System.out.print("Informe a Populacao do Pais A:");
				popPaisA = scan.nextDouble();
				System.out.println(" ");
				if(popPaisA <= 0)System.out.println("Valor Invalido - Informe Novamente.");
			}
			while(popPaisB <= 0){
				System.out.print("Informe a Populacao do Pais B:");
				popPaisB = scan.nextDouble();
				System.out.println(" ");
				if(popPaisB <= 0)System.out.println("Valor Inválido - Informe Novamente.");
			}			
			
			while(popPaisA < popPaisB){
				popPaisA = popPaisA*1.03;
				popPaisB = popPaisB*1.015;
				ano++;
			}
			System.out.println("Anos Necessarios para o Pais A alcancar o Pais B em populacao = "+ano+" anos.");
			popPaisA = popPaisB = 0;
		}
	}
}
			
