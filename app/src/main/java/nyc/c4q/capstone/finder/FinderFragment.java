package nyc.c4q.capstone.finder;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nyc.c4q.capstone.MainActivity;
import nyc.c4q.capstone.R;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;


/**
 * A simple {@link Fragment} subclass.
 */

public class FinderFragment extends Fragment implements OnMapReadyCallback, ViewPager.OnPageChangeListener, ValueEventListener, GoogleMap.OnInfoWindowClickListener {

    private static final String FINDERTAG = "FINDERFRAG?";
    private View rootView;
    private MapView mapView;
    private GoogleMap myGoogleMap;
    private HashMap<MarkerOptions, DBReturnCampaignModel> campaignHashMap = new HashMap<>();
    FusedLocationProviderClient fusedLocationProviderClient;
    private Location lastKnownLocation;
    private static final String TAG = "FUSEDLOCATIONPROVIDER?";

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
        firebaseDataHelper.getCampaignDatbaseReference().addValueEventListener(this);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView = rootView.findViewById(R.id.finder_map_view);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myGoogleMap = googleMap;
        myGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        myGoogleMap.getUiSettings().setZoomGesturesEnabled(true);
        myGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        myGoogleMap.getUiSettings().setMapToolbarEnabled(true);
        myGoogleMap.setMyLocationEnabled(true);
        getDeviceLocation();
        myGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {


            @Override
            public View getInfoWindow(Marker marker) {

                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View rootView = getLayoutInflater().inflate(R.layout.mapwindowlayout, null, true);

                DBReturnCampaignModel markerCampaign = (DBReturnCampaignModel) marker.getTag();

                ImageView mapInfoImage = rootView.findViewById(R.id.infowindow_marker_image);
                TextView mapInfoTitle = rootView.findViewById(R.id.infowindow_marker_title);
                TextView mapInfoAddress = rootView.findViewById(R.id.infowindow_marker_address);
                TextView mapInfoCity = rootView.findViewById(R.id.infowindow_marker_city);
                TextView mapInfoPhoneNumber = rootView.findViewById(R.id.infowindow_marker_phone_num);
                String campaignCity = "";
                String campaignAddress = markerCampaign.getAddress();
                String campaignPhoneNumber = markerCampaign.getPhoneNumber();
                String campaignTitle = markerCampaign.getTitle();
                String campaignImageUrl = markerCampaign.getImageUrl();
                Log.d(TAG, "onLoadInfoWindow: " + campaignAddress);
                Log.d(TAG, "onLoadInfoWindow: " + campaignTitle);
                Log.d(TAG, "onLoadInfoWindow: " + campaignPhoneNumber);
                Log.d(TAG, "onLoadInfoWindow: " + campaignImageUrl);

                String[] address = campaignAddress.split(",");
                Log.d(TAG, "onLoadInfoWindow: " + Arrays.toString(address));

                if (address.length != 0 && address.length>2) {
                    campaignCity = address[1] + address[2];
                }
                mapInfoTitle.setText(campaignTitle);
                mapInfoAddress.setText(address[0]);
                mapInfoCity.setText(campaignCity);
                mapInfoPhoneNumber.setText(campaignPhoneNumber);
                Picasso.get().load(campaignImageUrl).into(mapInfoImage);

                if (!TextUtils.isEmpty(campaignAddress) && !TextUtils.isEmpty(campaignAddress) && !TextUtils.isEmpty(campaignAddress) && TextUtils.isEmpty(campaignImageUrl)) {

                }
                return rootView;
            }
        });
        myGoogleMap.setOnInfoWindowClickListener(this);

    }

    private void getDeviceLocation() {
        final Task locationResult = fusedLocationProviderClient.getLastLocation();
        locationResult.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    lastKnownLocation = (Location) task.getResult();
                    if (lastKnownLocation != null) {
                        Log.d(TAG, "onComplete: " + lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
                        myGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude()), 13));
                        orderMarkers(lastKnownLocation);
                    }
                }
            }
        });
    }

    private void orderMarkers(Location deviceLocation) {
        for (Map.Entry<MarkerOptions, DBReturnCampaignModel> entry : campaignHashMap.entrySet()) {
            MarkerOptions marker = entry.getKey();
            DBReturnCampaignModel dbReturnCampaignModel = entry.getValue();
            if (deviceLocation != null) {
                Marker mapMarker = myGoogleMap.addMarker(marker);
                mapMarker.setTag(entry.getValue());
                if (lastKnownLocation != null) {
                    Log.d(TAG, "onComplete: " + lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
                    Log.d(TAG, "onMapMarkerAdded: " + marker.getPosition().latitude + "/" + mapMarker.getPosition().longitude + " " + dbReturnCampaignModel.getTitle());
                }
                LatLng latLng = new LatLng(deviceLocation.getLatitude(), deviceLocation.getLongitude());

                if (SphericalUtil.computeDistanceBetween(marker.getPosition(), latLng) <= 10000) {
                    mapMarker.setVisible(true);
                } else {
                    mapMarker.setVisible(false);
                }
            }
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
        setMapMarkers(firebaseDataHelper.getCampaignsList(dataSnapshot));
        getDeviceLocation();
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public void setMapMarkers(List<DBReturnCampaignModel> campaignModels) {
        for (DBReturnCampaignModel dbReturnCampaignModel : campaignModels) {
            LatLng currentCampaignLocation = LocationHelper.getLocationFromAddress(getActivity(), dbReturnCampaignModel.getAddress());
            if (currentCampaignLocation != null) {
                MarkerOptions marker = new MarkerOptions().position(new LatLng(currentCampaignLocation.latitude, currentCampaignLocation.longitude));
                campaignHashMap.put(marker, dbReturnCampaignModel);
                Log.d(FINDERTAG, "onNewLocationAddedFromName: " + dbReturnCampaignModel.getTitle() + "/" + dbReturnCampaignModel.getAddress() + "/ " + currentCampaignLocation.latitude + " " + currentCampaignLocation.longitude);
            } else {
                Log.d(FINDERTAG, "onGetLocationFromName: " + "ErrorLoading Location");
            }
        }
        for (Map.Entry<MarkerOptions, DBReturnCampaignModel> entry : campaignHashMap.entrySet()) {
            Log.d(FINDERTAG, entry.getKey() + " " + entry.getValue().getTitle());
        }
    }


    @Override
    public void onInfoWindowClick(Marker marker) {
        DBReturnCampaignModel markerCampaign = (DBReturnCampaignModel) marker.getTag();
        ((MainActivity) getActivity()).startSecondFragment(markerCampaign);
    }
}
//I am going to set up the preferneces fragment so that I can
//click on a tab in the tablayout and then go to than fragment.
