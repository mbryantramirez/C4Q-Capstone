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
import java.util.Arrays;
import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.models.PreferencesModel;

import static android.content.Context.MODE_PRIVATE;


public class FirebaseDataHelper {
    private static FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private SharedPreferences preferences;
    private static final String CAMPAIGN_PATH = "campaigns";
    private static final String USER_PATH = "users";
    private static final String FUNDED_PATH = "funded";
    private static final String FAVORITE_PATH = "favorites";
    private static final String PREFRENCE_PATH = "preferences";
    private static final String TAG = "FirebaseHelper?";
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

    public DatabaseReference getFundedCampaignsDatabaseReference() {
        return getDatabaseReference().child(FUNDED_PATH);
    }

    public DatabaseReference getCampaignDatbaseReference() {
        return getDatabaseReference().child(CAMPAIGN_PATH);
    }

    public DatabaseReference getUsersDatabaseReference() {
        return getDatabaseReference().child(USER_PATH);
    }

    public DatabaseReference getPreferencesDatabaseReference() {
        return getDatabaseReference().child(PREFRENCE_PATH);
    }

    public DatabaseReference getFavoritesDatabaseReference() {
        return getDatabaseReference().child(FAVORITE_PATH);
    }


    public List<DBReturnCampaignModel> getCampaignsList(DataSnapshot dataSnapshot) {
        List<DBReturnCampaignModel> DBReturnCampaignModelList = new ArrayList<>();
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            DBReturnCampaignModel dbReturnCampaignModel = child.getValue(DBReturnCampaignModel.class);
            DBReturnCampaignModelList.add(dbReturnCampaignModel);
        }
        return DBReturnCampaignModelList;
    }

    public List<String> getFavoritedCampaignsTitles(DataSnapshot dataSnapshot, String uid) {
        List<String> favoritedCampaignNames = new ArrayList<>();
        Log.d(TAG, "onDataSnapshot: " + uid);
        int count = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Log.d(TAG, "onGetSnapShotChildren: " + child);
            if (child.getKey().equals(uid)) {
                for (DataSnapshot values : child.getChildren()) {
                    Log.d(TAG, "True");
                    favoritedCampaignNames.add(values.getKey());
                }
            }
        }
        Log.d(TAG, "onGetFavoriteTitles: " + Arrays.toString(new List[]{favoritedCampaignNames}));
        return favoritedCampaignNames;
    }

    public List<DBReturnCampaignModel> getFavoritedCampaigns(DataSnapshot dataSnapshot, List<String> favoriteCampaignTitles) {
        List<DBReturnCampaignModel> favoritedCampaigns = new ArrayList<>();
        for (String title : favoriteCampaignTitles) {
            DBReturnCampaignModel campaign = getCampaign(dataSnapshot, title);
            if (campaign != null) {
                favoritedCampaigns.add(campaign);
            } else {
                Log.d(TAG, "Error Returned null on Favorite Campaign" + title);
            }
        }
        Log.d(TAG, Arrays.toString(new List[]{favoritedCampaigns}));
        return favoritedCampaigns;
    }

    public DBReturnCampaignModel getCampaign(DataSnapshot dataSnapshot, String blogTitleString) {
        Log.d(TAG, "query" + dataSnapshot.hasChild(blogTitleString));
        Log.d(TAG, "getCampaign: " + dataSnapshot.hasChild(blogTitleString));
        if (dataSnapshot.hasChild(blogTitleString)) {
            Log.d(TAG, "datasnapshot:" + dataSnapshot.child(blogTitleString));
            return dataSnapshot.child(blogTitleString).getValue(DBReturnCampaignModel.class);
        }

        return null;
    }

    public List<String> getFundedCampaignsList(DataSnapshot dataSnapshot, String uid) {
        List<String> fundedCampaignNames = new ArrayList<>();
        Log.d(TAG, "onDataSnapshot: " + uid);
        int count = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            for (DataSnapshot values : child.getChildren()) {
                if (values.getValue().equals(uid)) {
                    Log.d(TAG, "True");
                    fundedCampaignNames.add(child.getKey());
                }
            }
        }
        return fundedCampaignNames;
    }


    public List<DBReturnCampaignModel> getCampaignsFromFundedList(DataSnapshot dataSnapshot, List<String> fundedCampaignsList) {
        List<DBReturnCampaignModel> contributedCampaignsList = new ArrayList<>();

        for (String title : fundedCampaignsList) {
            DBReturnCampaignModel campaign = getCampaign(dataSnapshot, title);
            if (campaign != null) {
                contributedCampaignsList.add(campaign);
            } else {
                Log.d(TAG, "Error Returned Null on " + title);
            }
        }

        return contributedCampaignsList;
    }

    public List<String> getPreferenceString(DataSnapshot dataSnapshot, String uid) {
        List<String> preferenceList = new ArrayList<>();

        for (DataSnapshot child : dataSnapshot.child(uid).getChildren()) {
            String prefs = child.getValue(String.class);

            preferenceList.add(prefs);

        }
        Log.d(TAG, Arrays.toString(new List[]{preferenceList}));
        return preferenceList;
    }

    public List<DBReturnCampaignModel> getPreferencesModel(DataSnapshot dataSnapshot, List<String> preferenceList) {
        List<DBReturnCampaignModel> model = new ArrayList<>();
        for (String a : preferenceList) {
            for (DataSnapshot child : dataSnapshot.getChildren())
                if (child.child("category").getValue(String.class).contains(a)) {
                    DBReturnCampaignModel dbReturnCampaignModel = child.getValue(DBReturnCampaignModel.class);
                    model.add(dbReturnCampaignModel);
                }
        }
        Log.d(TAG, Arrays.toString(new List[]{model}));
        return model;
    }

}
