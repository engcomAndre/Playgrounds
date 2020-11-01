package questão12;

import java.util.Scanner;

public class Questao12 {
	public static void main(String[] args) {
		int num = 0, op = 0;
		while(true){
			System.out.print("Informe o número do qual deseja a tabuada:");
			System.out.println("");
			op = new Scanner(System.in).nextInt();
			System.out.println("Tabuada de "+op+":");
			while(num < 11){
				System.out.printf("%2d X %2d =%3d\n",op,num,num*op);
				num++;
			}
			num = 0;
		}
	}
}
