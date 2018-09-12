package com.example.hyeok.eventbusexample.adapter;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.hyeok.eventbusexample.R;

import java.util.List;

public class ColorRecyclerAdapter extends RecyclerView.Adapter<ColorRecyclerAdapter.ColorViewHolder> {

    private List<Integer> colorItems;

    public ColorRecyclerAdapter(List<Integer> colorItems){
        this.colorItems = colorItems;
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder{
        private View colorView;
        boolean isPostDelayed = false;
        final Handler longPressHandler = new Handler(Looper.getMainLooper());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                removeItem();
            }
        };

        public ColorViewHolder(@NonNull View itemView) {
            super(itemView);
            colorView = (View)itemView.findViewById(R.id.color_view);
            colorView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN :
                            isPostDelayed = longPressHandler.postDelayed(runnable, 3000);
                            Log.d("ColorRecylcerAdapter", isPostDelayed + "");
                            break;
                        case MotionEvent.ACTION_UP :
                        case MotionEvent.ACTION_MOVE :
                            v.performClick();
                            longPressHandler.removeCallbacks(runnable);
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
