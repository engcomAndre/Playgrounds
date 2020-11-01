package E007;

import java.util.Scanner;

public class Exer007 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int [] num = new int[3];
		int temp = 0;
		while(true){
			for (int i =0;i < num.length;i++){
				System.out.printf("Informe o %dº numero",i+1);
				System.out.println(" ");
				num[i] = scan.nextInt();				
			}
			//BubleSort
			for (int i = 0;i < num.length-1;i++){
				
				for (int j = 1;j < num.length;j++){
					if (num[i] >= num[j]){
						temp = num[i];
						num[i] = num[j];
						num[j] = temp;
					}		
				}		
			}
			System.out.printf("Numeros de Entrada Ordenados: %d,%d,%d\n",num[0],num[1],num[2]);			
		}		

	}

}
