package com.example.matthieu.sidenav;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
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

public class ImageAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Theme> themeBeanList;
    private Context c;
    private Intent i;
    private List<Item> items;
    public ImageAdapter(Context c, final List<Theme> themeBeanList) {

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.themeBeanList = themeBeanList;
        this.c = c;
    }

    public int getCount() {
        return themeBeanList.size();
    }

    public Theme getItem(int position) {
        return themeBeanList.get(position);
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
        final Theme themeBean = (Theme) getItem(position);

        viewHolder.ec_tv_name.setText(themeBean.getName());
        viewHolder.ec_iv.setImageResource(themeBean.getImg());

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                i = new Intent(c.getApplicationContext(), ItemActivity.class);
                Theme theme = themeBeanList.get(position);
                items = theme.getItems();
                if(items != null) {
                    i.putParcelableArrayListExtra("test", (ArrayList<? extends Parcelable>) items);
                    c.startActivity(i);
                }else{
                    Toast.makeText(c,"Aucune images pour cette galerie", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }

    public static class ViewHolder {

        public TextView ec_tv_name;
        public ImageView ec_iv;
    }
}