package nyc.c4q.capstone.payment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import nyc.c4q.capstone.R;

public class PaymentActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener {
    private Button one, five, ten, twenty, submitButton;
    private CardView googlePay, androidPay, payPal;
    private int amount;
    private String paymentType;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        submitButton = findViewById(R.id.submit_payment);
        submitButton.setOnClickListener(this);
        one = findViewById(R.id.one_button);
        one.setOnTouchListener(this);
        one.setOnClickListener(this);
        five = findViewById(R.id.five_button);
        five.setOnTouchListener(this);
        five.setOnClickListener(this);
        ten = findViewById(R.id.ten_button);
        ten.setOnTouchListener(this);
        ten.setOnClickListener(this);
        twenty = findViewById(R.id.twenty_button);
        twenty.setOnTouchListener(this);
        twenty.setOnClickListener(this);

        googlePay = findViewById(R.id.googlePay_cv);
        googlePay.setOnTouchListener(this);
        googlePay.setOnClickListener(this);
        androidPay = findViewById(R.id.androidpay_cv);
        androidPay.setOnTouchListener(this);
        androidPay.setOnClickListener(this);
        payPal = findViewById(R.id.paypal_cv);
        payPal.setOnTouchListener(this);
        payPal.setOnClickListener(this);

    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view instanceof Button) {
            one.setBackgroundColor(Color.LTGRAY);
            five.setBackgroundColor(Color.LTGRAY);
            ten.setBackgroundColor(Color.LTGRAY);
            twenty.setBackgroundColor(Color.LTGRAY);
        } else if (view instanceof CardView) {
            googlePay.setBackgroundColor(Color.TRANSPARENT);
            androidPay.setBackgroundColor(Color.TRANSPARENT);
            payPal.setBackgroundColor(Color.TRANSPARENT);

        }
        view.setBackgroundColor(Color.GREEN);

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one_button:
                amount = 1;
                break;
            case R.id.five_button:
                amount = 5;
                break;
            case R.id.ten_button:
                amount = 10;
                break;
            case R.id.twenty_button:
                amount = 20;
                break;
            case R.id.googlePay_cv:
                paymentType = "Google Pay";
                break;
            case R.id.androidpay_cv:
                paymentType = "Android Pay";
                break;
            case R.id.paypal_cv:
                paymentType = "PayPal";
                break;
            case R.id.submit_payment:
                Toast.makeText(this, getPaymentToastString(amount, paymentType), Toast.LENGTH_SHORT).show();
        }

    }

    private String getPaymentToastString(int amount, String paymentType){
        return "Thank you for donating " + amount + " through " + paymentType +
                ". Your contribution is greatly appreciated!";
    }

}