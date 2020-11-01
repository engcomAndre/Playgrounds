package E017;

import java.util.Random;
import java.util.Scanner;

import org.omg.CORBA.NO_IMPLEMENT;

public class Exer017 {
	public static void main(String[] args) {
		Random randint = new Random();
		Scanner scan  = new Scanner(System.in);
		int op = 0;
		int[] Notas = new int[81];
		while(true){
			System.out.println("Informe um Negativo para sair\nPositivo para Continuar:");
			op = scan.nextInt();
			if(op < 0)System.exit(0);
			else{
				for(int i = 0;i < 81;i++){
					Notas[i] = randint.nextInt(11);
				}		
				op = 0;		
				for(int i = 0;i < 11;i++){
					for(int j:Notas){
						if(i == j)op++;
					}
					System.out.printf("Nota: %4d Frequencia absoluta: %4d Frequencia Relativa: %5.03f\n",i,op,op/(float)Notas.length);
					op = 0;
				}
			}			
		}		
	}
}
