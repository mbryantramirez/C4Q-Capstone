package nyc.c4q.capstone.finder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.mapviewpager.MapViewPager;

import nyc.c4q.capstone.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MVPFinderFragment extends Fragment implements MapViewPager.Callback {

    private MapViewPager mapViewPager;
    private ViewPager viewPager;

    public MVPFinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mvpfinder, container, false);

        mapViewPager = rootView.findViewById(R.id.finder_mvp);

        FinderAdapter finderAdapter = new FinderAdapter(getActivity().getSupportFragmentManager());
        mapViewPager.start(getActivity(), finderAdapter,this);

        return rootView;
    }


    @Override
    public void onMapViewPagerReady() {

    }
}
