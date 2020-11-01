package questão05;

import java.util.Scanner;

public class Questao05 {
	public static void main(String[] args){ 
		Scanner scan =  new Scanner(System.in);
		float n1 = 0;
		float n2 = 0;
		while (true){
			System.out.println("\nDigite a nota 1:");
			n1 = scan.nextFloat();
			System.out.println("\nDigite a nota 1:");
			n2 = scan.nextFloat();
			if ((n1+n2)/2 >= 7)System.out.print("Aprovado");
			if ((n1+n2)/2 < 7)System.out.println("Reprovado");
			if ((n1+n2)/2 == 10)System.out.print(" com Distinção");
		}
	}

}
