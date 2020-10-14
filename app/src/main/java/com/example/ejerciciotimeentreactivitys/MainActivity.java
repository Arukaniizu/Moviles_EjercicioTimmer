package com.example.ejerciciotimeentreactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private TimerTask timerTask;
    private Timer timer;
    private int currenTime = 0;
    private TextView textViewTiempo01 = null;
    int tiempoActivity1 = 0;
    int tiempoActivity2 = 0;
    int tiempoActivity3 = 0;
    private TextView textViewTiempo02;
    private TextView textViewdataTF = null;
    private TextView textViewTiempo03;
    private TextView textViewPermTF = null;
    int dataTF =0;
    int permissionTF = 0;
    int recuperacionData = 0;
    int recuperacionPer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");

        textViewTiempo01 = (TextView) findViewById(R.id.tiempo01);


        Button botonData = findViewById(R.id.buttonData);
        botonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REALIZAMOS UN INTENT PARA MANDAR LOS DATOS DEL CURRENTTIME A LA ACTIVIDAD 2
                Intent activity2Intent= new Intent(MainActivity.this, DataActivity.class);
                activity2Intent.putExtra("tiempoMainToData", currenTime);
                activity2Intent.putExtra("permisosData", 0);
                startActivity(activity2Intent);
            }
        });

        Button botonPermission = findViewById(R.id.buttonPermission);
        botonPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //REALIZAMOS UN INTENT PARA MANDAR LOS DATOS DEL CURRENTTIME A LA ACTIVIDAD 3
                Intent activity3Intent = new Intent(MainActivity.this, PermissionActivity.class);
                activity3Intent.putExtra("tiempoMainToPermission", currenTime);
                startActivity(activity3Intent);
            }
        });

            //RECIBIMOS LOS INTENT DE LAS OTRAS ACTIVITYS Y LOS PONEMOS EN SUS TEXTVIEW
        Intent recibir = getIntent();
        tiempoActivity1 = getIntent().getIntExtra("tiempoMainDesdeData", 0);
        currenTime = tiempoActivity1;
        tiempoActivity2 = getIntent().getIntExtra("TiempoDataToMain", 0);
        tiempoActivity3 = getIntent().getIntExtra("TiempoPermissionToMain", 0);
        recuperacionData = getIntent().getIntExtra("mandarDataTF", 0);
        recuperacionPer = getIntent().getIntExtra("mandarPermissionTF", 0);
        dataTF = recuperacionData;
        permissionTF = recuperacionPer;
        textViewTiempo02 = findViewById(R.id.tiempo02);
        textViewTiempo02.setText(tiempoActivity2+"");
        textViewTiempo03 = findViewById(R.id.tiempo03);
        textViewTiempo03.setText(tiempoActivity3+"");
        textViewdataTF = findViewById(R.id.mdata);
        textViewPermTF = findViewById(R.id.mpermission);




        //PARA PASAR LOS DATOS A TRUE O FALSE
        if(dataTF == 0){
                textViewdataTF.setText("False");
        }else{
            textViewdataTF.setText("True");
        }
        if(permissionTF == 0){
            textViewPermTF.setText("False");
        }else{
            textViewPermTF.setText("True");
        }
    }


    //EL CRONOMETRO LO ARRANCAMOS EN EL ONSTART PORQUE SI LO PONEMOS EN EL ONCREATE AL VOVLER HACIA
    // ATRAS PERO NO CON EL BOTON SE PARARIA.
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        cronometro();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    //EL TIME.CANCEL LO METEMOS EN EL PAUSE PARA QUE CUANDO NO ESTEMOS EN ESA ACTIVITY SE PARE EL CONTADOR
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        timer.cancel();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    //CREAMOS EL CRONOMETRO CON EL TIMERTASK Y EL TIMER PARA QUE EL TIEMPOAVANCE Y LE DECIMOS QUE NOS
    //LO MUESTRE EN EL TEXTVIEWTIEMPO01
    public void cronometro() {
        Log.d(TAG,"cronometro");
        timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        currenTime++;
                        textViewTiempo01.setText(String.valueOf(currenTime));
                    }
                });

            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 1, 1000);

    }
}