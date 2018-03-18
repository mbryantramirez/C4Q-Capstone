package nyc.c4q.capstone.firebase;

/**
 * Created by c4q on 3/17/18.
 */

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.CampaignTestModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


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

}
