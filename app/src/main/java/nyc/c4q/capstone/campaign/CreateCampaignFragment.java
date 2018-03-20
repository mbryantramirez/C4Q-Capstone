package nyc.c4q.capstone.campaign;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.capstone.models.CreateCampaignModel;
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
    private EditText campaignSummary;
    private EditText campaignIntro;
    private EditText campaignBody;
    private EditText campaignAddress;
    private EditText campaignCategory;
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
        campaignSummary=rootview.findViewById(R.id.firebase_test_set_summary);
        campaignCreator = rootview.findViewById(R.id.firebase_test_set_campaign_creator);
        createCampaignButton = rootview.findViewById(R.id.firebase_test_store_data);
        campaignIntro=rootview.findViewById(R.id.firebase_test_set_intro);
        campaignBody=rootview.findViewById(R.id.firebase_test_set_campaign_body);
        campaignAddress=rootview.findViewById(R.id.address);
        campaignCategory=rootview.findViewById(R.id.category);
        createCampaignButton.setOnClickListener(this);
        return rootview;
    }

    private void createCampaign() {
        String title = campaignTitle.getText().toString();
        String user = campaignCreator.getText().toString();
        String goal = campaignGoal.getText().toString();
        String url = campaignImageUrl.getText().toString();
        String creator= campaignCreator.getText().toString();
        String intro=campaignIntro.getText().toString();
        String summary=campaignSummary.getText().toString();
        String body=campaignBody.getText().toString();
        String address=campaignAddress.getText().toString();
        String category=campaignCategory.getText().toString();


        CreateCampaignModel campaign = new CreateCampaignModel(title, user, creator, url, goal, summary, intro, body, address, category);
        firebaseDataHelper.getCampaignDatbaseRefrence().child(title).setValue(campaign);
    }

    @Override
    public void onClick(View view) {
        createCampaign();
    }
}
