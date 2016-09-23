package com.rodridg91.web.servcheck;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CargaDeService extends AppCompatActivity {
    private EditText nombre;
    private EditText puerto;
    private List services =  new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_de_service);

        nombre = (EditText) findViewById(R.id.nombre_service);
        puerto = (EditText) findViewById(R.id.puerto_service);



        Button agregar = (Button) findViewById(R.id.agregar_servicio);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                services.add(new Service(nombre.getText().toString(),Integer.parseInt( puerto.getText().toString())));
                nombre.setText("");
                puerto.setText("");
            }
        });

        Button cargar = (Button) findViewById(R.id.cargar_servicios);
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                services.add(new Service(nombre.getText().toString(),Integer.parseInt( puerto.getText().toString())));
                Intent i = new Intent();
                i.putParcelableArrayListExtra("objeto", (ArrayList) services);
                setResult(RESULT_OK,i);
                finish();
            }
        });







    }
}
