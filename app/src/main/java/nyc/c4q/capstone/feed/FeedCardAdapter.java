package nyc.c4q.capstone.feed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

/**
 * Created by c4q on 3/18/18.
 */

public class FeedCardAdapter extends ArrayAdapter<DBReturnCampaignModel> {


    public FeedCardAdapter(@NonNull Context context) {
        super(context, 0);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_campaign_card, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DBReturnCampaignModel dbReturnCampaignModel = getItem(position);

        holder.title.setText(dbReturnCampaignModel.getTitle());
        holder.summary.setText(dbReturnCampaignModel.getSummary());
        holder.imageUrl.setText(dbReturnCampaignModel.getImageUrl());

        return convertView;
    }


    private static class ViewHolder {
        public TextView title;
        public TextView summary;
        public TextView imageUrl;

        public ViewHolder(View view) {
            this.title = view.findViewById(R.id.item_campaign_card_title);
            this.summary = view.findViewById(R.id.item_campaign_card_summary);
            this.imageUrl = view.findViewById(R.id.item_campaign_card_image_url);
        }

    }
}

