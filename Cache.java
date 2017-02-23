public class Cache {
  int cap;
  Endpoint[] endpoints;
  int[] videos;

  public Cache(int cap, Endpoint[] endpoints, int[] videos){

    this.cap = cap;
    this.endpoints = endpoints;
    this.videos = videos;
  }
}
