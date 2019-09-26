package com.blogspot.yourfavoritekaisar.ems.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.blogspot.yourfavoritekaisar.ems.NewsInet.Pengumuman.PengumumanFragment;
import com.blogspot.yourfavoritekaisar.ems.NewsInet.PeraturanFragment;


public class MyPagerAdapter extends FragmentStatePagerAdapter {


    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new PengumumanFragment();
            case 1: return new PeraturanFragment();
        }
        return null;
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Pengumuman";
            case 1: return "Peraturan";
            default: return null;
        }
    }
}
