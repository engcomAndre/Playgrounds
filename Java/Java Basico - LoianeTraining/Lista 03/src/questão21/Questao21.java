package quest�o21;

import java.util.Scanner;

public class Questao21 {
	public static void main(String[] args) {
		int qntTurma = 100;
		while(true){
			while(qntTurma < 0 || qntTurma > 40){
					System.out.print("Informe a quantidade de turmas da unidade: at� 100 turmas");
					qntTurma = new Scanner(System.in).nextInt();
					System.out.println(" ");
					if(qntTurma == -1 || qntTurma > 100)System.out.println("Valor Informado Inv�lido.");
				}
			int i = 0,somAluno = 0,qntAluTurma = -1;
			while(i < qntTurma){
				while(qntAluTurma < 0 || qntAluTurma >40){
					System.out.printf("Informe a quantidade de alunos da %d� turma (at� 40 alunos por turma):",i+1);
					qntAluTurma =  new Scanner(System.in).nextInt();
					if(qntAluTurma < 0 || qntAluTurma >40){
						System.out.println("Quantidade de alunos informada Inv�lida");
						i--;
					}
					somAluno = somAluno + qntAluTurma;
				}
				qntAluTurma = -1;
				i++;				
			}
			System.out.println("M�dia de alunos por turmas : "+somAluno/qntTurma);
			qntTurma = -1;		
		}
	}
}
