package nyc.c4q.capstone.favorites;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PaignFragment extends Fragment {
    private View rootView;
    private List<String> myStrings= new ArrayList<>();
    private RecyclerView recyclerView;
    //In this fragment Muhaimen will put in the logic to display the list of campaigns
    //


    public PaignFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_paign, container, false);
        recyclerView=rootView.findViewById(R.id.paignRecyclerview);
        LinearLayoutManager layoutManager= new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        PaignAdapter adapter= new PaignAdapter(myStrings);
        recyclerView.setAdapter(adapter);
        // Inflate the layout for this fragment
        return rootView;
    }

}
