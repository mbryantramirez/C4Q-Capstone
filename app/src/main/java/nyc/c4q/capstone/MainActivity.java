package nyc.c4q.capstone;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import nyc.c4q.capstone.controller.FragmentAdapter;
import nyc.c4q.capstone.favorites.CampaignPreferencesFragment;
import nyc.c4q.capstone.feed.MainFeedFragment;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TabLayout tabLayout;
    public static FirebaseDataHelper firebaseDataHelper;
    private int currentPosition;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FragmentAdapter fragmentAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        Log.d(TAG, "user name is: " + currentUser.getUid());
        setContentView(R.layout.activity_main);
//        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
//        getSupportActionBar().setTitle("village");
        tabLayout = findViewById(R.id.main_tab_layout);
        firebaseDataHelper = new FirebaseDataHelper();
        firebaseDataHelper.getDatabaseReference().keepSynced(true);
        firebaseDataHelper.getCampaignDatbaseRefrence().keepSynced(true);
        tabLayoutSetup();
    }

    private void tabLayoutSetup() {
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_home_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_map_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_add_box_black_24dp));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.main_viewpager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.options_menu_me:
                Toast.makeText(this, "me", Toast.LENGTH_SHORT).show();
                break;
            case R.id.options_menu_about:
                Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
                break;
            case R.id.options_menu_logout:
                signOut();
                Toast.makeText(this, "logout successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.options_menu_refresh_feed:
                if (fragmentAdapter.getItem(currentPosition) instanceof MainFeedFragment) {
                    Fragment activeFragment = fragmentAdapter.getItem(currentPosition);
                    ((MainFeedFragment) activeFragment).doSomething();
                }
            case R.id.pref:
                CampaignPreferencesFragment fragment= new CampaignPreferencesFragment();
                FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container,fragment);
                fragmentTransaction.commit();

            default:
                Log.e(TAG, "nothing clicked");
        }
        return true;
    }

    private void signOut() {
        auth.signOut();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}
