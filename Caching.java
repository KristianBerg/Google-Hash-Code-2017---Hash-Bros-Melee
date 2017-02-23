import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
		int[] videoSizes = new int[nVid];
		for(int i = 0; i < nVid; i++){
			videoSizes[i] = scan.nextInt();
		}
		
		Endpoint[] endpoints = new Endpoint[nEnd];
		for(int i = 0; i < nEnd; i++){
			int dataLatency = scan.nextInt();
			int connections = scan.nextInt();
			ArrayList<Integer[]> cacheList = new ArrayList<Integer[]>();			
			endpoints[i] = new Endpoint(i, dataLatency, new int[nVid], new int[nCache]);
			for(int k = 0; k < connections; k++){
				int id = scan.nextInt();
				endpoints[i].cachelatency[id] = scan.nextInt(); 
			}
		}
		
		for(int i = 0; i < nReq; i++){
			int vid = scan.nextInt();
			int id = scan.nextInt();
			endpoints[id].requests[vid] = scan.nextInt();
        }


		
		Cache[] caches = new Cache[nCache];
		for(int i = 0; i < nCache; i++){
			caches[i] = new Cache(capacity, videoSizes);
			for(int k = 0; k < nEnd; k++){
				if(endpoints[k].cachelatency[i] > 0){
					caches[i].endpoints.add(endpoints[k]);
				}
			}
		}

        //Put this after all input
        int[] requestSum = new int[cacheSize];
        for (Endpoint p : endpoints)
          for (int i = 0; i < p.requests.length; ++i)
            requestSum[i] += p.requests[i];
        int mostRequestedVideo = 0;
        for (int i = 0; i < requestSum.length; ++i)
          mostRequestedVideo = (requestSum[i] > requestSum[mostRequestedVideo])? i : mostRequestedVideo;
        for (Cache c : caches)
          c.addVideo(i);
        //Before output

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
