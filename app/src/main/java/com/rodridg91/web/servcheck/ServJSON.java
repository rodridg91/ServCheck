package com.rodridg91.web.servcheck;

import java.util.List;

/**
 * Created by Alumno on 31/08/2016.
 */
public class ServJSON {
    private String name;
    private String url;
    private List<Service> services;
    private String state="up";

    public ServJSON(Server server){
        super();
        this.name=server.getName();
        this.url=server.getUrl();
        this.state=server.getStatus();
        this.services=server.getServices();
    }

    public String getName(){return name;}

    public String getUrl(){return url;}

    public String getState(){return state;}

    public List getServices(){return services;}



}
