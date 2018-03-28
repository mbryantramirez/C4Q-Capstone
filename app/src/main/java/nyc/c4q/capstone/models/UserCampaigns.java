package nyc.c4q.capstone.models;

public class UserCampaigns {

    public String title;
    public String creatorID;
    public String creatorName;
    public String goal;
    public String address;
    public String imageUrl;
    public String totalFunding;
    public String intro;
    public String story;
    public String category;
    public String website;



    public static class CampaignBuilder{
        private String title;
        private String creatorID;
        private String creatorName;
        private String goal;
        private String address;
    }


}
