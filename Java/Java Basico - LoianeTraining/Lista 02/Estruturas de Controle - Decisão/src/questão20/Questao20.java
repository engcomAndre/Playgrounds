package questão20;

import java.io.IOException;
import java.util.Scanner;

public class Questao20 {

	public static void main(String[] args) throws IOException{
		Scanner scan = new Scanner(System.in);
		char[] res = {'n','n','n','n','n'};
		while(true){
			System.out.println("Telefonou para a vitima?.");
			System.out.println("'S' ou 's' para sim \n'N' ou 'n' para nao");
			res[0] =  scan.nextLine().charAt(0);			
			
			System.out.println("Esteve no local do crime?.");
			System.out.println("'S' ou 's' para sim \n'N' ou 'n' para nao");
			res[1] =  scan.nextLine().charAt(0);
			
			System.out.println("Mora perto da vitima?.");
			System.out.println("'S' ou 's' para sim \n'N' ou 'n' para nao");
			res[2] =  scan.nextLine().charAt(0);
			
			System.out.println("Devia para a vitima ?.");
			System.out.println("'S' ou 's' para sim \n 'N' ou 'n' para nao");
			res[3] =  scan.nextLine().charAt(0);
			
			System.out.println("Ja trabalhou para a vitima?.");
			System.out.println("'S' ou 's' para sim \n'N' ou 'n' para nao");
			res[4] =  scan.nextLine().charAt(0);
			
			int cont = 0;
			for (char i : res){
				if(i == 'S' || i == 's')cont++;
			}
			if (cont == 5){
				System.out.println("Assassino.");
			}
			else{
				if(cont>=3 && cont <= 4){
					System.out.println("Cumplice.");
				}
				else{
					if(cont == 2){
						System.out.println("Suspeita.");
					}else{
						System.out.println("Inocente.");
					}				
						
				}
			}
		}
	}
}


