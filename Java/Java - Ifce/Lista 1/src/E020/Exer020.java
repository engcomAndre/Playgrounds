package E020;

import java.util.Random;

public class Exer020 {
	public static void main(String[] args) {
		Random randint = new Random();
		int[][] mat = new int[3][3];
		for(int i = 0 ;i < mat.length;i++){
			for(int j = 0 ;j < mat.length;j++){				
				mat[i][j] = randint.nextInt(100);
			}
		}
		for(int i = 0 ;i < mat.length;i++){
			for(int j = 0 ;j < mat.length;j++){
				System.out.printf("%2d ,",mat[i][j]);				
			}
			System.out.println();
		}
		System.out.println();
		for(int i = mat.length-1 ;i > -1;i--){
			for(int j = mat.length-1 ;j > -1;j--){
				System.out.printf("%2d ,",mat[i][j]);				
			}
			System.out.println();
		}
		
	}
}
