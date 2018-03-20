package nyc.c4q.capstone.favorites;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.firebase.FirebaseDataHelper;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */


public class FavoritesFragment extends Fragment implements ChildEventListener {
    private static final String KEY_POSITION = "position";
    private View rootView;
    private ViewPager pager;
    private RecyclerView recyclerView;
    private String title;
    private int page;
    //In this fragment Muhaimen will put in the logic to display the list of campaigns
    //
    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private FavoritesAdapter listAdapter;

    private static final String TAG = "FIREBASEFAV";

    public FavoritesFragment() {
        // Required empty public constructor
    }
    public static FavoritesFragment newInstance(int page, String title) {
        FavoritesFragment fragmentFirst = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = rootView.findViewById(R.id.favorites_recyclerview);
        pager= rootView.findViewById(R.id.viewpage);


        // Inflate the layout for this fragment
        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
        firebaseDataHelper.getDatabaseReference().addChildEventListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listAdapter = new FavoritesAdapter(campaignModelList);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(listAdapter);
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        campaignModelList = firebaseDataHelper.getCampaignsList(dataSnapshot);
        for (DBReturnCampaignModel ccm : campaignModelList) {
            Log.d(TAG, "onChildAdded: " + ccm.getTitle());
        }
        Log.d(TAG, "onChildAdded:size " + campaignModelList.size());
        listAdapter.setData(campaignModelList);
        listAdapter.notifyDataSetChanged();
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
