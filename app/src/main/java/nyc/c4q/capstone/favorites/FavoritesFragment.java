package nyc.c4q.capstone.favorites;


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
import java.util.Objects;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */


public class FavoritesFragment extends Fragment implements ValueEventListener {
    private View rootView;
    private RecyclerView recyclerView;
    private Button fundedButton;
    private Button favoritesButton;
    private DataSnapshot campaignsDatasnapshot;

    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private FavoritesAdapter listAdapter;
    private boolean isFavorite;

    private static final String TAG = "FIREBASEFAV";

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = rootView.findViewById(R.id.favorites_recyclerview);
        favoritesButton = rootView.findViewById(R.id.favorites);
        fundedButton = rootView.findViewById(R.id.fundedButton);
        firebaseDataHelper.getCampaignDatbaseReference().addValueEventListener(this);
        addFavoritesEventListner();
        isFavorite = true;

        fundedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = false;
                addFundedEventListner();
            }
        });

        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = true;
                addFavoritesEventListner();
            }
        });

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdapter = new FavoritesAdapter(campaignModelList, firebaseDataHelper);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        campaignsDatasnapshot = dataSnapshot;
    }

    private void addFundedEventListner() {
        firebaseDataHelper.getFundedCampaignsDatabaseReference().addValueEventListener(new ValueEventListener() {
            String uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> fundedCampaignList = firebaseDataHelper.getFundedCampaignsList(dataSnapshot, uid);
                campaignModelList = firebaseDataHelper.getCampaignsFromFundedList(campaignsDatasnapshot, fundedCampaignList);
                listAdapter.setData(campaignModelList, isFavorite);
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void addFavoritesEventListner() {
        firebaseDataHelper.getFavoritesDatabaseReference().addValueEventListener(new ValueEventListener() {
            String uid = ((MainActivity) (Objects.requireNonNull(getActivity()))).getCurrentUserID();

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                campaignModelList = firebaseDataHelper.getFavoritedCampaigns(campaignsDatasnapshot, firebaseDataHelper.getFavoritedCampaignsTitles(dataSnapshot, uid));
                listAdapter.setData(campaignModelList, isFavorite);
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}

