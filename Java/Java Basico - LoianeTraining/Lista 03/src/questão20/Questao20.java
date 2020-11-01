package questão20;

import java.util.Scanner;

public class Questao20 {
	public static void main(String[] args) {
		while(true){
			System.out.println("Informe o quantidade de Pessoa com idade a inserir:");
			int qntIdade = new Scanner(System.in).nextInt();
			int somIdade = 0;
			for(int i = 0; i < qntIdade ; i++){
				System.out.printf("Informe a %dª nota = ",i+1);
				somIdade = somIdade + new Scanner(System.in).nextInt();
				System.out.println(" ");
			}
			double media = somIdade/qntIdade;
			if(media >= 0 && media < 26){
				System.out.println("Média de idade das pessoas entre 0 e 25 anos = Jovens.");
			}else{
				if(media >= 26  && media < 61){
					System.out.println("Média de idade das pessoas entre 26 e 60 anos = Adulta.");
				}else{
					System.out.println("Média de idade das pessoas acima de 60 anos = Idoso.");
				}
			}
		}
	}

}
