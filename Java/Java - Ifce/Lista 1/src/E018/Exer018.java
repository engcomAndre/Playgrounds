package E018;

import java.util.Random;

public class Exer018 {

	public static void main(String[] args) {
		Random randint = new Random();
		int[][] mat = new int[10][10];
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
		for(int i = 0 ;i < mat.length;i++){
			for(int j = 0 ;j < mat.length;j++){
				if(i == j)System.out.printf("%2d ,",mat[i][j]);				
				else{
					System.out.printf("%4s"," ");
				}
			}
			System.out.println();

		}

	}

}
