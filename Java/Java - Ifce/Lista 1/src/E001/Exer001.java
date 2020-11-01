package E001;

import java.util.Scanner;

public class Exer001 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float dist,tempo = 0;
		while(true){
			System.out.print("Informe a Distancia:\n");
			dist = scan.nextFloat();
			System.out.print("Informe o tempo:\n");
			tempo = scan.nextFloat();
			System.out.printf("Velocidade media  = %.2f\n",dist/tempo);;
		}

	}

}
