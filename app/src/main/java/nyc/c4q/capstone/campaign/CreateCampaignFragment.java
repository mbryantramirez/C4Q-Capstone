package nyc.c4q.capstone.campaign;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
    private Spinner campaignCategory;
    private EditText campaignWebsite;
    private EditText campaignPhoneNumber;
    private Button createCampaignButton;
    Context context;


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
        campaignCategory = rootview.findViewById(R.id.firebase_set_category_spinner);
        campaignWebsite = rootview.findViewById(R.id.firebase_set_website_url);
        campaignPhoneNumber = rootview.findViewById(R.id.firebase_set_phone_num);
        createCampaignButton.setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.category_spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        campaignCategory.setAdapter(adapter);
//        campaignCategory.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) getContext());

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
//        String category = campaignCategory.getText().toString();
          String website = campaignWebsite.getText().toString();
        String phoneNumber = campaignPhoneNumber.getText().toString();

//        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(goal) || TextUtils.isEmpty(url) || TextUtils.isEmpty(creator) || TextUtils.isEmpty(intro) || TextUtils.isEmpty(body) || TextUtils.isEmpty(address) || TextUtils.isEmpty(category) || TextUtils.isEmpty(uid)) {
//            Toast.makeText(getContext(), "enter missing input", Toast.LENGTH_LONG).show();
//        } else {
//        String uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();
//        CreateCampaignModel campaign = new CreateCampaignModel(title, creator, uid, url, goal, intro, body, address, category, phoneNumber);
//            firebaseDataHelper.getCampaignDatbaseReference().child(title).setValue(campaign);
//        }
    }

    @Override
    public void onClick(View view) {
//        createCampaign();
        createSampleCampaign();
    }

    public void createSampleCampaign() {
        String uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();
        CreateCampaignModel campaign = new CreateCampaignModel("Sample4", "sample", uid, "https://dog.ceo/api/img/wolfhound-irish/n02090721_2319.jpg", "sample", "sample", "sample", "4804 Locust St, Woodside, NY 11377", "sample", /* "sample",*/ "sample");
        firebaseDataHelper.getCampaignDatbaseReference().child(campaign.title).setValue(campaign);
        Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_SHORT).show();
    }


}


