package com.example.hyeok.eventbusexample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hyeok.eventbusexample.R;

public class ImagePagerAdapter extends PagerAdapter {

    private int[] imageResources = {R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4};
    private Context context;

    public ImagePagerAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageResources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((ConstraintLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.pager_item, container, false);
        ImageView imageView = (ImageView)view.findViewById(R.id.viewpager_image);

        imageView.setImageResource(imageResources[position % imageResources.length]);
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
