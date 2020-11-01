package questão08;

import java.util.Scanner;

public class Questao08 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float valor,horas = 0;
		while (true){
			System.out.println("Digite quanto voce ganha por hora: - Negativo para sair");
			valor = scan.nextFloat();
			if (valor < 0){System.out.println("Programa Encerrado");System.exit(0);}
			System.out.println("Digite quantas horas  voce trabalhou no mês atual: - Negativo para sair");
			horas = scan.nextFloat();
			if (horas < 0){System.out.println("Programa Encerrado");System.exit(0);}
			System.out.printf("Horas trabalhadas no mês atual: %.2f \nValor Hora Trabalhada: %.2f\nSalario Mes Atual: %.2f \n\n\n",valor,horas,valor*horas);
		}		
	}

}
