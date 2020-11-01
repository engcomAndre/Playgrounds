package questão02;

import java.util.Scanner;

public class Questao02 {
	public static void main(String[] args) {
		Scanner scan =  new Scanner(System.in);
		String login = " ",senha = " ";
		while(true){
			while(login.equals(senha)){
				System.out.println("Informe LOGIN e SENHA");
				System.out.println("Informe login\nLOGIN:");
				login = scan.nextLine();
				System.out.println("Informe Senha\nSENHA:");
				senha = scan.nextLine();
				if(login.equals(senha))
				{
					System.out.println("LOGIN e SENHA são iguais insira novamente");
				}
			}			
		}
	}

}
