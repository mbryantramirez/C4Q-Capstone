package nyc.c4q.capstone.favorites;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import nyc.c4q.capstone.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignPreferencesFragment extends Fragment {
    private View rootView;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Button medButton;
    private Button housingButton;
    private Button clothingButton;
    private Button educationButton;
    private static final String SHARED_PREFS_KEY = "sharedPrefsTesting";


    public CampaignPreferencesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.fragment_campaign_preferences, container, false);
        preferences=rootView.getContext().getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        editor=preferences.edit();
        medButton= rootView.findViewById(R.id.medButton);
        housingButton=rootView.findViewById(R.id.housing_button);
        clothingButton=rootView.findViewById(R.id.clothingButton);
        educationButton=rootView.findViewById(R.id.education_button);
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
        // Inflate the layout for this fragment
        return rootView;
    }

}
