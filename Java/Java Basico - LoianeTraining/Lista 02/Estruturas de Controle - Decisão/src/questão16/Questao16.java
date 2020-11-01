package questão16;

import java.util.Scanner;

public class Questao16 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int a,b,c = 0;
		while(true){
			System.out.println("Informe os Coeficientes da equação de Segundo Grau(ax²+bx+c)");
			System.out.println("Informe o valor do Coeficiente 'a' ");
			a = scan.nextInt();
			if (a == 0){
				System.out.println("Valor de 'a' informado nao corresponde a uma função de 2º grau.");
				System.out.println("Programa Encerrado.");
				System.exit(0);
			}
			System.out.println("Informe o valor do Coeficiente 'b' ");
			b = scan.nextInt();
			System.out.println("Informe o valor do Coeficiente 'c' ");
			c = scan.nextInt();
			int delta = b*b-4*a*c;
			System.out.println(delta);
			if (delta < 0){
				System.out.println("Valor de 'delta' negativo.");
				System.out.println("Programa Encerrado.");
				System.exit(0);			
			}
			double raiz1,raiz2 = 0;
			raiz1 = ((-1*b-Math.sqrt(delta))/2*a);
			raiz2 = ((-1*b+Math.sqrt(delta))/2*a);
			if (raiz1 == raiz2)System.out.printf("Funcao de 2º grau possui um raiz real = %5.3g\n",raiz1);
			else System.out.printf("Funcao de 2º grau possui duas raizes reais = %5.3g e %5.3g\n",raiz1,raiz2);			
		}
	}
}
