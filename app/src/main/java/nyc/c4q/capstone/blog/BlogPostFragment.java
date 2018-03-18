package nyc.c4q.capstone.blog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment implements ChildEventListener {

    View rootView;
    private TextView userImage;
    private TextView blogPost;
    private RecyclerView recyclerView;


    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blog_post, container, false);
        userImage = rootView.findViewById(R.id.blog_post_imageview);
        blogPost = rootView.findViewById(R.id.blog_post_textview);
        recyclerView = rootView.findViewById(R.id.blog_post_recyclerView);

        blogPost.setMovementMethod(new ScrollingMovementMethod());

        return rootView;


    }

    private void loadTextFromList(List<DBReturnCampaignModel> currentCampaignsList) {
        for (DBReturnCampaignModel DBReturnCampaignModel : currentCampaignsList) {
            userImage.setText(DBReturnCampaignModel.getImageUrl());
            blogPost.setText(DBReturnCampaignModel.getIntro() + DBReturnCampaignModel.getBody());
        }

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
