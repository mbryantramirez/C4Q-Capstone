package nyc.c4q.capstone.feed;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.SwipeDirection;

import java.util.List;

import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.R;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFeedFragment extends Fragment implements ValueEventListener {

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

        firebaseDataHelper.getCampaignDatbaseRefrence().addValueEventListener(this);

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
                Log.d(CARD_TAG, "onCardClicked: " + dbReturnCampaignModel.getTitle());
                firebaseDataHelper.getDatabaseReference().child("funded").child(dbReturnCampaignModel.getTitle()).setValue(dbReturnCampaignModel);
            }
        });
    }


    private void loadTextFromList(List<DBReturnCampaignModel> currentCampaignsList) {
        if (getActivity() != null) {
            feedCardAdapter = new FeedCardAdapter(getActivity());
            feedCardAdapter.addAll(currentCampaignsList);
            cardStackView.setAdapter(feedCardAdapter);
        }
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        loadTextFromList(firebaseDataHelper.getCampaignsList(dataSnapshot));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

}
