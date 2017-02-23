public class Endpoint {
  int id;
  int[] requests;

  public Endpoint(int id, int datalatency, int[] requests, int[] cachelatency){
    this.id = id;
    this.requests = requests;
  }


}
