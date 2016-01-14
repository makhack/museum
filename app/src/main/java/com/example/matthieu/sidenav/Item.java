package com.example.matthieu.sidenav;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matthieu on 12/01/2016.
 */
public class Item implements Parcelable {
    private int image;
    private String description;
    private String name;

    public Item(int image, String description, String name) {
        this.image = image;
        this.description = description;
        this.name = name;
    }

    protected Item(Parcel in) {
        image = in.readInt();
        description = in.readString();
        name = in.readString();
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(description);
        dest.writeString(name);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
