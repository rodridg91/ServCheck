package com.rodridg91.web.servcheck;

import android.widget.ListView;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Service {
    private String name;
    private int port;

    public Service(){
        super();
    }

    public Service(String name, int port){
        super();
        this.name = name;
        this.port = port;


    }

    public String getName(){
        return this.name;
    }

    public int getUrl(){
        return this.port;
    }
}
