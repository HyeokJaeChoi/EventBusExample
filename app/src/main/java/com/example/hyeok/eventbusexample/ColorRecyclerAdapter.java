package com.example.hyeok.eventbusexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorRecyclerAdapter extends RecyclerView.Adapter<ColorRecyclerAdapter.ColorViewHolder> {

    private List<Integer> colorItems;

    public ColorRecyclerAdapter(List<Integer> colorItems){
        this.colorItems = colorItems;
    }

    public void appendColorItem(int colorCode){
        colorItems.add(colorCode);
        notifyItemInserted(getItemCount() - 1);
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{
        final LongPressHandler longPressHandler = new LongPressHandler(this);
        public View colorView;

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorView = (View)itemView.findViewById(R.id.color_view);
            colorView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN :
                            longPressHandler.sendEmptyMessageAtTime(3000, event.getDownTime() + 3000L);
                            break;
                        case MotionEvent.ACTION_UP :
                        case MotionEvent.ACTION_MOVE :
                            longPressHandler.removeMessages(3000);
                            break;
                    }
                    return false;
                }
            });
        }

        public void removeItem(){
            colorItems.remove(getLayoutPosition());
            notifyItemRemoved(getLayoutPosition());
        }
    }

    @NonNull
    @Override
    public ColorRecyclerAdapter.ColorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = (View) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.color_item, viewGroup, false);
        ColorViewHolder colorViewHolder = new ColorViewHolder(view);
        return colorViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorRecyclerAdapter.ColorViewHolder viewHolder, int i) {
        viewHolder.colorView.setBackgroundColor(colorItems.get(i));
    }

    @Override
    public int getItemCount() {
        return colorItems.size();
    }
}
