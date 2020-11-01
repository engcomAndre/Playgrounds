package questão13;

import java.util.Scanner;

public class Questao13 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = 0;
		while(true){
			System.out.println("Digite um numero entre 1 e 7");
			num = scan.nextInt();
			switch (num) {
			case 1:System.out.println("1 - Domingo");break;
			case 2:System.out.println("2 - Segunda");break;
			case 3:System.out.println("3 - Terca");break;
			case 4:System.out.println("4 - Quarta");break;
			case 5:System.out.println("5 - Quinta");break;
			case 6:System.out.println("6 - Sexta");break;
			case 7:System.out.println("7 - Sabado");break;
			default:System.out.println("Valor Invalido");break;
			}
			
		}

	}

}
