package nyc.c4q.capstone;

import android.app.job.JobInfo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import nyc.c4q.capstone.payment.PaymentActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = LoginActivity.class.getSimpleName();

    private EditText username;
    private EditText password;
    private Button submitButton;
    private Button registerButton;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private ImageView icon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpActionBar();
        setUpViews();
        moveIconClockwise(icon);

        auth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, CreateAccountActivity.class));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = username.getText().toString().trim();
                final String userPassword = password.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(userPassword)) {


                    auth.signInWithEmailAndPassword(email, userPassword)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "sign in: success");
                                        currentUser = auth.getCurrentUser();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Log.d(TAG, "sign in: failure", task.getException());
                                        Toast.makeText(LoginActivity.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }else if(TextUtils.isEmpty(email)){
                    username.setError("required");
                }else if(TextUtils.isEmpty(userPassword)){
                    password.setError("required");
                }
            }


        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        currentUser = auth.getCurrentUser();
        updateUI(currentUser);

    }

    public void setUpViews() {
        username = findViewById(R.id.login_username_edittext);
        password = findViewById(R.id.login_password_edittext);
        submitButton = findViewById(R.id.login_submit_button);
        registerButton = findViewById(R.id.login_register_button);

    }


    //if user is logged in, go to MainActivity
    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            Toast.makeText(this, "welcome back!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }

    }

    public void setUpActionBar() {
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
        getSupportActionBar().setTitle("Login");

    }

    public void moveIconClockwise(View view){
        icon = findViewById(R.id.logo_imageView);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.clockwise);
        icon.startAnimation(animation);

    }

}


/*

 */



