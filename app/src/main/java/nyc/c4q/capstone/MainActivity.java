package nyc.c4q.capstone;

import android.support.design.widget.TabLayout;
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
import com.google.firebase.database.FirebaseDatabase;

import nyc.c4q.capstone.controller.FragmentAdapter;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TabLayout tabLayout;
    public static FirebaseDataHelper firebaseDataHelper;

    private FirebaseAuth auth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        Log.d(TAG, "user name is: "+ currentUser.getUid());
        setContentView(R.layout.activity_main);
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
        FragmentAdapter fragmentAdapter =
                new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
            default:
                Log.e(TAG, "nothing clicked");
        }
        return true;
    }

    private void signOut() {
        auth.signOut();

    }
}
