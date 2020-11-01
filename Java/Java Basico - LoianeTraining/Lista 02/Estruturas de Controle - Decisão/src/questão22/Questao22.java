package questão22;

import java.util.Scanner;

public class Questao22 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float quantProd = 0;//Sera usada tambem para calcular o valor total.
		int tpProd = 0;		
		while (true) {
			System.out.println("Informe a tipo do produto;");
			System.out.print("1 - Morango \n2 - Maca\nOpcao:");
			tpProd = scan.nextInt();
			System.out.print("Informe a quantidade em kg:");
			quantProd = scan.nextFloat();
			double valor = 0;
			if (tpProd == 1){
				if(quantProd < 6){
					valor = quantProd*2.5;					
				}
				else{
					if(quantProd >=6){
						valor = quantProd*2.2;
					}
				}
			}
			else{
				if(tpProd == 2){
					if(quantProd < 6){
						valor = quantProd*1.8;
					}
					else {
						if(quantProd >= 6){
							valor = quantProd*1.5;
						}
					}
				}
			}
			if(quantProd > 8 || valor > 25){
				valor = valor - (valor*0.1);
			}
			System.out.printf("Total do pedido = %.2fR$\n",(float)valor);
		}
	}

}
