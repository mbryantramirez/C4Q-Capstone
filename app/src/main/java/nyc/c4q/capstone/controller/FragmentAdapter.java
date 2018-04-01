package nyc.c4q.capstone.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import nyc.c4q.capstone.favorites.FavoritesFragment;
import nyc.c4q.capstone.finder.FinderFragment;
import nyc.c4q.capstone.campaign.CreateCampaignFragment;
import nyc.c4q.capstone.feed.MainFeedFragment;

import static android.app.PendingIntent.getActivity;

/**
 * Created by c4q on 3/15/18.
 */

public class FragmentAdapter extends FragmentStatePagerAdapter {
    private int numberOfTabs;

    Fragment Home, Feed, Create, Favorites;


    public FragmentAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numberOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (Home == null) {
                    Home = new MainFeedFragment();
                }
                return Home;
            case 1:
                return new FinderFragment();
            case 2:
                return new CreateCampaignFragment();
            case 3:
                return new FavoritesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }


}
