import java.util.ArrayList;

public class Cache {
	int cap, remaining;
	ArrayList<Endpoint> endpoints;
	int[] videoSizes;
	ArrayList<Integer> storedVideos;

	public Cache(int cap, int[] videoSizes) {
		endpoints = new ArrayList<>();
		this.cap = cap;
		remaining = cap;
		this.videoSizes = videoSizes;
		storedVideos = new ArrayList<>();
	}

	public boolean addVideo(int id) {
		if (videoSizes[id] < remaining) {
			remaining -= videoSizes[id];
			storedVideos.add(id);
			return true;
		}
		return false;
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
}
