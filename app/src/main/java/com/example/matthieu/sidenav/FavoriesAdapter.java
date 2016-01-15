package com.example.matthieu.sidenav;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FavoriesAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context c;
    private Intent i;
    private List<Item> items;
    public FavoriesAdapter(Context c, final List<Item> items) {

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;
        this.c = c;
    }

    public int getCount() {
        return items.size();
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
            rowView = mInflater.inflate(R.layout.theme_cellule, null);

            viewHolder = new ViewHolder();
            viewHolder.ec_tv_name = (TextView) rowView.findViewById(R.id.tc_tv_name);
            viewHolder.ec_iv = (ImageView) rowView.findViewById(R.id.tc_iv_image);

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

        viewHolder.ec_tv_name.setText(itemBean.getName());
        viewHolder.ec_iv.setImageResource(itemBean.getImage());

 /*       rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                i = new Intent(c.getApplicationContext(), TabActivity.class);
                i.putParcelableArrayListExtra("items", (ArrayList<? extends Parcelable>) items);
                c.startActivity(i);
            }
        });*/

        return rowView;
    }

    public static class ViewHolder {

        public TextView ec_tv_name;
        public ImageView ec_iv;
    }
}