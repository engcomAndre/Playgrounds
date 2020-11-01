package E009;

import java.util.Scanner;

public class Exer009 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float x;
		while (true) {
			System.out.println("Informe o valor de 'X'");
			x = scan.nextFloat();
			if(x <= 1)System.out.println("F(x) = 1");
			if(x > 1 && x <= 2)System.out.println("F(x) = 2");
			if(x > 2 && x <= 3)System.out.printf("F(x) = %.2f\n",(float)x*x);
			if(x > 3)System.out.printf("F(x) = %.2f\n",(float)x*x*x);
				
		}
	}

}
