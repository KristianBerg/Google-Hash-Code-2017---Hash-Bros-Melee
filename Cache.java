import java.util.ArrayList;

public class Cache {
	int cap, remaining;
	ArrayList<Endpoint> endpoints;
	int[] videoSizes;
	ArrayList<Integer> storedVideos;
	static int nextId = 0;
	int id = 0;

	public Cache(int cap, int[] videoSizes) {
		endpoints = new ArrayList<>();
		this.cap = cap;
		remaining = cap;
		this.videoSizes = videoSizes;
		storedVideos = new ArrayList<>();
		id = nextId;
		nextId++;
	}

	public boolean addVideo(int id) {
		if (videoSizes[id] < remaining) {
			remaining -= videoSizes[id];
			storedVideos.add(id);
			return true;
		}
		return false;
	}

    public ArrayList<Integer> getVideos() {
      return storedVideos;
    }
	public void calcuteMostPopularVideos() {
		int[] requestSum = new int[videoSizes.length];
		for (Endpoint p : endpoints)
			for (int i = 0; i < p.requests.length; ++i)
				requestSum[i] += p.requests[i];
		for(int i = 0; i < videoSizes.length; i++){
			int best = 0;
			for(int k = 0; k < videoSizes.length; k++){
				if(requestSum[k] > requestSum[best]){
					best = k;
				}
			}
			if(!addVideo(best)){
				break;
			}
			requestSum[best] = 0;
		}
	}
	
	public void calcuteMostPopularVideos2() {
		int[] requestSum = new int[videoSizes.length];
		for (Endpoint p : endpoints)
			for (int i = 0; i < p.requests.length; ++i)
				requestSum[i] += p.requests[i];
		
		for(int i = 0; i < videoSizes.length; i++){
			int best = 0;
			for(int k = 0; k < videoSizes.length; k++){
				if(requestSum[k] > requestSum[best]){
					best = k;
				}
			}
			if(!addVideo(best)){
				break;
			} else {
				requestSum[best] = 0;
				for(Endpoint e: endpoints){
					e.requests[best] = 0;
				}
			}
		}
	}

	public void calcuteMostPopularVideos3() {
		double[] requestSum = new double[videoSizes.length];
		for (Endpoint p : endpoints)
			for (int i = 0; i < p.requests.length; ++i)
				if (p.cachelatency[id] != 0)
					requestSum[i] += p.requests[i]*(p.datalatency - p.cachelatency[id]);
		
		for(int i = 0; i < videoSizes.length; i++){
			int best = 0;
			for(int k = 0; k < videoSizes.length; k++){
				if(requestSum[k] > requestSum[best]){
					best = k;
				}
			}
			if(!addVideo(best)){
                requestSum[best] = 0;
			} else {
				requestSum[best] = 0;
				for(Endpoint e: endpoints){
					e.requests[best] = 0;
				}
			}
		}
	}
}
