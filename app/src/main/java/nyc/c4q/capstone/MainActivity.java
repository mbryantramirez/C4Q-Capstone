package nyc.c4q.capstone;

import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ValueEventListener {

  public static final CharitySearchService charityCallback = ServiceGenerator.createService();

  private static final String TAG = "GEO?";
  Geocoder geocoder;
  List<Address> addressBook;
  List<CampaignModel> campaignList = new ArrayList<>();
  private DatabaseReference mPrivateDatabaseRef;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    geocoder = new Geocoder(this, Locale.getDefault());
    String bryantsHome = "4123 74th St Flushing NY";

    mPrivateDatabaseRef = FirebaseDatabase.getInstance().getReference();
    mPrivateDatabaseRef.child("blogs");

    /* When user inputs address we will need to convert that address long and lat
    to calculate distance between user locations and charity/campaign location
    */
    try {
      addressBook = geocoder.getFromLocationName(bryantsHome, 1);
      double lat = addressBook.get(0).getLatitude();
      double lon = addressBook.get(0).getLongitude();
      Log.d(TAG, "onCreate: Latitude " + lat + "  Longitude: " + lon);
    } catch (IOException e) {
      e.printStackTrace();
    }

    charitySearch();
    writeNewCampaign();
  }

  private void charitySearch() {
    //BACK UP DATA USING THE CHARITY API
    Call<CharitySearchResult> charitySearchResultCall = charityCallback.getCharitySearchResults();
    charitySearchResultCall.enqueue(new Callback<CharitySearchResult>() {
      @Override public void onResponse(Call<CharitySearchResult> call,
          Response<CharitySearchResult> response) {
        List<CharityData> data = response.body().getData();
      }

      @Override public void onFailure(Call<CharitySearchResult> call, Throwable t) {

      }
    });
  }

  private void writeNewCampaign() {
    //WRITING TO FIREBASE
    CampaignModel campaignModel =
        new CampaignModel("4123 74th St", 20000, "http", "summary", "title");
    mPrivateDatabaseRef.child("blogs").child("title").setValue(campaignModel);
  }

  @Override public void onDataChange(DataSnapshot dataSnapshot) {
    /*UNFINISHED reading from database and returning a list of objects we're gonna need to do this
      inorder to organize this list back into location optimized order  */
    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
    for (DataSnapshot child : children) {
      CampaignModel campaignModel = child.getValue(CampaignModel.class);
      campaignList.add(campaignModel);
      Log.d(TAG, "onDataChange: " + dataSnapshot.getChildrenCount());
    }
  }

  @Override public void onCancelled(DatabaseError databaseError) {

  }
}

