package nyc.c4q.capstone.favorites;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static android.content.ContentValues.TAG;

/**
 * Created by c4q on 3/17/18.
 */

public class FavoritesViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView goal;
    private TextView imagleURl;
    private TextView creator;

    public FavoritesViewHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.paigTitle);
        goal = itemView.findViewById(R.id.paignGoal);
        imagleURl = itemView.findViewById(R.id.imageUrl);
        creator = itemView.findViewById(R.id.paignCreator);
    }

    public void onBind(DBReturnCampaignModel model) {
        Log.d(TAG, "onBind: viewholder");
        title.setText(model.getTitle());
        goal.setText(model.getGoal());
        imagleURl.setText(model.getImageUrl());
        creator.setText(model.getCreatorID());

        //I need to clean this code so It can be better implemented elsewhere in the app.
    }
}
