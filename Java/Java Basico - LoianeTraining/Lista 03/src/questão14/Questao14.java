package questão14;

import java.util.Scanner;

public class Questao14 {
	public static void main(String[] args){

		while(true){
			System.out.println("Informe 10 Números.");
			int cont = 0,num = 0;
			for (int i = 0;i < 10;i++){
				System.out.printf("Informe o %dº número:",i+1);
				num = new Scanner(System.in).nextInt();
				System.out.println("");
				if(num % 2 == 0)cont++;
			}
			System.out.println("Quantidade de Números Pares Informados: "+cont);
			System.out.println("Quantidade de Números Ímpares Informados: "+(10-cont));
		}		
	}
}
