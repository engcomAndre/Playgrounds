package questão17;

import java.util.Scanner;

public class Questao17 {
	public static void main(String[] args) {
		int num,fat = 1;
		while(true){
			System.out.println("Cálculo de Fatorial.");
			System.out.println("Informe o número para o fatorial:");
			num = new Scanner(System.in).nextInt();
			System.out.print(num+"! = ");
			while(num > 1){
				System.out.print(num+" X ");
				fat = fat*num--;			
			}
			System.out.println("1 = "+fat);
		}
	}
}
