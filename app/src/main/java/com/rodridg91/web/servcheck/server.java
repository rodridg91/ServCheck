package com.rodridg91.web.servcheck;

import android.support.v7.app.AppCompatActivity;



import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Server implements Serializable {
    private String name;
    private String url;
    private List<Service> services;
    private ListView servicesList;



    public Server(String name, String url, List<Service> services){
        super();
        this.name = name;
        this.url = url;
        this.services = services;
    }


    public String getName(){
        return this.name;
    }

    public String getUrl(){
        return this.url;
    }

    public ListView getServicesList() {return this.servicesList;}

    public void setServicesList(ListView servicesList){this.servicesList=servicesList;}

    public void setServices(List services){this.services= services;}

    public List getServices() {return  this.services;}

    public void setServicesListAdapter(ServiceAdapter adapter){

        this.servicesList.setAdapter(adapter);

        Utilidades.setListViewHeightBasedOnChildren(servicesList);
    }


}
