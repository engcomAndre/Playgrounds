package questão04;

import java.util.Scanner;

public class Questao04 {
	public static void main(String[] args) {
		Scanner scan  = new Scanner(System.in);
		char letter = 'a';
		while (true){
			System.out.println("Digite uma Letra + ENTER:");
			letter = scan.next().charAt(0);
			if (letter == 'A' || letter == 'a' || 
				letter == 'e' || letter == 'E' || 
				letter == 'i' || letter == 'I' || 
				letter == 'o' || letter == 'O' || 
				letter == 'u' || letter == 'U'){
				System.out.println("Voce Digitou uma vogal");
			}
			else{				
				System.out.println("Voce Digitou uma Consoante ou numero");				
			}				
		}
	}
}
