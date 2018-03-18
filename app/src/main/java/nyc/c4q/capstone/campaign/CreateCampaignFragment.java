package nyc.c4q.capstone.campaign;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.capstone.Campaign;
import nyc.c4q.capstone.R;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateCampaignFragment extends Fragment implements View.OnClickListener {

    private EditText campaignTitle;
    private EditText campaignGoal;
    private EditText campaignImageUrl;
    private EditText campaignCreator;
    private Button createCampaignButton;

    public CreateCampaignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_create_campaign, container, false);
        campaignTitle = rootview.findViewById(R.id.firebase_test_set_title);
        campaignGoal = rootview.findViewById(R.id.firebase_test_set_goal);
        campaignImageUrl = rootview.findViewById(R.id.firebase_test_set_imageurl);
        campaignCreator = rootview.findViewById(R.id.firebase_test_set_campaign_creator);
        createCampaignButton = rootview.findViewById(R.id.firebase_test_store_data);
        createCampaignButton.setOnClickListener(this);
        return rootview;
    }

    private void createCampaign() {
        String title = campaignTitle.getText().toString();
        String user = campaignCreator.getText().toString();
        String goal = campaignGoal.getText().toString();
        String url = campaignImageUrl.getText().toString();

        Campaign campaign = new Campaign(title, user, "sampleId", url, goal, "sample Summary", "sample Intro", "sample Body");
        firebaseDataHelper.getCampaignDatbaseRefrence().child(title).setValue(campaign);
    }

    @Override
    public void onClick(View view) {
        createCampaign();
    }
}
