package nyc.c4q.capstone;

/**
 * Created by c4q on 3/15/18.
 */

public class Campaign {
  public String title;
  public String creatorName;
  public String imageUri;
  public String goal;

  public Campaign() {

  }

  public Campaign(String title, String userName, String imageUri, String goal) {
    this.title = title;
    this.creatorName = userName;
    this.imageUri = imageUri;
    this.goal = goal;
  }
}
