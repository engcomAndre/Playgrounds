package E016;

import java.util.Scanner;

public class Exer016 {

	public static void main(String[] args) {
		int[] intVet = new int[50];
		int j = 0;
		for (int i = 1;i < 101;i++){
			if (i % 2 == 0){
				intVet[j] = i;
				j++;			
			}
		}
		for (int i : intVet){
			System.out.printf("%3d ",i);
			if(i % 10 == 0)System.out.println("");
		}

	}

}
