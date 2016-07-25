package com.example.modao.moguindext.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.List;

/**
 * Created by modao on 16/7/13.
 */
public class RecyclerViewItemViewpagerAdapter extends PagerAdapter {
    List<RelativeLayout> list;

    RecyclerViewItemViewpagerAdapter(List<RelativeLayout> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        // TODO Auto-generated method stub
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position));
    }

    @Override
    public Object instantiateItem(View arg0, int position) {
        position %= list.size();
        if (position < 0) {
            position = list.size() + position;
        }
        ((ViewGroup)arg0).addView(list.get(position));
        return list.get(position);
    }

}
