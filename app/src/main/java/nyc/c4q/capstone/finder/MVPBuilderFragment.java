package nyc.c4q.capstone.finder;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MVPBuilderFragment extends Fragment implements MapViewPager.Callback {

    private ViewPager viewPager;
    private MapViewPager mvp;
    private SupportMapFragment mapFragment;

    public MVPBuilderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_mvpbuilder, container, false);
        viewPager = rootview.findViewById(R.id.viewPager);
        viewPager.setPageMargin(Utils.dp(getActivity(), 18));
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
        }
        Utils.setMargins(viewPager, 0, 0, 0, Utils.getNavigationBarHeight(getActivity()));

        mvp = new MapViewPager.Builder(getActivity())
                .mapFragment(mapFragment)
                .viewPager(viewPager)
                .position(2)
                .adapter(new Sample2Adapter(getActivity().getSupportFragmentManager(), getActivity()))
                .callback(this)
                .build();

        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        return rootview;
    }

    @Override
    public void onMapViewPagerReady() {

    }


}
