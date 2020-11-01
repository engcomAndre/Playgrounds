package questão01;

import java.util.Scanner;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class Questao01 {
	public static void main(String[] args) {
		float nota = -1;
		Scanner scan = new Scanner(System.in);
		while(true){
			System.out.println("Informe uma nota entre 0 e 10:");
			while(nota > 10 || nota < 0){
				nota = scan.nextFloat();
				System.out.printf("A nota infomado foi %.2f\n",nota);
			}	
			nota = -1;
		}
	}
}
