import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PracticePizza {

	private final int MUSH = 0, TOM = 1;

	public static void main(String[] args) {
		new PracticePizza("small.in");
	}

	public PracticePizza(String filename){
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int r = scan.nextInt();
		int c = scan.nextInt();
		int min = scan.nextInt();
		int max = scan.nextInt();
		scan.nextLine();
		boolean[][] sliced = new boolean[r][c];
		int[][] ingredient = new int[r][c];
		for(int i = 0; i < r; i++){
			String input = scan.nextLine();
			for(int j = 0; j < c; j++){
				if(input.charAt(j) == 'T'){
					ingredient[i][j] = TOM;  
				} else {
					ingredient[i][j] = MUSH;
				}
			}
		}
		for(int i = 0; i < r-1; i++){
			for(int j = 0; j < c; j++){
				if(ingredient[i+1][j] != ingredient[i][j]){
					int k = i + 1;
					String sol = i + " " + j + " " + k + " " + j;
					printSolution(sol);
					return;
				}
			}
		}
	}

	private void printSolution(String solution) {
		try {
			PrintWriter writer = new PrintWriter("slices.txt");
			writer.println(1);
			writer.println(solution);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
