package nyc.c4q.capstone;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CampaignPreferenceActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button medButton;
    private Button housingButton;
    private Button clothingButton;
    private Button educationButton;
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_preference);
        getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        preferences=getApplicationContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        editor=preferences.edit();
        medButton= findViewById(R.id.medButton);
        housingButton=findViewById(R.id.housing_button);
        clothingButton=findViewById(R.id.clothingButton);
        educationButton=findViewById(R.id.education_button);
        medButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromMedButton= medButton.getText().toString();
                editor.putString("med",textFromMedButton);

            }
        });
        housingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromHousingButton= housingButton.getText().toString();
                editor.putString("med",textFromHousingButton);

            }
        });
        clothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFromClothingButton= clothingButton.getText().toString();
                editor.putString("med",textFromClothingButton);

            }
        });




    }
}
