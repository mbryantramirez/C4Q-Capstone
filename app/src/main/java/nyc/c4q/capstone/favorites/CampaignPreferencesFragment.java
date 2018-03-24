package nyc.c4q.capstone.favorites;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

import static android.content.Context.MODE_PRIVATE;
import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignPreferencesFragment extends Fragment implements ValueEventListener {
    private View rootView;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private RecyclerView recyclerView;
    private Button medButton;
    private Button housingButton;
    private Button clothingButton;
    private Button educationButton;
    private Button businessButton;
    private Button npButton;
    FirebaseDataHelper firebaseDataHelper;
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private FavoritesAdapter listAdapter;


    public CampaignPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_campaign_preferences, container, false);
        recyclerView = rootView.findViewById(R.id.favorites_recyclerview);
        firebaseDataHelper= new FirebaseDataHelper();
        preferences=rootView.getContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        editor=preferences.edit();
        medButton= rootView.findViewById(R.id.medButton);
        housingButton=rootView.findViewById(R.id.housing_button);
        clothingButton=rootView.findViewById(R.id.clothingButton);
        educationButton=rootView.findViewById(R.id.education_button);
        businessButton=rootView.findViewById(R.id.business);
        npButton=rootView.findViewById(R.id.npButton);

        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromMedButton= medButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromMedButton).addValueEventListener(CampaignPreferencesFragment.this);

            }
        });
        housingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromHousingButton= housingButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromHousingButton).addValueEventListener(CampaignPreferencesFragment.this);


            }
        });
        clothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromClothingButton= clothingButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromClothingButton).addValueEventListener(CampaignPreferencesFragment.this);


            }
        });
        educationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromEducationButton=educationButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromEducationButton).addValueEventListener(CampaignPreferencesFragment.this);

            }
        });
        businessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromBusinessButton= businessButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromBusinessButton).addValueEventListener(CampaignPreferencesFragment.this);

            }
        });
        npButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromnpButton=npButton.getText().toString();
                firebaseDataHelper.getDatabaseReference().child(textFromnpButton).addValueEventListener(CampaignPreferencesFragment.this);

            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdapter = new FavoritesAdapter(campaignModelList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        campaignModelList = firebaseDataHelper.getCampaignsList(dataSnapshot);
        listAdapter.setData(campaignModelList);
        listAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
    /*
    In here I will save the texts from the buttons and then save it to a sharedpreference and
    then firebase will take the text and look for campaigns with familiar texts and then display them
    in a recyclerview. But I am having trouble figuring out the logic for that.

     */

}
