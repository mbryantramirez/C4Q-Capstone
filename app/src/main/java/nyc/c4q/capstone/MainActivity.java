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

import java.util.HashMap;

import nyc.c4q.capstone.blog.BlogPostFragment;
import nyc.c4q.capstone.controller.FragmentAdapter;
import nyc.c4q.capstone.favorites.CampaignPreferencesFragment;
import nyc.c4q.capstone.feed.MainFeedFragment;
import nyc.c4q.capstone.models.DBReturnCampaignModel;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TabLayout tabLayout;
    public static FirebaseDataHelper firebaseDataHelper;
    private int currentPosition;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private FragmentAdapter fragmentAdapter;
    private ViewPager viewPager;
    HashMap<Integer,String>fragmentMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        Log.d(TAG, "user name is: " + currentUser.getUid());
        setContentView(R.layout.activity_main);
        setActionBarTitle("village");
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
        viewPager = findViewById(R.id.main_viewpager);
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(4);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPosition = tab.getPosition();
                viewPager.setCurrentItem(tab.getPosition());
                switch(currentPosition){
                    case 0: setActionBarTitle("village");
                    break;
                    case 1: setActionBarTitle("location");
                    break;
                    case 2: setActionBarTitle("create a campaign");
                    break;
                    case 3: setActionBarTitle("my favorites");
                }
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
                break;
            case R.id.pref:
                CampaignPreferencesFragment fragment = new CampaignPreferencesFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_container, fragment);
                fragmentTransaction.addToBackStack("Campaigns");
                setActionBarTitle("preferences");
                fragmentTransaction.commit();
                break;
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


    public void startSecondFragment(DBReturnCampaignModel dbReturnCampaignModel) {
        Log.d(TAG, "onCardLongClicked: " + dbReturnCampaignModel.getTitle());
        BlogPostFragment blogPostFragment = new BlogPostFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Title", dbReturnCampaignModel.getTitle());
        blogPostFragment.setArguments(bundle);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, blogPostFragment);
        fragmentTransaction.addToBackStack("Blogs");
        fragmentTransaction.commit();
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
    }
}
