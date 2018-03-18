package nyc.c4q.capstone.favorites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import nyc.c4q.capstone.CampaignTestModel;
import nyc.c4q.capstone.R;

/**
 * Created by c4q on 3/17/18.
 */

public class PaignViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView goal;
    private TextView imagleURl;
    private TextView creator;
    public PaignViewHolder(View itemView) {
        super(itemView);
        title=itemView.findViewById(R.id.paigTitle);
        goal=itemView.findViewById(R.id.paignGoal);
        imagleURl=itemView.findViewById(R.id.imageUrl);
        creator=itemView.findViewById(R.id.paignCreator);
    }
    public void onBind(String model){
        title.setText(model);

    }
}
