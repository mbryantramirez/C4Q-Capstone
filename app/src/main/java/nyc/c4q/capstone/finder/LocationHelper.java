package nyc.c4q.capstone.finder;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by c4q on 3/22/18.
 */

public class LocationHelper {


    private static final String GEOCODER_TAG = "GEOCODER?";

    public static LatLng getLocationFromAddress(Context context, String strAddress) {
        Geocoder coder = new Geocoder(context);
        List<Address> geoResults;
        LatLng p1 = null;
        try {
            geoResults = coder.getFromLocationName(strAddress, 3);
            if (geoResults == null) {
                Log.d(GEOCODER_TAG, "onGetFromLocationName: Error due to address format");
            } else {
                if (geoResults.size() > 0) {
                    Address location = geoResults.get(0);
                    location.getLatitude();
                    location.getLongitude();
                    Log.d(GEOCODER_TAG, "onGetFromLocationName: " + "Lat_" + location.getLatitude() + "Long_" + location.getLongitude());
                    p1 = new LatLng(location.getLatitude(), location.getLongitude());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p1;
    }
}

