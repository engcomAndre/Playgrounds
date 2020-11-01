package questão07;

import java.util.Scanner;

public class Questao07 {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		double lado = 0;
		while(true){
			System.out.println("Digite o lado do quadrado:");
			lado = scan.nextFloat();
			System.out.printf("A area do quadrado de lado %.2g e igual a %.2g e o dobro da area e %.2g.\n\n",lado,lado*lado,lado*lado*2);
		}
	}
}
