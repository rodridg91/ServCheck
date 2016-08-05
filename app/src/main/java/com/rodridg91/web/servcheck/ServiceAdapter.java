package com.rodridg91.web.servcheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class ServiceAdapter extends BaseAdapter {

    private List<Service> services;
    private Context context;

    public ServiceAdapter(Context context, List<Service> services){
        this.context = context;
        this.services = services;
    }

    @Override
    public int getCount() {
        return this.services.size();
    }

    @Override
    public Object getItem(int position) {
        return this.services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if (convertView == null) {
            //Creamos un nuevo View en la lista.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.service_element, parent, false);
        }

        //Seteamos la informacion del View.
        TextView serviceName = (TextView) rowView.findViewById(R.id.serviceName) ;


        Service service = this.services.get(position);
        serviceName.setText(service.getName());


        return rowView;
    }
}
