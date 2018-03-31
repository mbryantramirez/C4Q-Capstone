package nyc.c4q.capstone;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash);
        hideStatusBar();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();


    }

    public void hideStatusBar() {
        View view = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        view.setSystemUiVisibility(uiOptions);

    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
//                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
//                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | // hide nav bar
//                        View.SYSTEM_UI_FLAG_FULLSCREEN | // hide status bar
//                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
}


