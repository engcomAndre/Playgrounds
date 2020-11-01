package questão22;

import java.util.Scanner;

public class Questao22 {
	public static void main(String[] args) {
		while(true){
			System.out.print("Informe a quantidade de cds da coleção : ");
			int qntCDs = new Scanner(System.in).nextInt();
			int i  = 0,somValCd = 0,valCd = 0;
			while(i < qntCDs){
				System.out.printf("Informe o valor do Cd %d : ",i+1);
				somValCd =  somValCd + new Scanner(System.in).nextInt();
				++i;
			}
			System.out.println("Valor Total Investido = "+somValCd);
			System.out.println("Valor Médio Investido = "+(somValCd/qntCDs));
		}
	}
}
