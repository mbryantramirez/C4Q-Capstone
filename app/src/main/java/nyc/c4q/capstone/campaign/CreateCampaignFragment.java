package nyc.c4q.capstone.campaign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import nyc.c4q.capstone.MainActivity;
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
    private EditText campaignIntro;
    private EditText campaignBody;
    private EditText campaignAddress;
    private EditText campaignCategory;
    private EditText campaignWebsite;
    private EditText campaignPhoneNumber;
    private Button createCampaignButton;


    public CreateCampaignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_create_campaign, container, false);
        campaignTitle = rootview.findViewById(R.id.firebase_set_title);
        campaignGoal = rootview.findViewById(R.id.firebase_set_goal);
        campaignImageUrl = rootview.findViewById(R.id.firebase_set_imageurl);
        campaignCreator = rootview.findViewById(R.id.firebase_set_campaign_creator);
        createCampaignButton = rootview.findViewById(R.id.firebase_store_data);
        campaignIntro = rootview.findViewById(R.id.firebase_set_intro);
        campaignBody = rootview.findViewById(R.id.firebase_set_campaign_body);
        campaignAddress = rootview.findViewById(R.id.firebase_set_address);
        campaignCategory = rootview.findViewById(R.id.firebase_set_category);
        campaignWebsite = rootview.findViewById(R.id.firebase_set_website_url);
        campaignPhoneNumber = rootview.findViewById(R.id.firebase_set_phone_num);
        createCampaignButton.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void createCampaign() {
        String title = campaignTitle.getText().toString();
        String goal = campaignGoal.getText().toString();
        String url = campaignImageUrl.getText().toString();
        String creator = campaignCreator.getText().toString();
        String intro = campaignIntro.getText().toString();
        String body = campaignBody.getText().toString();
        String address = campaignAddress.getText().toString();
        String category = campaignCategory.getText().toString();
        String website = campaignWebsite.getText().toString();
        String phoneNumber = campaignPhoneNumber.getText().toString();

//        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(goal) || TextUtils.isEmpty(url) || TextUtils.isEmpty(creator) || TextUtils.isEmpty(intro) || TextUtils.isEmpty(body) || TextUtils.isEmpty(address) || TextUtils.isEmpty(category) || TextUtils.isEmpty(uid)) {
//            Toast.makeText(getContext(), "enter missing input", Toast.LENGTH_LONG).show();
//        } else {
//            CreateCampaignModel campaign = new CreateCampaignModel(title, creator, uid, url, goal, intro, body, address, category, website, phoneNumber);
//            firebaseDataHelper.getCampaignDatbaseRefrence().child(title).setValue(campaign);
//        }
    }

    @Override
    public void onClick(View view) {
//        createCampaign();
        createSampleCampaign();
    }

    public void createSampleCampaign() {
        String uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();
        CreateCampaignModel campaign = new CreateCampaignModel("sample", "sample", uid, "https://dog.ceo/api/img/sheepdog-shetland/n02105855_3498.jpg", "sample", "sample", "sample", "47-99-47-1 30th Pl", "sample", "sample", "sample");
        firebaseDataHelper.getCampaignDatbaseRefrence().push().setValue(campaign);
        Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_SHORT).show();
    }

}
