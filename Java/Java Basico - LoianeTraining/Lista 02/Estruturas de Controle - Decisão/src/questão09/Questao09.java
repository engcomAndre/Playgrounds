package questão09;

import java.util.Arrays;
import java.util.Scanner;

public class Questao09 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);	
		int[] numVet = new int [3];
		while (true){
			for (int i = 0;i < numVet.length;i++){
				System.out.println("\nDigite um numero:");
				numVet[i] = scan.nextInt();
			}
			Arrays.sort(numVet);
			for (int i = numVet.length-1;i > -1;i--){
				System.out.print(numVet[i]+" ");
			}					
		}
	}
}


