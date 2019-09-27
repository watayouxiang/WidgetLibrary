package com.watayouxiang.widgetlibdemo.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class VpAdapter extends FragmentPagerAdapter {
    private List<String> titles;
    private List<TestFragment> fragments;

    VpAdapter(@NonNull FragmentManager fm, List<String> titles) {
        super(fm);
        this.titles = titles;
        fragments = new ArrayList<>();
        for (String title : titles) {
            fragments.add(TestFragment.getInstance(title));
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}