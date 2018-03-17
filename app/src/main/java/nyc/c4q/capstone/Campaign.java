package nyc.c4q.capstone;

/**
 * Created by c4q on 3/15/18.
 */

public class Campaign {

    public String title;
    public String creatorName;
    public String creatorID;
    public String imageUrl;
    public String goal;
    public String summary;
    public String intro;
    public String body;

    public Campaign() {

    }

    public Campaign(String title, String userName, String imageUrl, String goal) {
        this.title = title;
        this.creatorName = userName;
        this.imageUrl = imageUrl;
        this.goal = goal;
    }

}
