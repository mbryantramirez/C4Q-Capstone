package nyc.c4q.capstone.payment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import nyc.c4q.capstone.R;

public class PaymentBottomDialogFragment extends BottomSheetDialogFragment {
    String sampleString;
    TextView paymentTV;
    Button button;
    ProgressBar progressBar;
    ImageView completedTransaction;
    long timerforprogressbar = (long) 4000;

    static PaymentBottomDialogFragment newInstance(String string) {
        PaymentBottomDialogFragment paymentBottomDialogFragment = new PaymentBottomDialogFragment();
        Bundle args = new Bundle();
        args.putString("String", string);
        paymentBottomDialogFragment.setArguments(args);
        return paymentBottomDialogFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sampleString = getArguments().getString("string");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.payment_bottom_sheet_modal, container, false);
        button = rootView.findViewById(R.id.pay_nav_back_button);
        progressBar = rootView.findViewById(R.id.pay_process_pb);
        completedTransaction = rootView.findViewById(R.id.transac_complete_iv);
        paymentTV = rootView.findViewById(R.id.pay_complete_tv);
        completedTransaction.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                completedTransaction.setVisibility(View.VISIBLE);
                paymentTV.setText("Thank You For Your Contribution");
            }

        }, timerforprogressbar);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavUtils.navigateUpFromSameTask(getActivity());
            }
        });

        return rootView;
    }
}
