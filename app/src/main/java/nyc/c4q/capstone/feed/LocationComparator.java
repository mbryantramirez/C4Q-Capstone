package nyc.c4q.capstone.feed;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;

import java.util.Comparator;

import nyc.c4q.capstone.finder.LocationHelper;
import nyc.c4q.capstone.models.DBReturnCampaignModel;

/**
 * Created by c4q on 3/20/18.
 */

public class LocationComparator implements Comparator<DBReturnCampaignModel> {

    LatLng currentLocation;
    Context context;

    public LocationComparator(LatLng currentLoc, Context context) {
        this.currentLocation = currentLoc;
        this.context = context;
    }

    @Override
    public int compare(DBReturnCampaignModel campaignOne, DBReturnCampaignModel campaignTwo) {
        LatLng locationOne = LocationHelper.getLocationFromAddress(context, campaignOne.getAddress());
        LatLng locationTwo = LocationHelper.getLocationFromAddress(context, campaignTwo.getAddress());

        double latOne;
        double longOne;
        double latTwo;
        double longTwo;

        if (locationOne == null || locationTwo == null) {
            latOne = 0;
            longOne = 0;
            latTwo = 0;
            longTwo = 0;
        } else {
            latOne = locationOne.latitude;
            longOne = locationOne.longitude;
            latTwo = locationTwo.latitude;
            longTwo = locationTwo.longitude;
        }


        double distanceToPlace1 = distance(currentLocation.latitude, currentLocation.longitude, latOne, longOne);
        double distanceToPlace2 = distance(currentLocation.latitude, currentLocation.longitude, latTwo, longTwo);
        return (int) (distanceToPlace1 - distanceToPlace2);
    }

    public double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double radius = 6378137;   // approximate Earth radius, *in meters*
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(deltaLat / 2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon / 2), 2)));
        return radius * angle;
    }
}
