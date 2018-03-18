package nyc.c4q.capstone.firebase;

/**
 * Created by c4q on 3/17/18.
 */

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;


public class FirebaseDataHelper {
    private DatabaseReference databaseReference;
    private static final String CAMPAIGN_PATH = "campaigns";
    private static final String TAG = "Firebase?";

    private static class FirebaseHolder {
        private static final FirebaseDataHelper INSTANCE = new FirebaseDataHelper();
    }

    private static FirebaseDataHelper getInstance() {
        return FirebaseHolder.INSTANCE;
    }

    public FirebaseDataHelper() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public DatabaseReference getCampaignDatbaseRefrence() {
        return databaseReference.child(CAMPAIGN_PATH);
    }

    public List<DBReturnCampaignModel> getCampaignsList(DataSnapshot dataSnapshot) {
        List<DBReturnCampaignModel> DBReturnCampaignModelList = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            DBReturnCampaignModel DBReturnCampaignModel = dataSnapshot.getValue(DBReturnCampaignModel.class);
            DBReturnCampaignModelList.add(DBReturnCampaignModel);
            Log.d(TAG, "onChildAdded: " + child.getValue());
        }
        return DBReturnCampaignModelList;
    }


}
