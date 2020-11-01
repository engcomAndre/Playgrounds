package questão23;

import java.util.Scanner;

public class Questao23 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int tpProd = 0;
		float quantProd = 0;
		while(true){
			System.out.println("Informe o tipo de carne:");
			System.out.println("1 - File Duplo \n2 - Alcatra \n3 - Picanha \nOpcao:");
			tpProd = scan.nextInt();
			System.out.println("Informe a quantidade em Kg do produto escolhido.");
			quantProd =  scan.nextFloat();
			double valor = 0;
			if(tpProd == 1){
				if(quantProd < 6){
					valor =  quantProd*4.9;
				}
				else{
					valor =  quantProd*5.8;
				}
			}else{
				if(tpProd == 2){
					if(quantProd < 6){
						valor = quantProd*5.9;
					}
					else{
						valor = quantProd*6.8;
					}
				}else{
					if(tpProd == 3){
						if(quantProd < 6){
							valor =  quantProd*6.9;
						}
						else {
							valor = quantProd*7.8;
						}
					}
				}
			}
			int tpPag = 0;
			System.out.println("Informe o tipo de pagamento");
			System.out.println("1 - Cartao Tabajara \n2 - Outros \nOpcao:");
			tpPag  =  scan.nextInt();
			if(tpPag == 1){
				valor = valor - (valor*0.05);				
			}
			System.out.print("Tipo de Carne: ");
			if(tpProd == 1)System.out.println("File Duplo");
			if(tpProd == 2)System.out.println("Alcatra");
			if(tpProd == 3)System.out.println("Picanha");
			System.out.printf("Quantidade : %.02f kg\n",quantProd);
			System.out.printf("Valor total = %.02f\n",(float)valor);
			System.out.print("Tipo de Pagamento:");
			if(tpPag == 1)System.out.println("Cartao Tabajara.");
			else System.out.println("Outros");		
			
		}	
	}
}
