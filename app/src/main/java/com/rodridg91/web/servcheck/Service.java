package com.rodridg91.web.servcheck;

import java.io.Serializable;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Service implements Serializable{
    private String name;
    private int port;
    private Boolean state = false;


    public Service(String name, int port){
        super();
        this.name = name;
        this.port = port;


    }



    public String getName(){
        return this.name;
    }

    public int getPort(){
        return this.port;
    }

    public Boolean getState(){return this.state;}

    public void setState(Boolean state){this.state=state;}
}
