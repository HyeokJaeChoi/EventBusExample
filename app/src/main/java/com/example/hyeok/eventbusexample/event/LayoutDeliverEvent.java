package com.example.hyeok.eventbusexample.event;

import android.widget.LinearLayout;

public class LayoutDeliverEvent {
    private LinearLayout linearLayout;

    public LayoutDeliverEvent(LinearLayout linearLayout){
        this.linearLayout = linearLayout;
    }

    public LinearLayout getLinearLayout() {
        return linearLayout;
    }
}
