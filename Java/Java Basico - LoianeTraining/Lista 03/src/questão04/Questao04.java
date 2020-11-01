package questão04;

public class Questao04 {
	public static void main(String[] args) {
		double popPaisA = 80000;
		double popPaisB = 200000;
		int ano = 0;
		while(popPaisA <= popPaisB){
			popPaisA = popPaisA*1.03;
			popPaisB = popPaisB*1.015;
			ano++;
		}
		System.out.println("Anos Necessarios para o Pais A alcancar o Pais B em populacao = "+ano+" anos.");
	}
}
