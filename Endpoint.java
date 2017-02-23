public class Endpoint {
  int id;
  int[] requests;
  int[] cachelatency;
  int datalatency;

  public Endpoint(int id, int datalatency, int[] requests, int[] cachelatency){
    this.id = id;
    this.requests = requests;
    this.cachelatency = cachelatency;
    this.datalatency = datalatency;
  }


}
