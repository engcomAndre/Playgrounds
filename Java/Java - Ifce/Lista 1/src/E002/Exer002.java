package E002;

import java.util.Scanner;

public class Exer002 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float nota,temp = 0;		
		while(true){
			String nome = "nome";
			nota = 0;
			System.out.print("Informe o nome do aluno:\n");
			nome = scan.next();
			for (int i = 0;i < 5;i++){
				System.out.printf("Informe a nota %d:\n",i);
				temp = scan.nextFloat();
				nota = nota + temp ;				
			}
			System.out.printf("Aluno %s com media = %.2f\n",nome,nota/5);
		}

	}

}
