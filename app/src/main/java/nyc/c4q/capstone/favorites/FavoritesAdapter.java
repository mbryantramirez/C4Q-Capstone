package nyc.c4q.capstone.favorites;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

/**
 * Created by c4q on 3/17/18.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
    private List<DBReturnCampaignModel> modelList = new ArrayList<>();
    //In here Muhaimen will put the logic for the recyclerview for the list of campaigns.
    private FirebaseDataHelper firebaseDataHelper;

    public FavoritesAdapter(List<DBReturnCampaignModel> campaignModelList, FirebaseDataHelper firebaseDataHelper) {
        this.modelList = modelList;
        this.firebaseDataHelper = firebaseDataHelper;
    }


    public void setData(List<DBReturnCampaignModel> data) {
        this.modelList = data;
    }


    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites_card, parent, false);
        return new FavoritesViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        holder.onBind(modelList.get(position), firebaseDataHelper);
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}
