package E015;
import java.util.Scanner;

public class Exer015 {

	public static void main(String[] args) throws InterruptedException {
		Scanner scan =  new Scanner(System.in);
		int nContas;
		System.out.println("Informe o Quantidade de Contas a Inserir:");
		nContas = scan.nextInt();
		int[][] Conta = new int[nContas][2];
		while(true){
			for(int i = 0;i < Conta.length;i++){
				System.out.println("##################################\nInforme o numero da Conta (Negativo para Encerrar):");
				Conta[i][0] = scan.nextInt();
				if(Conta[i][0] < 0){
					nContas = 3;
					while(nContas >= 0){
						System.out.printf("Programa Encerrando em %d\n",nContas);
						Thread.sleep(1000);		
						nContas--;
						}
					System.out.print("Programa Encerrado.");
					System.exit(0);
					
					}
				System.out.printf("Informe o saldo da Conta: (%07d): ",Conta[i][0]);
				Conta[i][1] = scan.nextInt();			
			}
			nContas = 0;
			for(int i = 0;i < Conta.length;i++){
				System.out.printf("Conta: %010d Saldo: %010dR$",Conta[i][0],Conta[i][1]);
				if (Conta[i][1] < 0){
					System.out.println(" - Negativo");
					nContas++;
					}
				else System.out.println(" - Positivo");
				}
			System.out.printf("\nPercentual de Negativados: %.2f\n",(float)(nContas*100/Conta.length));
			}
			
		}	
		
	}


