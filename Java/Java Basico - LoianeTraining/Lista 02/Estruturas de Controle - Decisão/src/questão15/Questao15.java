package questão15;

import java.util.Scanner;

public class Questao15 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		float[] lad = new float[3];
		while(true){
			for (int i = 0;i < lad.length;i++){
				System.out.printf("Digite o %dº lado do triangulo\n",i+1);
				lad[i] = scan.nextFloat();
			}
			if (((lad[0] + lad[1]) < lad[2]) || ((lad[1] + lad[2]) < lad[0]) || ((lad[0] + lad[2]) < lad[1])){
				System.out.println("Valores informados nao formam um triangulo");	
			}
			else{
				if (lad[0] == lad[1] && lad[0] == lad[2]){
					System.out.println("Triangulo Equilatero");
				}else{
					if (lad[0] == lad[1] || lad[0] == lad[2] || lad[1] == lad[2]){
						System.out.println("Triangulo Isosceles");
					}else{
						if (lad[0] != lad[1] && lad[0] != lad[2] && lad[1] != lad[2] ){
							System.out.println("Triangulo Escaleno");
						}
					}
				}		
			}	
		}
	}
}
