package com.blogspot.yourfavoritekaisar.ems.NewsInet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.blogspot.yourfavoritekaisar.ems.adapter.MyPagerAdapter;
import com.blogspot.yourfavoritekaisar.ems.R;
import com.google.android.material.tabs.TabLayout;

public class PagerNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_news);

        ViewPager viewPager = findViewById(R.id.pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }


}
