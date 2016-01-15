package com.example.matthieu.sidenav;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matthieu on 12/01/2016.
 */
public class Item implements Parcelable {
    // on précède les variables global par un
    // underscore pour les différencier
    private int _image;
    private String _description;
    private String _name;
    private long _theme_id;
    private long _item_id;
    private double _latitude;
    private double _longitude;

    public Item(int image, String description, String name, double latitude, double longitude, long theme_id) {
        this._image = image;
        this._description = description;
        this._name = name;
        this._latitude = latitude;
        this._longitude = longitude;
        this._theme_id = theme_id;
    }

    public Item(int image, String description, String name, double latitude, double longitude, long theme_id, long item_id) {
        this._image = image;
        this._description = description;
        this._name = name;
        this._latitude = latitude;
        this._longitude = longitude;
        this._theme_id = theme_id;
        this._item_id = item_id;
    }

    protected Item(Parcel in) {
        _image = in.readInt();
        _description = in.readString();
        _name = in.readString();
        _theme_id = in.readInt();
        _longitude = in.readDouble();
        _latitude = in.readDouble();
    }

    public long get_item_id() {
        return _item_id;
    }

    public void set_item_id(int _item_id) {
        this._item_id = _item_id;
    }

    public long get_theme_id() {
        return _theme_id;
    }

    public void set_theme_id(int _theme_id) {
        this._theme_id = _theme_id;
    }

    public double get_latitude() {
        return _latitude;
    }

    public void set_latitude(double _latitude) {
        this._latitude = _latitude;
    }

    public double get_longitude() {
        return _longitude;
    }

    public void set_longitude(double _longitude) {
        this._longitude = _longitude;
    }

    public int getImage() {
        return _image;
    }

    public void setImage(int image) {
        this._image = image;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_image);
        dest.writeString(_description);
        dest.writeString(_name);
        dest.writeDouble(_latitude);
        dest.writeDouble(_longitude);
        dest.writeLong(_theme_id);
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
