package quest�o08;

import java.util.Scanner;

public class Questao08 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		while(true){
			System.out.println("Informe 5 n�meros.");
			int i = 0,soma = 0;
			while(i < 5){
				System.out.printf("Informe o %d� n�mero:",i+1);
				soma = scan.nextInt() + soma;
				i++;
				System.out.println(" ");				
			}			
			System.out.println("Soma dos n�meros informados = "+soma);
			System.out.println("M�dia dos n�meros informados = "+(double)soma/i);
		}
	}
}
