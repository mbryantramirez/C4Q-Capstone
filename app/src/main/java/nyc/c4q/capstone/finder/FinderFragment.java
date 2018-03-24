package nyc.c4q.capstone.finder;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


/**
 * A simple {@link Fragment} subclass.
 */

public class FinderFragment extends Fragment implements OnMapReadyCallback, ViewPager.OnPageChangeListener, ValueEventListener {

    private View rootView;
    private MapView mapView;
    private GoogleMap myGoogleMap;
    private HashMap<MarkerOptions, DBReturnCampaignModel> campaignHashMap = new HashMap<>();
    FusedLocationProviderClient fusedLocationProviderClient;

    public FinderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = rootView.findViewById(R.id.finder_map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        centerMapOnMyLocation();
        firebaseDataHelper.getCampaignDatbaseRefrence().addValueEventListener(this);
        return rootView;
    }

    private void centerMapOnMyLocation() {
//        myGoogleMap.setMyLocationEnabled(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = rootView.findViewById(R.id.finder_map_view);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myGoogleMap = googleMap;
        myGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        myGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        myGoogleMap.setMyLocationEnabled(true);
        for(Map.Entry<MarkerOptions, DBReturnCampaignModel> entry: campaignHashMap.entrySet()){
         MarkerOptions marker = entry.getKey();
         myGoogleMap.addMarker(marker);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        setMapMarkers(firebaseDataHelper.getCampaignsList(dataSnapshot," "));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void setMapMarkers(List<DBReturnCampaignModel> campaignModels) {
        for (DBReturnCampaignModel dbReturnCampaignModel : campaignModels) {
            LatLng currentLocation = LocationHelper.getLocationFromAddress(getActivity(), dbReturnCampaignModel.getAddress());
            MarkerOptions marker =new MarkerOptions().position(new LatLng(currentLocation.latitude, currentLocation.longitude));
            campaignHashMap.put(marker, dbReturnCampaignModel);
        }
    }

}
