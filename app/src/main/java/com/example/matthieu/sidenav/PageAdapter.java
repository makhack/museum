package com.example.matthieu.sidenav;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends android.support.v4.view.PagerAdapter {
    private LayoutInflater mInflater;
    private Context c;
    private Intent i;
    private ArrayList<Item> items;

    public PageAdapter(Context c, final ArrayList<Item> items) {

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.c = c;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object instantiateItem(View collection, final int position) {

        View rowView = mInflater.inflate(R.layout.pager_cellule, null);

        TextView tvName = (TextView) rowView.findViewById(R.id.pc_tv_name);
        ImageView ivImage = (ImageView) rowView.findViewById(R.id.pc_iv_image);
        TextView tvDescription = (TextView) rowView.findViewById(R.id.pc_tv_description);
        ImageButton ibLike = (ImageButton) rowView.findViewById(R.id.pc_ib_like);

        ibLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDAO sqlite = new BaseDAO(c);
                SQLiteDatabase db = sqlite.open();

                Item itemdb = items.get(position);
                // id de l'item selectionné
                long item_id = itemdb.get_item_id();

                FavoritesDAO fdao = new FavoritesDAO(c, db);

                Favorites favInDb = fdao.select(item_id);

                if (favInDb != null) {
                    fdao.delete(item_id);
                }
                else {
                    Favorites f = new Favorites(item_id);
                    fdao.add(f);
                }
            }
        });

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
        container.removeView((ScrollView) object);
    }

    public static class ViewHolder {

        public TextView pc_tv_name;
        public ImageView pc_iv;
        public TextView pc_tv_description;
    }
}