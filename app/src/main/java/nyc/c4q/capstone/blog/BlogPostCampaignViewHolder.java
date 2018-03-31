package nyc.c4q.capstone.blog;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

/**
 * Created by C4Q on 3/20/18.
 */

public class BlogPostCampaignViewHolder extends RecyclerView.ViewHolder {

    private ImageView recyclerViewImage;

    public static final String TAG = "recycler view image";

    public BlogPostCampaignViewHolder(View itemView) {
        super(itemView);
        recyclerViewImage = itemView.findViewById(R.id.my_contributions_image);

    }

    public void onBind(DBReturnCampaignModel model) {

        Picasso.get().load(model.getImageUrl()).into(recyclerViewImage);


    }
}
