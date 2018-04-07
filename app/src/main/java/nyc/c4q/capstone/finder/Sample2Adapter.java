package nyc.c4q.capstone.finder;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import nyc.c4q.capstone.models.DBReturnCampaignModel;

import static nyc.c4q.capstone.MainActivity.firebaseDataHelper;

public class Sample2Adapter extends MapViewPager.Adapter implements ValueEventListener {
    public static final String[] PAGE_TITLES = {"England", "France", "Spain",
            "Portugal", "Italy", "Belgium"};

    public static final String[] ENGLAND_TITLES = {"London"};
    public static final String[] FRANCE_TITLES = {"Paris"};
    public static final String[] SPAIN_TITLES = {"Barcelona", "Madrid", "Valencia"};
    public static final String[] PORTUGAL_TITLES = {};
    public static final String[] ITALY_TITLES = {"Milan", "Rome"};
    public static final String[] BELGIUM_TITLES = {"Brussels"};
    private static final String TAG = "GoogleLastKnownLocation";

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location lastKnownLocation;
    private Context context;
    private List<DBReturnCampaignModel> adapterCampaignModels = new ArrayList<>();

    public static final CameraPosition LONDON
            = CameraPosition.fromLatLngZoom(new LatLng(51.5287352, -0.381784), 6f);
    public static final CameraPosition PARIS
            = CameraPosition.fromLatLngZoom(new LatLng(48.859, 2.2074722), 6f);
    public static final CameraPosition BARCELONA
            = CameraPosition.fromLatLngZoom(new LatLng(41.3948976, 2.0787274), 6f);
    public static final CameraPosition MADRID
            = CameraPosition.fromLatLngZoom(new LatLng(40.4381311, -3.8196227), 6f);
    public static final CameraPosition VALENCIA
            = CameraPosition.fromLatLngZoom(new LatLng(39.4079665, -0.5015975), 6f);
    public static final CameraPosition MILAN
            = CameraPosition.fromLatLngZoom(new LatLng(45.4628329, 9.107692), 6f);
    public static final CameraPosition ROME
            = CameraPosition.fromLatLngZoom(new LatLng(41.9102415, 12.3959121), 6f);
    public static final CameraPosition BRUSSELS
            = CameraPosition.fromLatLngZoom(new LatLng(50.8550625, 4.3053499), 6f);

    private LinkedList<CameraPosition> england;
    private LinkedList<CameraPosition> france;
    private LinkedList<CameraPosition> spain;
    private LinkedList<CameraPosition> portugal;
    private LinkedList<CameraPosition> italy;
    private LinkedList<CameraPosition> belgium;

    public Sample2Adapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        // camera positions
        england = new LinkedList<>();
        france = new LinkedList<>();
        spain = new LinkedList<>();
        portugal = new LinkedList<>();
        italy = new LinkedList<>();
        belgium = new LinkedList<>();

        england.add(LONDON);
        france.add(PARIS);
        spain.add(BARCELONA);
        spain.add(MADRID);
        spain.add(VALENCIA);
        italy.add(MILAN);
        italy.add(ROME);
        belgium.add(BRUSSELS);
    }

    @Override
    public int getCount() {
        return adapterCampaignModels.size();
    }

    @Override
    public Fragment getItem(int position) {
        return Sample2Fragment.newInstance(position);
    }

    @Override
    public CameraPosition getCameraPosition(int position) {
        DBReturnCampaignModel dbReturnCampaignModel = adapterCampaignModels.get(position);
        LatLng positionLatLng = LocationHelper.getLocationFromAddress(context, dbReturnCampaignModel.getAddress());
        CameraPosition cameraPosition = CameraPosition.fromLatLngZoom(positionLatLng, 6f);
        return cameraPosition;
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
            LatLng currentCampaignLocation = LocationHelper.getLocationFromAddress(context, dbReturnCampaignModel.getAddress());
            if (currentCampaignLocation != null) {
                adapterCampaignModels.add(dbReturnCampaignModel);
            }
        }
    }

    private void orderMarkers(Location deviceLocation) {
        for (DBReturnCampaignModel dbReturnCampaignModel : adapterCampaignModels) {
            if (deviceLocation != null) {
                if (lastKnownLocation != null) {
                    Log.d(TAG, "onComplete: " + lastKnownLocation.getLatitude() + " " + lastKnownLocation.getLongitude());
                }
                LatLng latLng = new LatLng(deviceLocation.getLatitude(), deviceLocation.getLongitude());
                LatLng currentCampaignLatLng = LocationHelper.getLocationFromAddress(context, dbReturnCampaignModel.getAddress());
                if (!(SphericalUtil.computeDistanceBetween(currentCampaignLatLng, latLng) <= 5000)) {
                    adapterCampaignModels.remove(dbReturnCampaignModel);
                }
            }
        }

        cameraPositionList(adapterCampaignModels);
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
                        orderMarkers(lastKnownLocation);
                    }
                }
            }
        });
    }

    private void cameraPositionList(List<DBReturnCampaignModel> adapterCampaignModels) {

    }


}
