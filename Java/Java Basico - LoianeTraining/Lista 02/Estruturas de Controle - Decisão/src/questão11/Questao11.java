package questão11;

import java.util.Scanner;

public class Questao11 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		float sal = 0;
		while(true){
			System.out.println("Digite o salario do Servidor:");
			sal =  scan.nextFloat();
			System.out.printf("Salario Bruto Atual = %.2f \n",sal);
			if (sal > 0 && sal <= 280){
				System.out.print("Percentual Aplicado = 20%\n");
				System.out.printf("Valor do Aumento = %.2f\n",(sal*.2));
				System.out.printf("Novo Salario  = %.2f\n",sal*1.2);				
			}
			if (sal > 280 && sal <= 700){
				System.out.print("Percentual Aplicado = 15%\n");
				System.out.printf("Valor do Aumento = %.2f\n",(sal*.15));
				System.out.printf("Novo Salario  = %.2f\n",sal*1.15);			
			}
			if (sal > 700 && sal <= 1500){
				System.out.print("Percentual Aplicado = 10%\n");
				System.out.printf("Valor do Aumento = %.2f\n",(sal*.1));
				System.out.printf("Novo Salario  = %.2f\n",sal*1.1);				
			}
			if (sal > 1500){
				System.out.print("Percentual Aplicado = 5%\n");
				System.out.printf("Valor do Aumento = %.2f\n",(sal*.05));
				System.out.printf("Novo Salario  = %.2f\n",sal*1.05);				
			}
		}
	}
}
