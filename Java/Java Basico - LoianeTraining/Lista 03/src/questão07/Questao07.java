package questão07;

import java.util.Scanner;

public class Questao07 {

	public static void main(String[] args) {
		int[] vetNum = new int[5];
		while(true){
			System.out.println("Informe 5 números.");
			int i = 0,maior = 0;
			while(i < vetNum.length){
				System.out.printf("Informe o %dº número:",i+1);
				vetNum[i] = new Scanner(System.in).nextInt();
				i++;
				System.out.println(" ");				
			}
			i = 1;
			maior = vetNum[0];
			while(i < vetNum.length){
				if(vetNum[i] > maior)maior = vetNum[i];
				++i;
			}
			System.out.println("Maior dos Cinco Números Informados = "+maior);
		}
	}
}
