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
    public Object instantiateItem(ViewGroup container, int position) {
        TextView textView = new TextView(c);
        textView.setTextColor(Color.WHITE);
        textView.setTextSize(30);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText(String.valueOf(position));

        ImageView imageView = new ImageView(c);
        imageView.setImageResource(items.get(position).getImage());
        LayoutParams imageParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        imageView.setLayoutParams(imageParams);

        LinearLayout layout = new LinearLayout(c);
        layout.setOrientation(LinearLayout.VERTICAL);
        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        layout.setBackgroundColor(Color.BLACK);
        layout.setLayoutParams(layoutParams);
        layout.addView(textView);
        layout.addView(imageView);

        final int page = position;
        layout.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(c,
                        "Page " + page + " clicked",
                        Toast.LENGTH_LONG).show();
            }});

        container.addView(layout);
        return layout;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

    public Item getItem(int position) {
        return items.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        //---------------------
        // inflate
        //-------------------------
        final ViewHolder viewHolder;
        if (rowView == null) {
            //cr�ation
            rowView = mInflater.inflate(R.layout.pager_cellule, null);

            viewHolder = new ViewHolder();
            viewHolder.pc_tv_name = (TextView) rowView.findViewById(R.id.pc_tv_name);
            viewHolder.pc_iv = (ImageView) rowView.findViewById(R.id.pc_iv_image);
            viewHolder.pc_tv_description = (TextView) rowView.findViewById(R.id.pc_tv_description);

            rowView.setTag(viewHolder);
        }
        else {
            //recyclage
            viewHolder = (ViewHolder) rowView.getTag();
        }

        //---------------------
        // Remplissage
        //-------------------------

        //on remplit avec l'objet voulu
        final Item itemBean = (Item) getItem(position);

        viewHolder.pc_tv_name.setText(itemBean.getName());
        viewHolder.pc_iv.setImageResource(itemBean.getImage());
        viewHolder.pc_tv_description.setText(itemBean.getDescription());

//        rowView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                i = new Intent(c.getApplicationContext(), TabActivity.class);
//                Theme theme = themeBeanList.get(position);
//                items = theme.getItems();
//                i.putParcelableArrayListExtra("test", (ArrayList<? extends Parcelable>) items);
//                c.startActivity(i);
//            }
//        });

        return rowView;
    }

    public static class ViewHolder {

        public TextView pc_tv_name;
        public ImageView pc_iv;
        public TextView pc_tv_description;
    }
}