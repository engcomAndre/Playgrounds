package quest�o15;

import java.util.Scanner;

public class Questao15 {
	public static void main(String[] args) {
		int fibant = 0,fib = 1,aux = 0,numTerm = 0;
		while(true){
			System.out.println("S�rie de Fibonacci.");
			System.out.println();
			System.out.print("Informe a quantidade de termos da S�rie de Fibonnacci:");
			numTerm =  new Scanner(System.in).nextInt();
			while(numTerm > 0){
				System.out.print(aux+" ,");
				aux = fib+fibant;
				fibant = fib;
				fib = aux;								
				numTerm--;
			}
			System.out.println("");
		}
	}
}
