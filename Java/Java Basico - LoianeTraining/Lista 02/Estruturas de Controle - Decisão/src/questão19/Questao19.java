package questão19;

import java.util.Scanner;

public class Questao19 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		double numA,numB = 0;
		int key = 0;
		while(true){
			System.out.println("Informe o 1º numero :");
			numA = scan.nextDouble();
			System.out.println("Informe o 2º numero :");
			numB = scan.nextDouble();
			System.out.println("Informe aperação que deseja realizar:");
			System.out.println("Digite :");
			System.out.println("1 - Soma 	2 - Subtracao");
			System.out.println("3 - Produto     4 - Divisao");
			key = scan.nextInt();
			switch (key) {
			case 1:
				System.out.printf("Somando %.2g e %.2g\n ",numA,numB);
				numA = numA+numB;				
				break;
			case 2:
				System.out.printf("Subtraindo %.2g de %.2g\n ",numA,numB);
				numA = numA-numB;				
				break;
			case 3:
				System.out.printf("Multiplicando %.2g e %.2g\n ",numA,numB);
				numA = numA*numB;				
				break;
			case 4:
				System.out.printf("Dividindo %.2g por %.2g\n",numA,numB);
				numA = numA/numB;				
				break;
			default:
				break;			
			}
			System.out.printf("Resultado %5.2g",numA);
			if(numA % 2 == 0)System.out.print(" e par");
			else System.out.print(" e impar");
			if(numA == 0)System.out.println(" e nulo.");
			else{
				if(numA > 0)System.out.println(" e positivo.");
				else System.out.println(" e negativo.");
			}
		}
	}
}
