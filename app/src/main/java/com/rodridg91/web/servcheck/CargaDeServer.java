package com.rodridg91.web.servcheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class CargaDeServer extends AppCompatActivity {

    private EditText campo_nombre;
    private EditText campo_direccion;
    private int request_code=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_de_server);

        campo_nombre = (EditText) findViewById(R.id.nombre_serv);
        campo_direccion = (EditText) findViewById(R.id.direccion_serv);


        Button cargar = (Button) findViewById(R.id.cargar_serv);
        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(CargaDeServer.this, CargaDeService.class);
                startActivityForResult(i, request_code);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ((requestCode == request_code) && (resultCode == RESULT_OK)) {
            if (data.getSerializableExtra("objeto") != null) {
                Intent i = new Intent();
                List services =  data.getParcelableArrayListExtra("objeto");
                i.putExtra("objeto",new Server(campo_nombre.getText().toString(),campo_direccion.getText().toString(), services));
                setResult(RESULT_OK,i);
                finish();
            }
        }
    }
}
