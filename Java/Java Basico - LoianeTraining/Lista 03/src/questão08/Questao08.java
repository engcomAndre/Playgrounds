package questão08;

import java.util.Scanner;

public class Questao08 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		while(true){
			System.out.println("Informe 5 números.");
			int i = 0,soma = 0;
			while(i < 5){
				System.out.printf("Informe o %dº número:",i+1);
				soma = scan.nextInt() + soma;
				i++;
				System.out.println(" ");				
			}			
			System.out.println("Soma dos números informados = "+soma);
			System.out.println("Média dos números informados = "+(double)soma/i);
		}
	}
}
