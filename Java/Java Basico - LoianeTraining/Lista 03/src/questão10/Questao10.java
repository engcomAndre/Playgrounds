package quest�o10;

import java.util.Scanner;

public class Questao10 {

	public static void main(String[] args) {
		int ini,fim = 0;
		while(true){
			System.out.println("Impress�o entre faixa de valores.");
			System.out.print("Informe o �nicio:");System.out.println("");
			ini = new Scanner(System.in).nextInt();
			System.out.print("Informe o final:");System.out.println("");
			fim = new Scanner(System.in).nextInt();
			while(ini <= fim){
				System.out.print(ini+" ,");
				ini++;
			}
			System.out.println("");
		}
	}

}
