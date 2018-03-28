package nyc.c4q.capstone.blog;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.payment.PaymentActivity;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlogPostFragment extends Fragment implements ValueEventListener {

    public static final String TAG = "firebase?";

    View rootView;
    private ImageView userImage;
    private TextView blogPost;
    private TextView storyTitle;
    private Button donateButton;
    private RecyclerView recyclerView;

    private List<DBReturnCampaignModel> campaignModelList = new ArrayList<>();
    private BlogPostCampaignAdapter campaignAdapter;


    public BlogPostFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_blog_post, container, false);
        userImage = rootView.findViewById(R.id.blog_post_imageview);
        blogPost = rootView.findViewById(R.id.blog_post_textview);
        storyTitle = rootView.findViewById(R.id.blog_post_title);
        donateButton = rootView.findViewById(R.id.donate_button);
        recyclerView = rootView.findViewById(R.id.blog_post_recyclerView);

        firebaseDataHelper.getDatabaseReference().addValueEventListener(this);
        firebaseDataHelper.getDatabaseReference().child("campaigns").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                campaignModelList = firebaseDataHelper.getCampaignsList(dataSnapshot," ");
                campaignAdapter.setData(campaignModelList);
                campaignAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        blogPost.setMovementMethod(new ScrollingMovementMethod());

        donateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(rootView.getContext(), PaymentActivity.class);
                startActivity(intent);
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
        campaignAdapter = new BlogPostCampaignAdapter(campaignModelList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(campaignAdapter);
    }


    //this is linked to blog feed
    private void loadTextFromFirebase(DBReturnCampaignModel model) {
//        Picasso.get().load(model.getImageUrl()).into(userImage);
        userImage.setImageResource(R.drawable.community_logo);

    }


    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        loadTextFromFirebase(firebaseDataHelper.getCampaign(dataSnapshot));
        campaignAdapter.setData(campaignModelList);
        campaignAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }


}

