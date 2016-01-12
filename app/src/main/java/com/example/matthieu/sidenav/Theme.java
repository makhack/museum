package com.example.matthieu.sidenav;

/**
 * Created by matthieu on 12/01/2016.
 */
public class Theme {

    private String name;
    private int img;
    private long id;

    public Theme() {

    }

    public Theme(Long id) {
        this.id = id;
    }

    public Theme(final String name, final int id, final int img) {
        super();

        this.name = name;
        this.id = id;
        this.img = img;
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
}
