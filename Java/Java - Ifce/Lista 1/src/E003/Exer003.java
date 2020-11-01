package E003;

import java.util.Scanner;

public class Exer003 {

	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		float tem = 0;
		while(true){
			System.out.println("Informe grau em farenheit:");
			tem = scan.nextFloat();
			System.out.printf("Grau em Celsius = %.2f \n",(float)5/9*(tem-32));
		}

	}

}
