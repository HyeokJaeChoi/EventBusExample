package com.example.hyeok.eventbusexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.hyeok.eventbusexample.event.ColorEvent;
import com.example.hyeok.eventbusexample.adapter.ColorRecyclerAdapter;
import com.example.hyeok.eventbusexample.adapter.ImagePagerAdapter;
import com.example.hyeok.eventbusexample.event.LayoutDeliverEvent;
import com.example.hyeok.eventbusexample.R;
import com.example.hyeok.eventbusexample.event.RenderLayoutEvent;
import com.example.hyeok.eventbusexample.util.RandomColorListUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    PagerAdapter viewPagerAdapater;
    ViewPager viewPager;
    ArrayList<Integer> colorItems;
    RecyclerView recyclerView;
    RecyclerView.Adapter colorAdapater;
    RecyclerView.LayoutManager layoutManager;
    LinearLayout colorListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.image_slider);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.image_slider_dot);
        recyclerView = (RecyclerView) findViewById(R.id.color_recyclerview);
        colorItems = new ArrayList<>();
        colorAdapater = new ColorRecyclerAdapter(colorItems);
        viewPagerAdapater = new ImagePagerAdapter(this);
        setSupportActionBar(toolbar);

        appBarLayout.setExpanded(true, true);
        viewPager.setAdapter(viewPagerAdapater);
        tabLayout.setupWithViewPager(viewPager, true);

        EventBus.getDefault().register(this);

        layoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(colorAdapater);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (colorListLayout != null)
                    EventBus.getDefault().postSticky(new RenderLayoutEvent(colorListLayout));
                Intent intent = new Intent(MainActivity.this, ColorListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onColorEvent(ColorEvent colorEvent) {
        Integer colorHexCode = colorEvent.getColorCode();
        Integer amount = colorEvent.getAmount();
        Integer listSize = colorItems.size();

        for (int i = listSize; i < (listSize + amount); i++) {
            colorItems.add(colorHexCode);
        }

        colorItems = RandomColorListUtil.randomizeColorList(colorItems);

        colorAdapater = new ColorRecyclerAdapter(colorItems);
        recyclerView.setAdapter(colorAdapater);
    }

    @Subscribe
    public void onLayoutDeliverEvent(LayoutDeliverEvent layoutDeliverEvent) {
        this.colorListLayout = layoutDeliverEvent.getLinearLayout();
        Log.d("MainActivity", colorListLayout.getChildCount() + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
