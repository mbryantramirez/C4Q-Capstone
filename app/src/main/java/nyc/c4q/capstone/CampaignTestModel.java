package nyc.c4q.capstone;

/**
 * Created by c4q on 3/17/18.
 */

public class CampaignTestModel {
    private String title;
    private String goal;
    private String imageUrl;
    private String creator;

    public CampaignTestModel() {
    }

    public String getTitle() {
        return title;
    }

    public String getGoal() {
        return goal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
