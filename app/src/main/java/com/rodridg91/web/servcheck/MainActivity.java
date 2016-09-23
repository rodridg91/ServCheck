package com.rodridg91.web.servcheck;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView serversList;
    private List servers = new ArrayList();
    private List services = new ArrayList();
    private int request_code=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creamos la lista de servidores
        this.serversList = (ListView) findViewById(R.id.serverList);

        Type t = new TypeToken<ArrayList<ServJSON>>() {}.getType();
        String servidores;
        SharedPreferences prefe = getSharedPreferences("Servidores", Context.MODE_PRIVATE);
        servidores = prefe.getString("Servidores", null);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Server.class, new ServerDeserialiser());
        Gson gson = gsonBuilder.create();
        ArrayList<ServJSON> resultado = gson.fromJson(servidores,t);
        if (resultado != null){
            if (!resultado.isEmpty()) {
                for (int j = 0; j < resultado.size(); j++) {
                    servers.add(new Server(resultado.get(j)));
                }
            }
        }
        this.serversList.setAdapter(new ServerAdapter(this, servers));


       // services.add(new Service("http",80));

       // servers.add(new Server("Creado a mano","www.google.com",services));
       // this.serversList.setAdapter(new ServerAdapter(this,servers));


        //Creamos addButton
        FloatingActionButton addButton = (FloatingActionButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CargaDeServer.class);
                startActivityForResult(i, request_code);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if ((requestCode == request_code) && (resultCode == RESULT_OK)) {
            if (servers == null) {servers = new ArrayList();}
            if (data.getSerializableExtra("objeto") != null) {
                //Cargamos el servidor
                servers.add(data.getSerializableExtra("objeto"));
                this.serversList.setAdapter(new ServerAdapter(this, servers));
            }
            for (int i=0; i<servers.size(); i++){
                Server serv = (Server) servers.get(i);
                serv.checkStatus();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();


    }

    @Override
    protected void onPause(){
        super.onPause();
        Gson userJson = new Gson();
        ArrayList<ServJSON> jservers= new ArrayList<>();


        for (int i=0; i<servers.size(); i++){
            jservers.add(new ServJSON((Server) servers.get(i)));
        }
        String servidores=userJson.toJson(jservers);
        SharedPreferences prefe = getSharedPreferences("Servidores", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefe.edit();
        editor.putString("Servidores", servidores);
        editor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
