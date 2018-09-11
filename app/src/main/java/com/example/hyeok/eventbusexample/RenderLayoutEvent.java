package com.example.hyeok.eventbusexample;

import android.widget.LinearLayout;

public class RenderLayoutEvent {
    private LinearLayout linearLayout;

    public RenderLayoutEvent(LinearLayout linearLayout){
        this.linearLayout = linearLayout;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
