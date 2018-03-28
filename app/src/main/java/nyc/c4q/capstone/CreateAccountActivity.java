package nyc.c4q.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateAccountActivity extends AppCompatActivity {
    private TextView firstName_tv, lastName_tv, password_tv, email_tv, number_tv;
    private EditText firstName_et, lastName_et, password_et, email_et, number_et;
    private Button upload_bt, submit_bt;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstName_et = findViewById(R.id.name_et);
        lastName_et = findViewById(R.id.last_name_et);
        password_et = findViewById(R.id.pw_et);
        email_et = findViewById(R.id.number_et);
        upload_bt = findViewById(R.id.uploadPic_button);
        submit_bt = findViewById(R.id.submit_payment);
        profilePic = findViewById(R.id.icon_iv);
    }
}
