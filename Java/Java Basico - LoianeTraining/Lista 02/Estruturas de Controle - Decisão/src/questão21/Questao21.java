package questão21;

import java.io.IOException;
import java.util.Scanner;

public class Questao21 {
	public static void main(String[] args) throws IOException{
		Scanner scan =  new Scanner(System.in);
		char tpComb = 'a';
		double quantComb = 0;
		while(true){
			System.out.print("Informe a quantidade de litros vendidos:");
			quantComb = scan.nextDouble();
			
			System.out.println("Informe o tipo de combustivel:");
			System.out.println("'A' ou 'a' para Alcool.");
			System.out.println("'G' ou 'g' para Gasolina.");
			tpComb = scan.next().charAt(0);
			
			
			if(tpComb == 'A' || tpComb == 'a'){
				if(quantComb < 21){
					System.out.printf("Valor a ser pago pelo cliente : %5.2g\n",quantComb*(1.9-(1.9*0.03)));
				}
				else {
					System.out.printf("Valor a ser pago pelo cliente : %5.2g\n",quantComb*(1.9-(1.9*0.05)));				
				}
			}else{
				if(tpComb== 'G' || tpComb == 'g'){
					if(quantComb < 21){
						System.out.printf("Valor a ser pago pelo cliente : %5.2g\n",quantComb*(2.5-(2.5*0.04)));
					}
					else {
						System.out.printf("Valor a ser pago pelo cliente : %5.2g\n",quantComb*(2.5-(2.5*0.06)));				
					}					
				}							
			}
		}
	}
}
