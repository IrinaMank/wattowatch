package com.example.irina.wtw.cells;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

;import com.example.irina.wtw.R;

public class ProfileTextCell extends FrameLayout {
    private TextView headerTextView;
    private TextView valueTextView;

    public ProfileTextCell(Context context) {
        super(context);
        headerTextView = new TextView(context);
        headerTextView.setTextSize(16);
        headerTextView.setGravity(Gravity.RIGHT);
        addView(headerTextView);

        valueTextView = new TextView(context);
        valueTextView.setTextSize(16);
        valueTextView.setGravity(Gravity.RIGHT);
        addView(valueTextView);
    }

    public TextView getHeaderTextView() {
        return headerTextView;
    }

    public TextView getValueTextView() {
        return valueTextView;
    }

    public void setHeader(String header) {
        headerTextView.setText(header);
    }

    public void setValue(String value) {
        valueTextView.setText(value);
    }
}
