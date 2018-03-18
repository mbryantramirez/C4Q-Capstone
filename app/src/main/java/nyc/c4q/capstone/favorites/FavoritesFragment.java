package nyc.c4q.capstone.favorites;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.CreateCampaignModel;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.LoginActivity.TAG;
import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritesFragment extends Fragment implements ChildEventListener {
    private View rootView;
    private List<String> myStrings = new ArrayList<>();
    private RecyclerView recyclerView;
    //In this fragment Muhaimen will put in the logic to display the list of campaigns
    //
    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private PaignAdapter listAdapter;

    private static final String TAG = "FIREBASEFAV";

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myStrings.add("who");
        myStrings.add("what");
        myStrings.add("where");
        myStrings.add("why");

        rootView = inflater.inflate(R.layout.fragment_paign, container, false);
        recyclerView = rootView.findViewById(R.id.paignRecyclerview);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(listAdapter);


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "onChildAdded: " + dataSnapshot);

        campaignModelList.addAll(firebaseDataHelper.getCampaignsList(dataSnapshot));
        for (DBReturnCampaignModel ccm : campaignModelList) {
            Log.d(TAG, "onChildAdded: " + ccm.getTitle());
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(rootView.getContext().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new PaignAdapter(campaignModelList);
        recyclerView.setAdapter(listAdapter);
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
