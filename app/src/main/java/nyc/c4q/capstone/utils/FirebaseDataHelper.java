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
    private static final String TAG = "Firebase?";
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private Context context;


    private static class FirebaseHolder {
        private static final FirebaseDataHelper INSTANCE = new FirebaseDataHelper();
        //this is a instance class
    }

    public static FirebaseDataHelper getInstance() {
        // this is the instance method
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

    public DatabaseReference getPreferenceDatabaseReference() {
        return getDatabaseReference().child("preferences");
        //In here the ide is getting data from the database with child preferences.
    }


    public List<DBReturnCampaignModel> getCampaignsList(DataSnapshot dataSnapshot, String textFromPref) {
        List<DBReturnCampaignModel> DBReturnCampaignModelList = new ArrayList<>();

        int count = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            //this is assigning each child in the the datbase to a child.
            Log.d(TAG, "onFireBaseDatahelperCall: " + child + count);
            Log.d(TAG, "onFireBaseDatahelperCall: " + child.getValue() + count);
            Log.d(TAG, "onLoopCount: " + count);

            Log.d(TAG, "getCampaignsList: " + child.getKey());
            if (child.child("category").getValue(String.class).contains(textFromPref)) {
                //If any of the keys in the category contains the text in the string
                //the parameters in the model will be assigned to the values of the keys in the database.
                // it will add it to the listg
                Log.d(TAG, "onChildrenLoop: " + child.child("category"));
                DBReturnCampaignModel dbReturnCampaignModel = child.getValue(DBReturnCampaignModel.class);
                Log.d(TAG, "getCampaignsList: " + dbReturnCampaignModel.getTitle());
                DBReturnCampaignModelList.add(dbReturnCampaignModel);
                //The updated model will be added to the list.
            }
        }
        return DBReturnCampaignModelList;
    }

    public DBReturnCampaignModel getCampaign(DataSnapshot dataSnapshot, String blogTitleString) {
        Log.d(TAG, "query" + dataSnapshot.hasChild(blogTitleString));
        Log.d(TAG, "getCampaign: " + dataSnapshot.hasChild(blogTitleString));
        if (dataSnapshot.hasChild(blogTitleString)) {
            Log.d(TAG, "datasnapshot:" + dataSnapshot.child(blogTitleString));
            return dataSnapshot.child(blogTitleString).getValue(DBReturnCampaignModel.class);
        }
        //this method will return a child from the  database with a certain blog title.

        return null;
    }

    public List<String> getFundedCampaignsList(DataSnapshot dataSnapshot, String uid) {
        List<String> fundedCampaignNames = new ArrayList<>();
        Log.d(TAG, "onDataSnapshot: " + uid);
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Log.d(TAG, "onDataSnapshotParse: " + child.getKey());
        }
        int count = 0;
        for (DataSnapshot child : dataSnapshot.getChildren()) {
            Log.d(TAG, "onFireBaseDatahelperCall: " + child + count);
            Log.d(TAG, "onFireBaseDatahelperCall: " + child.getValue() + count);
            Log.d(TAG, "onLoopCount: " + count);

            Log.d(TAG, "getFundedCampaignsList: " + child.getKey());
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
//The methods in the class gets used throughout the app, so that is why it is called the helper class.
