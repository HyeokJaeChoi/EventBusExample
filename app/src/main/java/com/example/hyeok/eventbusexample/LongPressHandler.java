package com.example.hyeok.eventbusexample;

import android.os.Handler;
import android.os.Message;

import com.example.hyeok.eventbusexample.adapter.ColorRecyclerAdapter;

public class LongPressHandler extends Handler {

    public ColorRecyclerAdapter.ColorViewHolder colorViewHolder;

    public LongPressHandler(ColorRecyclerAdapter.ColorViewHolder colorViewHolder){
        this.colorViewHolder = colorViewHolder;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 3000 :
                colorViewHolder.removeItem();
                break;
        }
    }
}
