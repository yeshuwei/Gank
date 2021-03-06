/*
 * Copyright 2017 lizhaotailang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gank.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gank.mainpager.FrontFragment;
import com.gank.mainpager.GankFragment;
import com.gank.mainpager.IosFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Swy on 2016/8/10.
 * ViewPager适配器
 */

public class MainPagerAdapter extends FragmentPagerAdapter {
    private String[] titles;
    private Context context;
    private GankFragment gankFragment;
    private FrontFragment frontFragment;
    //    private MeiziFragment meiziFragment;
    private IosFragment iosFragment;

    private List<Fragment> fragments = new ArrayList<>();

    public GankFragment getGankFragment() {
        return gankFragment;
    }

    public FrontFragment getFrontFragment() {
        return frontFragment;
    }

    public IosFragment getIosFragment() {
        return iosFragment;
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    public MainPagerAdapter(FragmentManager fm, Context context, GankFragment gankFragment, FrontFragment frontFragment, IosFragment iosFragment) {
        super(fm);
        this.context = context;
        titles = new String[]{"Android", "Web", "IOS"};
        this.gankFragment = gankFragment;
        this.frontFragment = frontFragment;
//        this.meiziFragment=meiziFragment;
        this.iosFragment = iosFragment;
        fragments.add(gankFragment);
        fragments.add(frontFragment);
        fragments.add(iosFragment);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1) {
            return frontFragment;
        } else if (position == 2) {
            return iosFragment;
        }
        return gankFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
