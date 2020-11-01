package questão03;

import java.util.Scanner;

import javax.swing.plaf.SliderUI;
import javax.swing.plaf.synth.SynthSpinnerUI;

public class Questao03 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		String nome = " ";
		int idade = -1;
		float salario = -1;
		char sexo = 'a';
		char estCivil = 'a';
		while(true){
			while(nome.length() < 4){
				System.out.println("Informe o nome:");
				nome = scan.nextLine();
				if(nome.length() < 4)System.out.println("Nome com tamanho insuficiente.\nInforme Novamente");
			}
			while(idade < 0 || idade > 150){
				System.out.println("Informe a idade:");
				idade = scan.nextInt();
				if(idade < 0 || idade > 150)System.out.println("Idade Invalida - Insira Novamente.");
			}
			while(salario < 0){
				System.out.println("Informe o salario:");
				salario = scan.nextFloat();
				if(salario < 0)System.out.println("Salario Informado Invalido - Informe Novamente");
			}
			while(sexo != 'm' && sexo != 'M' && sexo != 'f' && sexo != 'F' ){
				System.out.println("Informe o sexo:");
				sexo = scan.next().charAt(0);
				if(sexo != 'm' && sexo != 'M' && sexo != 'f' && sexo != 'F')System.out.println("Sexo Informado Invalido - Informe Novamente");
			}
			while(estCivil != 's' && estCivil != 'S' && estCivil != 'C' && estCivil != 'c' && estCivil != 'v' && estCivil != 'V' && estCivil != 'd' && estCivil != 'D' ){
				System.out.println("Informe o Estado Civil:");
				estCivil =  scan.next().charAt(0);
				if(estCivil != 's' && estCivil != 'S' && estCivil != 'C' && estCivil != 'c' && estCivil != 'v' && estCivil != 'V' && estCivil != 'd' && estCivil != 'D' ){
					System.out.println("Estado Civil Informado Invalido");
				}
			}
			System.out.println("Nome = "+nome);nome = " ";
			System.out.println("Idade = "+idade);idade = -1;
			System.out.println("Salario = "+salario);salario = -1;
			System.out.println("Sexo = "+sexo);sexo = 'h';
			System.out.println("Estado Civil = "+estCivil);estCivil = 'q';
			
			
		}		
	}

}
