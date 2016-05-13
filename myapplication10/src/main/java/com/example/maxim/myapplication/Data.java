package com.example.maxim.myapplication;

/**
 * Created by Maxim on 10.01.2016.
 */
public class Data {
    String name;
    String size;
    Boolean file;
    String pathParent;

    public Data(String namesList, String size, Boolean file, String pathParent) {
        this.name = namesList;
        this.size = size;
        this.file = file;
        this.pathParent = pathParent;
    }
}
