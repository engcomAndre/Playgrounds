package questão18;

import java.util.Scanner;

public class Questao18 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = 0;
		while(true){
			System.out.println("Informe um Numero:");
			num = scan.nextInt();
			if (num % 2 == 0)System.out.println("Numero Informado e par.");
			else System.out.println("Numero Informado e impar.");
		}
	}

}
