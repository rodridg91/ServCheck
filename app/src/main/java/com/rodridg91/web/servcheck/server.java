package com.rodridg91.web.servcheck;

import android.support.v7.app.AppCompatActivity;



import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Server {
    private String name;
    private String url;
    private ListView servicesList;

    public Server(){
        super();
    }

    public Server(String name, String url){
        super();
        this.name = name;
        this.url = url;
    }


    public String getName(){
        return this.name;
    }

    public String getUrl(){
        return this.url;
    }

    public ListView getServicesList() {return this.servicesList;}

    public void setServicesList(ListView servicesList){this.servicesList=servicesList;}


}
