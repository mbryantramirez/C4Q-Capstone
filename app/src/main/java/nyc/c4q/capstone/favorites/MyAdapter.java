package nyc.c4q.capstone.favorites;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by c4q on 3/19/18.
 */

public class MyAdapter extends FragmentPagerAdapter {
    Context context=null;
    public MyAdapter(Context context,FragmentManager fm) {
        super(fm);
        this.context=context;

    }


    @Override
    public Fragment getItem(int position) {
        return(FavoritesFragment.newInstance(position));
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
