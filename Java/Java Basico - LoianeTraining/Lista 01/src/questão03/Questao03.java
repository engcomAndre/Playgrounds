package quest�o03;
import java.util.Scanner;

public class Questao03 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int numeroA = 0;
		int numeroB = 0;
		while (numeroA >= 0 || numeroB >= 0){
			System.out.println("Digite o 1� Numero :");
			numeroA = scan.nextInt();
			System.out.println("Digite o 2� Numero :");
			numeroB = scan.nextInt();
			System.out.printf("A soma dos numeros %d e %d � igual a %d.\n\n\n",numeroA,numeroB,numeroA+numeroB);
		}
		System.out.println("Programa Encerrado.");
	}
}
