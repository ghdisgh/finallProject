package com.example.iyeonju.finallproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class Main2Activity extends FragmentPagerAdapter {

    int tabCount;

    public Main2Activity(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                BlankFragment tab1 = new BlankFragment();
                return tab1;
            case 1:
                BlankFragment2 tab2 = new BlankFragment2();
                return tab2;
            case 2:
                BlankFragment3 tab3 = new BlankFragment3();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}

