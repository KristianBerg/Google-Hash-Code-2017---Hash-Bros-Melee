import java.util.ArrayList;

public class Cache {
  int cap, remaining;
  Endpoint[] endpoints;
  int[] videoSizes;
  ArrayList<Integer> storedVideos;

  public Cache(int cap, Endpoint[] endpoints, int[] videoSizes){

    this.cap = cap;
    remaining = cap;
    this.endpoints = endpoints;
    this.videoSizes = videoSizes;
    storedVideos = new ArrayList<>();
  }

  public boolean addVideo(int id){
    if (videoSizes[id] < remaining){
      remaining -= videoSizes[id];
      storedVideos.add(id);
      return true;
    }
    return false;
}
