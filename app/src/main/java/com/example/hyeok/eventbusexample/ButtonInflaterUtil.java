package com.example.hyeok.eventbusexample;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ButtonInflaterUtil {

    public static void inflateNewButton(Context context, LinearLayout linearLayout, String selectedColor, Integer amount){
        Button button = new Button(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int marginInDp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, context.getResources().getDisplayMetrics());

        layoutParams.setMargins(0, 0, 0, marginInDp);
        button.setLayoutParams(layoutParams);
        button.setText(selectedColor + " " + Integer.toString(amount));

        linearLayout.addView(button);
    }
}
