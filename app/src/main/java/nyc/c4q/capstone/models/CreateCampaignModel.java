package nyc.c4q.capstone.models;

/**
 * Created by c4q on 3/15/18.
 */

public class CreateCampaignModel {

    private String title;
    private String creatorName;
    private String creatorID;
    private String imageUrl;
    private String goal;
    private String summary;
    private String intro;
    private String body;
    private String address;
    private String category;

    public CreateCampaignModel() {

    }

    public CreateCampaignModel(String title, String creatorName, String creatorID, String imageUrl, String goal, String summary, String intro, String body, String address, String category) {
        this.title = title;
        this.creatorName = creatorName;
        this.creatorID = creatorID;
        this.imageUrl = imageUrl;
        this.goal = goal;
        this.summary = summary;
        this.intro = intro;
        this.body = body;
        this.address = address;
        this.category = category;
    }

}
