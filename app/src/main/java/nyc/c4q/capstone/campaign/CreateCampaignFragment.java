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

import com.rengwuxian.materialedittext.MaterialEditText;

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
        CreateCampaignModel campaign1 = new CreateCampaignModel("Harlem Book Festival – Harlem is Reading", "sample", uid, "https://media-cdn.tripadvisor.com/media/photo-s/02/ab/52/02/filename-img-0587-jpg.jpg", "5000", "Help cultivate the next Langston Hughes and bring literacy to Harlem", "We are striving to bring culturally relevant texts into the community and lives of kids who will really appreciate being able to have a book or two at home to read. It is our belief that bringing culturally relevant texts to our youth connects them to their communities and uplifts the community through the process of reading and learning. A child that does not know where he or she comes from is a child that will be lost, always wondering who they are and what their purpose may be in their own lives. In our event we will be showcasing upcoming authors from our community and hopefully encouraging the next Langston Hughes to pick up a book and truly make a lasting impact in our community.", "1619 Amsterdam Ave, New York, NY 10031", "Community", "0", "(718) 729-9071");
        CreateCampaignModel campaign2 = new CreateCampaignModel("We lost everything", "sample", uid, "https://media.nbcnewyork.com/images/1200*675/WNBC+11PM+AIRCHECK+M-Sun+-+23011029_WNBC_000000015818784+copy.jpg", "10000", "We stood on the curb and watched the scene of our home burning to the ground", "We lost everything that night. As we stood on the curb and watched the surreal scene of our home burning to the ground, I felt numb. The enormity of what was happening was just too overwhelming. But that numbness quickly turned to anger. Anger gave way to sorrow, and in time, my sorrow turned to gratitude. Yes, we lost everything, but we escaped with our lives. All the proceeds from this campaign will go to families that have experienced a tragedy — specifically, the loss of a child. Please help me in easing the suffering of others that have truly lost everything. Thank you.", "51-11 34th St, Long Island City, NY 11101 ", "Tragedy", "0", "(917) 484-3582");
        CreateCampaignModel campaign3 = new CreateCampaignModel("Widowed Father In Need of Help With Childcare", "sample", uid, "http://photos.myjoyonline.com/photos/news/201501/2426489714998_6549114670966.png", "1500", "My wife just passed away this January and I am left alone to raise our two kids...", "Hello. My name is Michael. I am the proud father of two gorgeous children. My wife passed away several months ago and it’s been hard to get by. I was also laid off from my job recently. Child care is so expensive and I don’t have any family around to help with babysitting. Can you help me to cover the cost of daycare so that I can focus on finding a job and give my kids a solid future. Thank you for helping us.", "824 St Nicholas Ave, New York, NY 10031", "Children", "0", "(212) 585-0757");
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


