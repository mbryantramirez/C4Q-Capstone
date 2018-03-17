package nyc.c4q.capstone.favorites;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nyc.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaignFragment extends Fragment {
    //In this fragment Muhaimen will put in the logic to display the list of campaigns
    //


    public PaignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

}
