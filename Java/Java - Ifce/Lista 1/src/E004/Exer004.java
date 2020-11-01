package E004;

import java.util.Scanner;

public class Exer004 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		int num = 0;
		while(true){
			System.out.println("Informe um numero:");
			num = scan.nextInt();
			if (num > 20){
				System.out.printf("Numero informado = %d\n",num);
			}			
		}
	}

}
