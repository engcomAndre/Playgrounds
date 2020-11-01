package questão13;

import java.util.Scanner;//Potencia de uma base sem usar método da linguagem
public class Questao13 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		double base = 0;
		int exp = 0;
		while(true){
			System.out.println("Cálculo de Potência");
			System.out.print("Informe a número da Base:");
			base = new Scanner(System.in).nextInt();
			System.out.print("Informe a número do Expoente:");
			exp = new Scanner(System.in).nextInt();
			System.out.printf("%3.0g elevado a %3.0g é igual a ",base,exp);
			while(exp > 1){
				base =  base*base;
				exp--;
			}
			if(exp == 0)System.out.println("1");
			else System.out.printf("%g\n\n",base);			
		}
	}

}
