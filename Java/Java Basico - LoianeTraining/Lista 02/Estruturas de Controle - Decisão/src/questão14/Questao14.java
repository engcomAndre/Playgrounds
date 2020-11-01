package questão14;

import java.util.Scanner;

public class Questao14 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		float n1,n2,media = 0;
		while(true){
			System.out.println("Digite a nota 1");
			n1 = scan.nextFloat();
			System.out.println("Digite a nota 2");
			n2 = scan.nextFloat();
			media = (float)(n1+n2)/2;
			System.out.printf("%-7.10s %5.10s %5.10s\n","Media","Conceito","Resultado");
			if (media >= 9 && media <= 10)System.out.printf("%-7.2f %-2.2s %14.10s\n",media,"A","Aprovado");
			if (media >= 7.5 && media < 9)System.out.printf("%-7.2f %-2.2s %14.10s\n",media,"B","Aprovado");
			if (media >= 6 && media < 7.5)System.out.printf("%-7.2f %-2.2s %14.10s\n",media,"C","Aprovado");
			if (media >= 4 && media < 6)System.out.printf("%-7.2f %-8.4s %-14.11s\n",media,"D","Reprovado");
			if (media >= 0 && media < 4)System.out.printf("%-7.2f %-8.4s %-14.11s\n",media,"E","Reprovado");
		}
	}
}
