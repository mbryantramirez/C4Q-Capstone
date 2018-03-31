package nyc.c4q.capstone.favorites;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static android.content.ContentValues.TAG;

/**
 * Created by c4q on 3/17/18.
 */

public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView title;
    private TextView goal;
    private ImageView imagleURl;
    private TextView creator;

    public FavoritesViewHolder(View itemView) {
        //its called itemview because it is a view in the item of the recycler view
        super(itemView);
        title = itemView.findViewById(R.id.paigTitle);
        goal = itemView.findViewById(R.id.paignGoal);
        imagleURl = itemView.findViewById(R.id.imageUrl);
        creator = itemView.findViewById(R.id.paignCreator);

        itemView.setOnClickListener(this);
    }

    public void onBind(final DBReturnCampaignModel model) {
        Log.d(TAG, "onBind: viewholder");
        title.setText(model.getTitle());
        goal.setText(model.getGoal());
        Picasso.get().load(model.getImageUrl()).resize(100, 100).into(imagleURl);
        creator.setText(model.getCreatorID());
        //I need to clean this code so It can be better implemented elsewhere in the app.
        itemView.setOnClickListener(new View.OnClickListener() {
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
