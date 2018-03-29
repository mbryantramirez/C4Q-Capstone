package nyc.c4q.capstone.models;

/**
 * Created by c4q on 3/15/18.
 */

public class CreateCampaignModel {

    public String creatorID;
    public String title;
    public String creatorName;
    public String imageUrl;
    public String goal;
    public String intro;
    public String body;
    public String address;
    public String category;
    public String website;
    public String phoneNumber;


    public CreateCampaignModel() {

    }

    public CreateCampaignModel(String title, String creatorName, String creatorID, String imageUrl, String goal, String intro, String body, String address, String category, String website, String phoneNumber) {
        this.title = title;
        this.creatorName = creatorName;
        this.creatorID = creatorID;
        this.imageUrl = imageUrl;
        this.goal = goal;
        this.intro = intro;
        this.body = body;
        this.address = address;
        this.category = category;
        this.website = website;
        this.phoneNumber = phoneNumber;
    }


}
