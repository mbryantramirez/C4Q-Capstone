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
import java.util.Stack;

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
    private String uid;
    Context context;
    private Stack<CreateCampaignModel> createSampleCampaignsStack = new Stack<>();


    public CreateCampaignFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_create_campaign, container, false);
        uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();
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
        initializeDemoSamples();
        return rootview;
    }

    private void initializeDemoSamples() {
        CreateCampaignModel campaign1 = new CreateCampaignModel("Daycare Help", "sample", uid, "https://static1.squarespace.com/static/53def449e4b031c95f37d516/541346c0e4b0f910e30f2835/5419fcc4e4b0e99e67e19b6d/1410989271679/P1170008.jpg", "500", "Child care is so expensive and I don’t have any family around to babysit", "My daughter turns 3 next week. I remember so clearly the day I found out I was pregnant. I was young, scared, and alone. It’s been tough for me, but I graduate from school in a year and I can start a career that will give us both a better life. Child care is so expensive and I don’t have any family around to babysit. Can you help me to cover the cost of daycare so that I can focus on my studies and give my baby girl the life she deserves? Thank you for helping us.", "544 47th Ave, Long Island City, NY 11101", "Education", "0", "(718) 729-9071");
        CreateCampaignModel campaign2 = new CreateCampaignModel("We lost everything", "sample", uid, "https://media.nbcnewyork.com/images/1200*675/WNBC+11PM+AIRCHECK+M-Sun+-+23011029_WNBC_000000015818784+copy.jpg", "10000", "We stood on the curb and watched the scene of our home burning to the ground", "We lost everything that night. As we stood on the curb and watched the surreal scene of our home burning to the ground, I felt numb. The enormity of what was happening was just too overwhelming. But that numbness quickly turned to anger. Anger gave way to sorrow, and in time, my sorrow turned to gratitude. Yes, we lost everything, but we escaped with our lives. All the proceeds from this campaign will go to families that have experienced a tragedy — specifically, the loss of a child. Please help me in easing the suffering of others that have truly lost everything. Thank you.", "51-11 34th St, Long Island City, NY 11101 ", "Tragedy", "0", "(917) 484-3582");
        CreateCampaignModel campaign3 = new CreateCampaignModel("World Youth Alliance", "sample", uid, "https://scontent-lga3-1.xx.fbcdn.net/v/t1.0-9/29178459_1588198964568155_6796363408900882432_n.jpg?_nc_cat=0&oh=cb32b892f8431707c64b6013e13ff7a1&oe=5B2AF6C5", "300", "The World Youth Alliance works to understand and speak about the idea of the human person", "This is the East New York Worlds Youth Alliance. I came here every day after school when I was a child. My home life was rough and school life was even rougher. This center was a safe-haven for me. I could always count on a warm meal and a friendly face to help me with my problems. I can honestly say that I don’t know where I would be today if this place hadn’t existed. I am starting this campaign to give back to the place that helped me so much, so that they can continue to help the children of today. Thank you for your generosity.", "228 E 71st St, New York, NY 10021", "Community", "0", "(212) 585-0757");
        createSampleCampaignsStack.add(campaign1);
        createSampleCampaignsStack.add(campaign2);
        createSampleCampaignsStack.add(campaign3);
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
        CreateCampaignModel model = createSampleCampaignsStack.pop();
        firebaseDataHelper.getCampaignDatbaseReference().child(model.title).setValue(model);
        Toast.makeText(getActivity(), "Upload Successful", Toast.LENGTH_SHORT).show();
    }


}


