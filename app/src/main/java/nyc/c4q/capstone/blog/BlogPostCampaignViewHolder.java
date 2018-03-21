package nyc.c4q.capstone.blog;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

/**
 * Created by C4Q on 3/20/18.
 */

public class BlogPostCampaignViewHolder extends RecyclerView.ViewHolder {

    private TextView image;

    public BlogPostCampaignViewHolder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.my_contributions_image);
    }

    public void onBind(DBReturnCampaignModel model){
        image.setText(model.getImageUrl());

    }
}
