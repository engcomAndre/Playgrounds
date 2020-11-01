package E010;

import java.util.Scanner;

public class Exer010 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float x;
		while (true) {
			System.out.println("Informe o valor de 'X':");
			x = scan.nextFloat();
			System.out.printf("F(x) = %.2f\n",(float)8/(2-x));						
		}
	}

}
