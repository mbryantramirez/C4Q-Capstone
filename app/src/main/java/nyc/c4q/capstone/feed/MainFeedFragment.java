package nyc.c4q.capstone.feed;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.R;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFeedFragment extends Fragment implements ChildEventListener {

    private static final String TAG = "FIREBASE?";
    private static final String CARD_TAG = "CARDSTACKVIEW?";
    private CardStackView cardStackView;
    private FeedCardAdapter feedCardAdapter;

    public MainFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_feed, container, false);

        firebaseDataHelper.getDatabaseReference().addChildEventListener(this);

        cardStackView = rootView.findViewById(R.id.feed_card_stack_view);
        setup();
        return rootView;
    }

    private void setup() {
        cardStackView.setCardEventListener(new CardStackView.CardEventListener() {
            @Override
            public void onCardDragging(float percentX, float percentY) {
                Log.d(CARD_TAG, "onCardDragging:");
            }

            @Override
            public void onCardSwiped(SwipeDirection direction) {
                Log.d(CARD_TAG, "onCardSwipped: " + direction.toString());
                Log.d(CARD_TAG, "topIndex: " + cardStackView.getTopIndex());

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
            }
        });
    }


    private void loadTextFromList(List<DBReturnCampaignModel> currentCampaignsList) {
        feedCardAdapter = new FeedCardAdapter(getContext());
        feedCardAdapter.addAll(currentCampaignsList);
        cardStackView.setAdapter(feedCardAdapter);

    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        loadTextFromList(firebaseDataHelper.getCampaignsList(dataSnapshot));
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
