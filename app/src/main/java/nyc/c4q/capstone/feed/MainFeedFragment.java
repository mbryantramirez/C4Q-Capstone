package nyc.c4q.capstone.feed;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.Collections;
import java.util.List;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.R;

import static android.content.Context.MODE_PRIVATE;
import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFeedFragment extends Fragment implements ValueEventListener {

    private static final String TAG = "FIREBASE?";
    private SharedPreferences preferences;
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";
    private static final String CARD_TAG = "CARDSTACKVIEW?";
    private static final String LOCATION_TAG = "LASTKNOWNLOCATION?";
    private CardStackView cardStackView;
    private FeedCardAdapter feedCardAdapter;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;
    private Location getLocationFromMenu;
    private View rootView;

    public MainFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("village");
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_main_feed, container, false);

        firebaseDataHelper.getCampaignDatbaseReference().addValueEventListener(this);

        cardStackView = rootView.findViewById(R.id.feed_card_stack_view);

        this.context = container.getContext();

        setup();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        preferences = getActivity().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }


    private void setup() {
        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.d(CARD_TAG, "onCardDragging:" + percentX + " "+ percentY);
//                Toast.makeText(rootView.getContext(), "like", Toast.LENGTH_LONG).show();
                if (percentX>=.9 &&percentY>=.243) {
                    Toast toast = new Toast(rootView.getContext());
                    ImageView view = new ImageView(rootView.getContext());
                    view.setImageResource(R.drawable.heart_icon);
                    view.setMaxHeight(15);
                    view.setMaxWidth(15);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(view);
                    toast.show();
                }
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d(CARD_TAG, "onCardSwipped: " + direction.toString());
                Log.d(CARD_TAG, "topIndex: " + cardStackView.getTopIndex());
                if (direction == SwipeDirection.Right) {
                    Log.d(CARD_TAG, "onCardSwippedRight: " + "add to favorites");
                    Log.d(CARD_TAG, "topIndex: " + cardStackView.getTopIndex());
                    int pos = cardStackView.getTopIndex() - 1;
                    DBReturnCampaignModel dbReturnCampaignModel = feedCardAdapter.getItem(pos);



                    Log.d(CARD_TAG, "onCardSwippedRight: " + dbReturnCampaignModel.getTitle());
                    firebaseDataHelper.getDatabaseReference().child("favorites").child(dbReturnCampaignModel.getTitle()).setValue(dbReturnCampaignModel);

                }
            }

            @Override
            public void onCardReversed() {
                Log.d(CARD_TAG, "onCardReversed: ");

            }

            @Override
            public void onCardMovedToOrigin() {
                Log.d(CARD_TAG, "onCardReversed");

            }

            @Override
            public void onCardClicked(int index) {
                Log.d(CARD_TAG, "onCardClicked");
                Log.d(CARD_TAG, "topIndex: " + cardStackView.getTopIndex());
                int pos = cardStackView.getTopIndex();
                DBReturnCampaignModel dbReturnCampaignModel = feedCardAdapter.getItem(pos);
                ((MainActivity) getActivity()).startSecondFragment(dbReturnCampaignModel);
            }
        });
    }


    private void loadTextFromList(List<DBReturnCampaignModel> currentCampaignsList, Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        Collections.sort(currentCampaignsList, new LocationComparator(latLng, getActivity()));

        if (getActivity() != null) {
            feedCardAdapter = new FeedCardAdapter(getActivity());
            feedCardAdapter.addAll(currentCampaignsList);
            cardStackView.setAdapter(feedCardAdapter);
        }
    }

    public void doSomething() {
        Toast.makeText(context, "Refreshed Feed", Toast.LENGTH_LONG).show();
        firebaseDataHelper.getDatabaseReference().addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(final DataSnapshot dataSnapshot) {
        final String textFromPref = preferences.getString("Keyword", "");
        Log.d(TAG, "onDataChange: " + textFromPref);
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    getLocationFromMenu = location;
                    Log.d(LOCATION_TAG, "onSuccess: " + "Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
                    loadTextFromList(firebaseDataHelper.getCampaignsList(dataSnapshot, textFromPref), location);

                }
            }
        });
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }


}
