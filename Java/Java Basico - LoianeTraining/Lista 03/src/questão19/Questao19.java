package questão19;

import java.util.Scanner;

public class Questao19 {
	public static void main(String[] args) {
		while(true){
			System.out.println("Informe o quantidade de notas a inserir:");
			int qntNotas = new Scanner(System.in).nextInt();
			int somNota = 0;
			for(int i = 0; i < qntNotas ; i++){
				System.out.printf("Informe a %dª nota = ",i+1);
				somNota = somNota + new Scanner(System.in).nextInt();
				System.out.println(" ");
			}
			System.out.printf("Média das notas informadas = %3.2g\n",(double)somNota/qntNotas);
		}
	}
}
