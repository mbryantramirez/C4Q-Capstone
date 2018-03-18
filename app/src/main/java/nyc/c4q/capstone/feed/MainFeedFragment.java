package nyc.c4q.capstone.feed;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.R;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFeedFragment extends Fragment implements ChildEventListener {
    private static final String TAG = "FIREBASE?";
    private TextView campaignTitle;
    private TextView campaignGoal;
    private TextView campaignImage;
    private TextView campaignCreator;

    public MainFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_feed, container, false);
        campaignTitle = rootView.findViewById(R.id.firebase_test_title);
        campaignGoal = rootView.findViewById(R.id.firebase_test_goal);
        campaignImage = rootView.findViewById(R.id.firebase_test_imageurl);
        campaignCreator = rootView.findViewById(R.id.firebase_test_username);

        firebaseDataHelper.getCampaignDatbaseRefrence().addChildEventListener(this);

        return rootView;
    }

    private void loadTextFromList(List<DBReturnCampaignModel> currentCampaignsList) {
        for (DBReturnCampaignModel DBReturnCampaignModel : currentCampaignsList) {
            campaignTitle.setText(DBReturnCampaignModel.getTitle());
            campaignGoal.setText(DBReturnCampaignModel.getGoal());
            campaignImage.setText(DBReturnCampaignModel.getImageUrl());
            campaignCreator.setText(DBReturnCampaignModel.getCreatorName());
        }
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        loadTextFromList(firebaseDataHelper.getCampaignsList(dataSnapshot));
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

}
