package com.example.matthieu.sidenav;

/**
 * Created by Amine on 14-Jan-16.
 */
public class Favorites {
    private long id;
    private long item_id;

    public Favorites(long id, long item_id)
    {
        super();
        this.id = id;
        this.item_id = item_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }
}
