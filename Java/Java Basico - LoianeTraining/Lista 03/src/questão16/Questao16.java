package questão16;

public class Questao16 {
	public static void main(String[] args) {
		int fibant = 0,fib = 1,aux = 0;
		while(true){
			System.out.println("Série de Fibonacci.");
			System.out.println();
			while(aux < 500){
				System.out.print(aux+" ,");
				aux = fib+fibant;
				fibant = fib;
				fib = aux;								
			}
			return;
		}
	}
}

