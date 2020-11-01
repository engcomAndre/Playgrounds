package E008;

import java.util.Scanner;

public class Exer008 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int idade = 0;
		while(true){
			System.out.println("Informe a Idade do Nadador:");
			idade = scan.nextInt();
			if (idade > 4 && idade <= 7)System.out.println("Categoria : Infantil A");
			if (idade > 7 && idade <= 10)System.out.println("Categoria : Infantil B");
			if (idade > 10 && idade <= 13)System.out.println("Categoria : Juvenil A");
			if (idade > 13 && idade <= 17)System.out.println("Categoria : Juvenil B");
			if (idade > 17)System.out.println("Categoria : Senior");
		}
	}
}
