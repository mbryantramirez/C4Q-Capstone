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
    //In this fragment Muhaimen will put in the logic to display the list of campaigns
    //
    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private FavoritesAdapter listAdapter;

    private static final String TAG = "FIREBASEFAV";

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = rootView.findViewById(R.id.favorites_recyclerview);
        favoritesButton=rootView.findViewById(R.id.favorites);
        fundedButton=rootView.findViewById(R.id.fundedButton);
        fundedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDataHelper.getDatabaseReference().child("funded").addValueEventListener(FavoritesFragment.this);

            }
        });
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseDataHelper.getDatabaseReference().child("favorites").addValueEventListener(FavoritesFragment.this);

            }
        });




        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        //In her firebase takes a snapshot of the model and saves it, then puts the data wherever needed.
        campaignModelList = firebaseDataHelper.getCampaignsList(dataSnapshot," ");
        listAdapter.setData(campaignModelList);
        listAdapter.notifyDataSetChanged();

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }



}
