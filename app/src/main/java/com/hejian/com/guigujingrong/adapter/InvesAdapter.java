package com.hejian.com.guigujingrong.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hejian.com.guigujingrong.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何健 on 2017/3/14.
 */

public class InvesAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragments = new ArrayList<>();

    public InvesAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        if(fragments!=null &&fragments.size()>0){
            this.fragments = fragments;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
