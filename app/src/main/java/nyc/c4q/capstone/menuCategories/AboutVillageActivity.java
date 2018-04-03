package nyc.c4q.capstone.menuCategories;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import nyc.c4q.capstone.R;

public class AboutVillageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_village);
        setUpActionBar("about village");

    }

    public void setUpActionBar(String title){
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
        getSupportActionBar().setTitle(title);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;

    }

}
