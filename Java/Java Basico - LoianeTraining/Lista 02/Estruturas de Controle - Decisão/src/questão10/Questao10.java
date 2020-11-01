package questão10;

import java.util.Scanner;

public class Questao10 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		char letra = 'A';
		while (true){
			System.out.println("\nDigite uma letra :");
			letra = scan.nextLine().charAt(0);
			switch (letra) {
			case 'M':System.out.printf("Bom Dia");break;
			case 'm':System.out.printf("Bom Dia");break;
			case 'V':System.out.printf("Bom Tarde");break;
			case 'v':System.out.printf("Bom Tarde");break;			
			case 'N':System.out.printf("Boa Noite");break;
			case 'n':System.out.printf("Boa Noite");break;
			default:System.out.printf("Valor Invalido");break;
			}			
		}		
	}
}
