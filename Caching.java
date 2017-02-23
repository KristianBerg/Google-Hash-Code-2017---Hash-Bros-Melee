import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.StringBuilder;


public class Caching {
	int nVid, nEnd, nReq, nCache, capacity; 
    
    static String[] files = {"example", "kittens", "meatthezoo", "trendingtoday", "videosworthspreading"};
	
	public static void main(String[] args){
      for (String filename : files){
		new Caching(filename);
		System.out.println("done");
      }
	}
	
	public Caching(String filename){
		Cache.nextId = 0;
		Scanner scan = null;
		try {
			scan = new Scanner(new File("data/" + filename + ".in"));
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
		
		int nCachesWithVideo = strategy2(endpoints, caches);
        
        //Before output
        StringBuilder sb = new StringBuilder();
        sb.append("" + nCachesWithVideo);
        for (int i = 0; i < caches.length; ++i){
          sb.append("\n" + i);
          for (int vid : caches[i].getVideos())
            sb.append(" " + vid);
        }
        

		printSolution(sb.toString(), filename);
	}

	private int strategy0(Endpoint[] endpoints, Cache[] caches) {
		//Put this after all input
        int[] requestSum = new int[nVid];
        for (Endpoint p : endpoints)
          for (int i = 0; i < p.requests.length; ++i)
            requestSum[i] += p.requests[i];
        int mostRequestedVideo = 0;
        for (int i = 0; i < requestSum.length; ++i)
          mostRequestedVideo = (requestSum[i] > requestSum[mostRequestedVideo])? i : mostRequestedVideo;
        int nCachesWithVideo = 0;
        for (Cache c : caches) {
          if (c.addVideo(mostRequestedVideo))
            nCachesWithVideo++;
        }
		return nCachesWithVideo;
	}
	
	private int strategy1(Endpoint[] endpoints, Cache[] caches){
		for(Cache c: caches){
			c.calcuteMostPopularVideos3();
		}
		int nCachesWithVideo = 0;
        for (Cache c : caches) {
          if (c.cap != c.remaining)
            nCachesWithVideo++;
        }
		return nCachesWithVideo;
	}
	
	private int strategy2(Endpoint[] endpoints, Cache[] caches){
		int[] magnitude = new int[nCache];
		int i = 0;
		for(Cache c: caches){
			magnitude[i] = c.endpoints.size();
			i++;
		}
		
		for(int j = 0; j < nCache; j++){
			int biggest = Integer.MIN_VALUE;
			for(int k = 0; k < nCache; k++){
				if(magnitude[k] > biggest){
					biggest = k;
				}
			}
			caches[biggest].calcuteMostPopularVideos3();
			magnitude[biggest] = Integer.MIN_VALUE;
		}
		
		int nCachesWithVideo = 0;
        for (Cache c : caches) {
          if (c.cap != c.remaining)
            nCachesWithVideo++;
        }
		return nCachesWithVideo;
	}
	
	
	private void printSolution(String solution, String filename) {
		try {
			PrintWriter writer = new PrintWriter("data/" + filename + ".out");
			writer.print(solution);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

