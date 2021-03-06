package nyc.c4q.capstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.Objects;

import nyc.c4q.capstone.models.UserAccount;
import nyc.c4q.capstone.utils.FirebaseDataHelper;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    private TextView firstName_tv, lastName_tv, password_tv, email_tv, number_tv;
    private EditText firstName_et, lastName_et, password_et, passwordConfirm_et, email_et, address_et, number_et;
    private Button upload_bt, submit_bt;
    private ImageView profilePic;
    private FirebaseAuth auth;
    private FirebaseUser newUser;
    private FirebaseDataHelper firebaseDataHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        setUpActionBar();

        firstName_et = findViewById(R.id.name_et);
        lastName_et = findViewById(R.id.last_name_et);
        address_et = findViewById(R.id.address_et);
        password_et = findViewById(R.id.pw_et);
        passwordConfirm_et = findViewById(R.id.pw_confirm_et);
        email_et = findViewById(R.id.email_et);
        upload_bt = findViewById(R.id.uploadPic_button);
        submit_bt = findViewById(R.id.create_account_bt);
        firebaseDataHelper = new FirebaseDataHelper();


        auth = FirebaseAuth.getInstance();

        newUser = auth.getCurrentUser();

        submit_bt.setOnClickListener(this);

    }

    private void updateUI(FirebaseUser newUser) {
        if (newUser != null) {
            Toast.makeText(this, "Your account has been created!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void setUpActionBar() {
        getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.rounded_shape_dark_blue));
        getSupportActionBar().setTitle("Create Account");
    }

    @Override
    public void onClick(View view) {
        createUser();
    }

    private void createUser() {
        final String firstName = firstName_et.getText().toString().trim();
        final String lastName = lastName_et.getText().toString().trim();
        String address = address_et.getText().toString().trim();
        String passwordConfirm = passwordConfirm_et.getText().toString().trim();
        String email = email_et.getText().toString().trim();
        String password = password_et.getText().toString().trim();
        String confirmPassword = passwordConfirm_et.getText().toString().trim();


        if (!password.equals(confirmPassword)) {
            passwordConfirm_et.setError("password does not match");
        }

        if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(address) || TextUtils.isEmpty(passwordConfirm) || TextUtils.isEmpty(email)) {
            firstName_et.setError("required");
            lastName_et.setError("required");
            address_et.setError("required");
            password_et.setError("required");
            email_et.setError("required");
        } else {

            final UserAccount userAccount = new UserAccount(firstName, lastName, address, email);

            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(CreateAccountActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                String uid = newUser.getUid();

                                Log.d(TAG, "new account created?: YES!" + uid);

                                firebaseDataHelper.getUsersDatabaseReference().child(uid).setValue(userAccount);

                                newUser = auth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(firstName + " " + lastName).build();

                                newUser.updateProfile(profileUpdates);

                                updateUI(newUser);

                            } else {
                                Log.d(TAG, "new account created?: NOOOOO, exception is:" + task.getException());
                                Toast.makeText(CreateAccountActivity.this, "Unable to create your account", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }
                        }
                    });
        }
    }
}
