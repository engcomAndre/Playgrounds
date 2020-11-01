package questão14;

import java.util.Scanner;

public class Questao14 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float tamMB,veloc = 0;
		while (true){
			System.out.println("Informe o tamanho do Arquivo em MB - Negativo para sair");
			tamMB = scan.nextFloat();
			if (tamMB < 0){System.out.println("Programa Encerrado");System.exit(0);}
			System.out.println("Informe a velocidade do link em MBps - Negativo para sair");
			veloc = scan.nextFloat();
			if (veloc < 0){System.out.println("Programa Encerrado");System.exit(0);}	
			System.out.printf("Download do Arquivo de %.2f Mbs no link de %.2f MBps em aproximadamente %.2f minutos\n\n",tamMB,veloc,(float)((tamMB/veloc)/60));
		}		
	}

}
