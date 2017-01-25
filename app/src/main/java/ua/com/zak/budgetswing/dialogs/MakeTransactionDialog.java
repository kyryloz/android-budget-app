package ua.com.zak.budgetswing.dialogs;

import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.zak.budgetswing.R;

/**
 * @author zak <zak@swingpulse.com>
 */
public class MakeTransactionDialog extends AppCompatDialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_make_transaction, container, false);
//        int title = getArguments().getInt("title");
        getDialog().setTitle("Make transaction");
        return view;
    }
}
