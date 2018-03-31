package nyc.c4q.capstone.favorites;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import nyc.c4q.capstone.R;

public class PreferenceActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "error";
    private Button medical, housing, education, business, volunteer, events, community, sports, tragedies, save_preferences;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        medical = findViewById(R.id.medButton);
        medical.setOnClickListener(this);

        housing = findViewById(R.id.housing_button);
        housing.setOnClickListener(this);
        education = findViewById(R.id.education_button);
        education.setOnClickListener(this);
        business = findViewById(R.id.business);
        business.setOnClickListener(this);
        volunteer = findViewById(R.id.volunteerButton);
        volunteer.setOnClickListener(this);
        events = findViewById(R.id.eventsButton);
        events.setOnClickListener(this);
        community = findViewById(R.id.commButton);
        community.setOnClickListener(this);
        sports = findViewById(R.id.sports_button);
        sports.setOnClickListener(this);
        tragedies = findViewById(R.id.tragedy_button);
        tragedies.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, view.isPressed()+"");

       if (i == 0) {
           i = 1;
           view.setBackground(getResources().getDrawable(R.drawable.second_preference_round_button));
       }
       else {
           i = 0;
           view.setBackground(getResources().getDrawable(R.drawable.preference_round_button));
       }

    }
}
