package nyc.c4q.capstone.payment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nyc.c4q.capstone.R;

public class PaymentBottomDialogFragment extends BottomSheetDialogFragment {
    String sampleString;

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
        TextView textView = rootView.findViewById(R.id.bottom_dialog_text);
        return rootView;
    }
}
