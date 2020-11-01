package questão12;

import java.util.Scanner;

public class Questão12 {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		float jobHour,hour,bruto,ir = 0;
		while (true) {			
			System.out.println("Digite o valor da Hora trabalhada:\n");
			jobHour = scan.nextFloat();
			System.out.println("Digite a quantidade de horas trabalhadas no mes:");
			hour = scan.nextFloat();
			bruto = jobHour*hour;
			if (bruto <= 900)ir = 0;
			if (bruto > 900 && bruto <= 1500)ir = (float) 0.05;
			if (bruto > 1500 && bruto <= 2500) ir = (float)0.1;
			if (bruto > 2500) ir = (float)0.2;
			//System.out.printf("Salario Bruto (%.0f * %.0f) = %.2f",jobHour,hour,(float)bruto);
			System.out.printf("%-1.15s (%.0f * %.0f) %10.0s: R$ %-4.2f\n","Salario Brut ",jobHour,hour," ",(float)bruto);
			System.out.printf("%-1.15s (%.0f%.1s) %20.0s: R$   %-4.2f\n","( - ) IR",ir,"%"," ",(float)bruto*ir);
			System.out.printf("%-1.15s (10%.1s) %17.0s: R$  %-4.2f\n","( - ) INSS","%"," ",(float)bruto*.1);
			System.out.printf("%-1.15s (11%.1s) %23.0s: R$  %-4.2f\n","FGTS","%"," ",(float)bruto*.11);
			System.out.printf("%-1.20s  %14.0s: R$  %-4.2f\n","Total de descontos"," ",(float)bruto*.15);
			System.out.printf("%-1.20s  %17.0s: R$  %-4.2f\n","Salario Liquido"," ",(float)(bruto-bruto*.15));
		}		
	}
}
