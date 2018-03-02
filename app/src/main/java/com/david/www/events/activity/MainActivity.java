package com.david.www.events.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.david.www.events.R;
import com.david.www.events.adapter.ViewPager;
import com.david.www.events.model.Data;

public class MainActivity extends AppCompatActivity{
    private static android.support.v4.view.ViewPager viewPager;
    private ViewPager viewPagerAdapter;
    private static TabLayout tabLayout;
    public static FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        fm = this.getSupportFragmentManager();

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPager(this.getSupportFragmentManager());
        viewPagerAdapter.addFragments(new Restaurant(),"Restaurant");
        viewPagerAdapter.addFragments(new Events(),"Events");
        viewPagerAdapter.addFragments(new MapsActivity(),"Map");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void goToLocation (String points){
        try{
            Data data = new Data();
            data.setMapPoints(points.split("-"));
            data.setPage("Restaurant");

            tabLayout.setScrollPosition(2,0f,true);
            viewPager.setCurrentItem(2);
        }catch (Exception e){}
    }
}
