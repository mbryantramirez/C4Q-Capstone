package nyc.c4q.capstone.favorites;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Objects;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

import static android.content.ContentValues.TAG;

/**
 * Created by c4q on 3/17/18.
 */

public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title;
    private TextView subheading;
    private ImageView imageURl;
    private Button goToBlogPost;
    private Button removeFromFavorites;

    public FavoritesViewHolder(View itemView) {
        //its called itemview because it is a view in the item of the recycler view
        super(itemView);
        title = itemView.findViewById(R.id.paigTitle);
        subheading = itemView.findViewById(R.id.paigSubheading);
        imageURl = itemView.findViewById(R.id.imageUrl);
        goToBlogPost = itemView.findViewById(R.id.favorites_nav_action_button);
            removeFromFavorites = itemView.findViewById(R.id.favorites_clear_action_button);

        itemView.setOnClickListener(this);
    }

    public void onBind(final DBReturnCampaignModel model, final FirebaseDataHelper firebaseDataHelper, boolean isFavorite) {
        Log.d(TAG, "onBind: vieFwholder");
        Picasso.get().load(model.getImageUrl()).into(imageURl);
        String titleText = model.getTitle() + ": ";
        title.setText(titleText);
        subheading.setText(model.getBody());
        if(isFavorite) {
            removeFromFavorites.setVisibility(View.VISIBLE);
            removeFromFavorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uid = ((MainActivity) (Objects.requireNonNull(itemView.getContext()))).getCurrentUserID();
                    firebaseDataHelper.getFavoritesDatabaseReference().child(uid).child(model.getTitle()).removeValue();
                }
            });
        }else{
            removeFromFavorites.setVisibility(View.INVISIBLE);
        }
        goToBlogPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) itemView.getContext()).startSecondFragment(model);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}
