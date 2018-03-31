package nyc.c4q.capstone.favorites;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import nyc.c4q.capstone.R;

public class PreferenceActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener{
    private Button medical, housing, education, business, volunteer, events, community, sports, tragedies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        setActionBarTitle("my preferences");

        medical = findViewById(R.id.medButton);
        medical.setOnClickListener(this);
        medical.setOnTouchListener(this);
        housing = findViewById(R.id.housing_button);
        housing.setOnClickListener(this);
        housing.setOnTouchListener(this);
        education = findViewById(R.id.education_button);
        education.setOnClickListener(this);
        education.setOnTouchListener(this);
        business = findViewById(R.id.business);
        business.setOnClickListener(this);
        business.setOnTouchListener(this);
        volunteer = findViewById(R.id.volunteerButton);
        volunteer.setOnClickListener(this);
        volunteer.setOnTouchListener(this);
        events = findViewById(R.id.eventsButton);
        events.setOnClickListener(this);
        events.setOnTouchListener(this);
        community = findViewById(R.id.commButton);
        community.setOnClickListener(this);
        community.setOnTouchListener(this);
        sports = findViewById(R.id.sports_button);
        sports.setOnClickListener(this);
        sports.setOnTouchListener(this);
        tragedies = findViewById(R.id.tragedy_button);
        tragedies.setOnClickListener(this);
        tragedies.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        view.setBackgroundColor(getResources().getColor(R.color.darkBlue));

        return false;
    }

    @Override
    public void onClick(View view) {



    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.options_menu, menu);
            return true;
    }
}
