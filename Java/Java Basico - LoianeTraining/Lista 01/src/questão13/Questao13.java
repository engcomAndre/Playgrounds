package questão13;

import java.util.Scanner;

public class Questao13 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float valor,horas,salBruto,salLiq,imposRenda,sind,inss = 0;
		while (true){
			System.out.println("Digite quanto voce ganha por hora: - Negativo para sair");
			valor = scan.nextFloat();
			if (valor < 0){System.out.println("Programa Encerrado");System.exit(0);}
			System.out.println("Digite quantas horas  voce trabalhou no mês atual: - Negativo para sair");
			horas = scan.nextFloat();
			if (horas < 0){System.out.println("Programa Encerrado");System.exit(0);}	
			salBruto = valor*horas;					
			sind = (float)(salBruto*0.05);
			inss = (float)(salBruto*0.08);
			imposRenda = (float)(salBruto*0.11);
			salLiq = salBruto-sind-inss;					
			System.out.println("Salario Bruto:"+salBruto);
			System.out.println("INSS:"+inss);
			System.out.println("Sindicato:"+sind);
			System.out.println("Salario Liquido: "+salLiq);



		}		
	}

}
