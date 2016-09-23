package com.rodridg91.web.servcheck;

import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.List;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class Server implements Serializable {
    private String name;
    private String url;
    private List<Service> services;
    private ListView servicesList;
    private String state="up";


    public Server(String name, String url, List<Service> services) {
        super();
        this.name = name;
        this.url = url;
        this.services = services;
        this.checkStatus();
    }

    public Server(ServJSON server){
        super();
        this.name=server.getName();
        this.url=server.getUrl();
        this.state=server.getState();
        this.services=server.getServices();
        this.checkStatus();
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public ListView getServicesList() {
        return this.servicesList;
    }

    public void setServicesList(ListView servicesList) {
        this.servicesList = servicesList;
    }

    public void setServices(List services) {
        this.services = services;
    }

    public List getServices() {
        return this.services;
    }

    public void setServicesListAdapter(ServiceAdapter adapter) {

        this.servicesList.setAdapter(adapter);

        Utilidades.setListViewHeightBasedOnChildren(servicesList);
    }

    public String getStatus(){return state;}


    public void checkStatus() {
        int contErr=0;
        for (int i = 0; i < services.size(); i++) {

            Service service = services.get(i);

            SocketAddress host = new InetSocketAddress(url,service.getPort());
            Socket ping = new Socket();
            service.setState(true);
            state="up";

            try {
                ping.connect(host,10000);
                }
            catch (SocketTimeoutException e){
                service.setState(false);
                contErr++;
            }
            catch (IOException e) {
                service.setState(false);
                contErr++;
            }
        }

        if (contErr==services.size()){
            state="down";
        }else if(contErr > 0){
            state="half";
        }

    }
}
