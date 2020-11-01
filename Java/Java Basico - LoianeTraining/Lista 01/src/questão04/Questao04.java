package questão04;
import java.util.Scanner;

public class Questao04 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float nota1,nota2,nota3,nota4 = 0;
		while (true){
			System.out.println("\nDigite a nota 1 - Negativo para encerrar o programa");
			nota1 = scan.nextInt();
			System.out.printf("Nota 1 = "+nota1);
			if (nota1 < 0)System.exit(0);
			System.out.println("\nDigite a nota 2 - Negativo para encerrar o programa");
			nota2 = scan.nextInt();
			System.out.printf("Nota 2 = "+nota2);
			if (nota2 < 0)System.exit(0);
			System.out.println("\nDigite a nota 3 - Negativo para encerrar o programa");
			nota3 = scan.nextInt();
			System.out.printf("Nota 3 = "+nota3);
			if (nota3 < 0)System.exit(0);
			System.out.println("\nDigite a nota 4 - Negativo para encerrar o programa");
			nota4 = scan.nextInt();
			System.out.printf("Nota 4 = "+nota4);
			if (nota4 < 0)System.exit(0);
			System.out.printf("\n\nA media das notas %.2f,%.2f,%.2f,%.2f é igual a %.2f",nota1,nota2,nota3,nota4,(nota1+nota2+nota3+nota4)/4);			
		}
	}	
}
