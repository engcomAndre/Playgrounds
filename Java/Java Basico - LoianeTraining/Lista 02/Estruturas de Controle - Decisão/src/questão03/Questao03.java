package questão03;

import java.util.Scanner;

public class Questao03 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		char letra = 'A';
		while (true){
			System.out.println("\nDigite uma letra :");
			letra = scan.nextLine().charAt(0);
			switch (letra) {
			case 'F':System.out.printf("Feminino");break;
			case 'f':System.out.printf("Feminino");break;
			case 'M':System.out.printf("Masculino");break;
			case 'm':System.out.printf("Masculino");break;
			default:System.out.printf("Sexo Invalido");break;
			}			
		}		
	}
}
