package nyc.c4q.capstone.feed;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.CampaignTestModel;
import nyc.c4q.capstone.R;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFeedFragment extends Fragment {
    private static final String TAG = "FIREBASE?";
    private TextView campaignTitle;
    private TextView campaignGoal;
    private TextView campaignImage;
    private TextView campaignCreator;

    public MainFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_feed, container, false);
        campaignTitle = rootView.findViewById(R.id.firebase_test_title);
        campaignGoal = rootView.findViewById(R.id.firebase_test_goal);
        campaignImage = rootView.findViewById(R.id.firebase_test_imageurl);
        campaignCreator = rootView.findViewById(R.id.firebase_test_username);

        loadListFromFirebase();

        return rootView;
    }

    private void loadListFromFirebase() {
        firebaseDataHelper.getCampaignDatbaseRefrence().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                List<CampaignTestModel> campaignTestModelList = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    CampaignTestModel campaignTestModel = dataSnapshot.getValue(CampaignTestModel.class);
                    campaignTestModelList.add(campaignTestModel);
                    Log.d(TAG, "onChildAdded: " + child.getValue());
                }
                loadTextFromList(campaignTestModelList);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void loadTextFromList(List<CampaignTestModel> retrivedCampaigns) {
        for (CampaignTestModel campaignTestModel : retrivedCampaigns) {
            campaignTitle.setText(campaignTestModel.getTitle());
            campaignGoal.setText(campaignTestModel.getGoal());
            campaignImage.setText(campaignTestModel.getImageUrl());
            campaignCreator.setText(campaignTestModel.getCreatorName());
        }
    }
}
