import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Caching {
	int nVid, nEnd, nReq, nCache, capacity; 
	
	public static void main(String[] args){
		new Caching("me_at_the_zoo.in");
	}
	
	public Caching(String filename){
		Scanner scan = null;
		try {
			scan = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		nVid = scan.nextInt();
		nEnd = scan.nextInt();
		nReq = scan.nextInt();
		nCache = scan.nextInt();
		capacity = scan.nextInt();
		scan.nextLine();
		printSolution("0");
	}
	
	private void printSolution(String solution) {
		try {
			PrintWriter writer = new PrintWriter("caching.txt");
			writer.println(solution);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
