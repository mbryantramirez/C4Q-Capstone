package nyc.c4q.capstone.blog;


import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.payment.PaymentActivity;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment implements ChildEventListener {

    public static final String TAG = "firebase?";

    View rootView;
    private TextView userImage;
    private TextView blogPost;
    private TextView storyTitle;
    private Button donateButton;
    private RecyclerView recyclerView;


    public BlogPostFragment() {
        // Required empty public constructor
    }

    //


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blog_post, container, false);
        userImage = rootView.findViewById(R.id.blog_post_imageview);
        blogPost = rootView.findViewById(R.id.blog_post_textview);
        storyTitle = rootView.findViewById(R.id.blog_post_title);
        donateButton = rootView.findViewById(R.id.donate_button);
        recyclerView = rootView.findViewById(R.id.blog_post_recyclerView);

//        firebaseDataHelper.getCampaignDatbaseRefrence().addChildEventListener(this);
        firebaseDataHelper.getDatabaseReference().addChildEventListener(this);

        blogPost.setMovementMethod(new ScrollingMovementMethod());

//        Bundle bundle = getArguments();
//        String title = bundle.getString("title");

        donateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), PaymentActivity.class);
                startActivity(intent);
            }
        });
        return rootView;


    }

    private void loadTextFromList(DBReturnCampaignModel model) {
        userImage.setText(model.getTitle());
//            blogPost.setText(DBReturnCampaignModel.getIntro() + DBReturnCampaignModel.getBody());
    }


    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        Log.d(TAG, "query" + dataSnapshot.hasChild("eight"));
        if (dataSnapshot.hasChild("eight")) {
            Log.d(TAG, "datasnapshot:" + dataSnapshot.child("eight"));
            DBReturnCampaignModel DBReturnCampaignModel = dataSnapshot.child("eight").getValue(DBReturnCampaignModel.class);
            loadTextFromList(DBReturnCampaignModel);

        }

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

