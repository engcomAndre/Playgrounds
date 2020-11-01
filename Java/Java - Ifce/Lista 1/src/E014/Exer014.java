package E014;

public class Exer014 {

	public static void main(String[] args) {
		int cont =0;
		for (int i = 1;i < 501;i++){
			if (i % 5 == 0){
				cont++;
				System.out.printf("%03d,",i);
				if (cont == 10){
					System.out.println(" ");
					cont = 0;;
				}
			}
			
		}	

	}

}
