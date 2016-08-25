package com.rodridg91.web.servcheck;

import android.text.Editable;
import android.widget.ListView;

import java.io.Serializable;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Service implements Serializable{
    private String name;
    private int port;


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
