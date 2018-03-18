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

/**
 * Created by c4q on 3/17/18.
 */

public class PaignAdapter extends RecyclerView.Adapter<PaignViewHolder> {
    private List<DBReturnCampaignModel> modelList= new ArrayList<>();
    //In here Muhaimen will put the logic for the recyclerview for the list of campaigns.

    public PaignAdapter(List<DBReturnCampaignModel> modelList) {
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public PaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView= LayoutInflater.from(parent.getContext()).inflate(R.layout.paign_itemview, parent, false);
        return new PaignViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull PaignViewHolder holder, int position) {
        holder.onBind(modelList.get(position));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
