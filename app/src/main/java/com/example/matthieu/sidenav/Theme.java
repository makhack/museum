package com.example.matthieu.sidenav;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by matthieu on 12/01/2016.
 */
public class Theme implements Parcelable {

    private String name;
    private int img;
    private long id;
    private ArrayList<Item> items = new ArrayList<>();
    private String description;

    public Theme() {

    }

    public Theme(Long id) {
        this.id = id;
    }

    public Theme(final String name, final long id, final int img, final String desc) {
        super();

        this.name = name;
        this.id = id;
        this.img = img;
        this.description = desc;
    }

    public Theme(final String name, final int img, final String desc) {
        super();

        this.name = name;
        this.img = img;
        this.description = desc;
    }

    public Theme(final String name, final long id, final int img, ArrayList<Item> items, final String desc) {
        super();

        this.name = name;
        this.id = id;
        this.img = img;
        this.items = items;
        this.description = desc;
    }

    protected Theme(Parcel in) {
        name = in.readString();
        img = in.readInt();
        id = in.readLong();
        description = in.readString();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(img);
        dest.writeLong(id);
    }

    public static final Creator<Theme> CREATOR = new Creator<Theme>() {
        @Override
        public Theme createFromParcel(Parcel in) {
            return new Theme(in);
        }

        @Override
        public Theme[] newArray(int size) {
            return new Theme[size];
        }
    };
}
