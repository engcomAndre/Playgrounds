package questão02;
import java.util.Scanner;

public class Questao02 {
	public static void main(String[] args){
		Scanner scan = new Scanner (System.in);
		int numero = 0;
		while (numero >= 0){
			System.out.println("Digite um numero - Negativo para Sair : ");
			numero = scan.nextInt();	
			System.out.println("O numero informado foi :"+ numero);
		}	
		System.out.println("Encerrando Programa");
	}
}