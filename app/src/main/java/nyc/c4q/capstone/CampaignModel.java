package nyc.c4q.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by c4q on 3/11/18.
 */

@IgnoreExtraProperties
public class CampaignModel {
  //SAMPLE OF WHAT USER CREATED CAMPAIGN POJO WOULD LOOK LIKE
  private String address;
  private double goal;
  private String imageurl;
  private String summary;
  private String title;

  public CampaignModel(String address, double goal, String imageurl, String summary, String title) {
    this.address = address;
    this.goal = goal;
    this.imageurl = imageurl;
    this.summary = summary;
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getGoal() {
    return goal;
  }

  public void setGoal(double goal) {
    this.goal = goal;
  }

  public String getImageurl() {
    return imageurl;
  }

  public void setImageurl(String imageurl) {
    this.imageurl = imageurl;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}