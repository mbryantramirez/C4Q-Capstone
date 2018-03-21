package nyc.c4q.capstone.blog;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.blog.BlogPostCampaignViewHolder;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

/**
 * Created by C4Q on 3/20/18.
 */

public class BlogPostCampaignAdapter extends RecyclerView.Adapter<BlogPostCampaignViewHolder> {

    private List<DBReturnCampaignModel> modelList = new ArrayList<>();

    public BlogPostCampaignAdapter(List<DBReturnCampaignModel> modelList) {
        this.modelList = modelList;
    }

    public void setData(List<DBReturnCampaignModel> data) {
        this.modelList = data;
    }


    @NonNull
    @Override
    public BlogPostCampaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.campaigns_i_contributed_to_itemview, parent, false);
        return new BlogPostCampaignViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogPostCampaignViewHolder holder, int position) {
        holder.onBind(modelList.get(position));

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }
}
