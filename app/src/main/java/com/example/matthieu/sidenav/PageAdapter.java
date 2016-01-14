package com.example.matthieu.sidenav;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import java.util.List;

public class PageAdapter extends android.support.v4.view.PagerAdapter {
    private LayoutInflater mInflater;
    private Context c;
    private Intent i;
    private List<Item> items;
    public PageAdapter(Context c, final List<Item> items) {

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.c = c;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object instantiateItem(View collection, int position) {

        View rowView = mInflater.inflate(R.layout.pager_cellule, null);

        TextView tvName = (TextView) rowView.findViewById(R.id.pc_tv_name);
        ImageView ivImage = (ImageView) rowView.findViewById(R.id.pc_iv_image);
        TextView tvDescription = (TextView) rowView.findViewById(R.id.pc_tv_description);


        tvName.setText(items.get(position).getName());
        ivImage.setBackgroundResource(items.get(position).getImage());
        tvDescription.setText(items.get(position).getDescription());
        ((ViewPager) collection).addView(rowView);
        return rowView;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ScrollView)object);
    }

    public static class ViewHolder {

        public TextView pc_tv_name;
        public ImageView pc_iv;
        public TextView pc_tv_description;
    }
}