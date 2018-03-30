package nyc.c4q.capstone.utils;

/**
 * Created by c4q on 3/17/18.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.FeatureInfo;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static android.content.Context.MODE_PRIVATE;


public class FirebaseDataHelper {
    private static FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences preferences;
    private static final String CAMPAIGN_PATH = "campaigns";
    private static final String USER_PATH = "users";
    private static final String TAG = "Firebase?";
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private Context context;

    private static class FirebaseHolder {
        private static final FirebaseDataHelper INSTANCE = new FirebaseDataHelper();
    }

    public static FirebaseDataHelper getInstance() {
        return FirebaseHolder.INSTANCE;
    }

    private static FirebaseDatabase getDatabase() {
        if (firebaseDatabase == null) {
            firebaseDatabase = FirebaseDatabase.getInstance();
            firebaseDatabase.setPersistenceEnabled(true);
        }
        return firebaseDatabase;
    }

    public DatabaseReference getDatabaseReference() {
        databaseReference = getDatabase().getReference();
        return databaseReference;
    }

    public DatabaseReference getCampaignDatbaseRefrence() {
        return getDatabaseReference().child(CAMPAIGN_PATH);
    }

    public DatabaseReference getUsersDatabaseReference() {
        return getDatabaseReference().child(USER_PATH);
    }


    public List<DBReturnCampaignModel> getCampaignsList(DataSnapshot dataSnapshot, String textFromPref) {
        List<DBReturnCampaignModel> DBReturnCampaignModelList = new ArrayList<>();

        int count = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Log.d(TAG, "onFireBaseDatahelperCall: " + child + count);
            Log.d(TAG, "onFireBaseDatahelperCall: " + child.getValue() + count);
            Log.d(TAG, "onLoopCount: " + count);

            Log.d(TAG, "getCampaignsList: " + dataSnapshot.getChildren());
            if (child.child("category").getValue(String.class).contains(textFromPref)) {
                Log.d(TAG, "onChildrenLoop: " + child.child("category"));
                DBReturnCampaignModel dbReturnCampaignModel = child.getValue(DBReturnCampaignModel.class);
                Log.d(TAG, "getCampaignsList: "+dbReturnCampaignModel.getTitle());
                DBReturnCampaignModelList.add(dbReturnCampaignModel);
            }

        }
        //what this method is doing is it takes a string as a parameter and then
        return DBReturnCampaignModelList;
    }

    public DBReturnCampaignModel getCampaign(DataSnapshot dataSnapshot, String blogTitleString) {
        Log.d(TAG, "query" + dataSnapshot.hasChild(blogTitleString));
        Log.d(TAG, "getCampaign: "+dataSnapshot.hasChild(blogTitleString));
        if (dataSnapshot.hasChild(blogTitleString)) {
            Log.d(TAG, "datasnapshot:" + dataSnapshot.child(blogTitleString));
            return dataSnapshot.child(blogTitleString).getValue(DBReturnCampaignModel.class);
        }

        return null;
    }

}
