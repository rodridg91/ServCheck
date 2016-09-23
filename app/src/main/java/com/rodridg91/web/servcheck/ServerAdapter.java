package com.rodridg91.web.servcheck;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rodridg91 on 04/08/2016.
 */
public class ServerAdapter extends BaseAdapter{

    private Context context;
    private List<Server> servers;

    public ServerAdapter(Context context,List<Server> servers){
        this.context = context;
        this.servers = servers;
    }

    @Override
    public int getCount() {
        return this.servers.size();
    }

    @Override
    public Object getItem(int position) {
        return this.servers.get(position);
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
            rowView = inflater.inflate(R.layout.server_elemtent, parent, false);
        }

        //Seteamos la informacion del View.
        TextView serverName = (TextView) rowView.findViewById(R.id.serverName) ;
        ImageView serverStatus = (ImageView) rowView.findViewById(R.id.serverIcon);

        Server server = this.servers.get(position);
        serverName.setText(server.getName());

        server.setServicesList((ListView) rowView.findViewById(R.id.servicesList));
        server.setServicesListAdapter(new ServiceAdapter(context, server.getServices()));

        switch (server.getStatus()){
            case "up":
                serverStatus.setBackgroundResource(R.drawable.status_up);
                break;
            case "half":
                serverStatus.setBackgroundResource(R.drawable.status_half);
                break;
            case "down":
                serverStatus.setBackgroundResource(R.drawable.status_down);
                break;
        }

        return rowView;
    }


}
