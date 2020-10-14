package com.example.ejerciciotimeentreactivitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PermissionActivity extends AppCompatActivity {

    private final String TAG = getClass().getName();
    private TimerTask timerTask;
    private Timer timer;
    private int currenTime = 0;
    private int tiempoActivity1 = 0;
    private TextView textViewTiempoActivity01 = null;
    private TextView tiempoPermissionActivity;
    private int permisosTF = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

       //RECIBIMOS EL INTENT, LLAMAMOS AL INT TIEMPOACTIVITY UNO PARA PASARLE EL VALOR QUE RECIBIMOS
        // Y CREAMOS LOS 2 TEXTVIEW PARA EL TIEMPO Y LLAMAMOS AL CRONOMETRO
        Intent recibir =getIntent();
        tiempoActivity1 = getIntent().getIntExtra("tiempoMainToPermission", 0);
        textViewTiempoActivity01 = findViewById(R.id.tiempo07);
        textViewTiempoActivity01.setText(tiempoActivity1+"");
        tiempoPermissionActivity = findViewById(R.id.tiempo06);
       cronometro();


        Button backToMain = findViewById(R.id.buttonBackFromPermission);
        backToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backFromData = new Intent(PermissionActivity.this , MainActivity.class);
                backFromData.putExtra("TiempoPermissionToMain", currenTime);
                backFromData.putExtra("tiempoMainDesdePermission", tiempoActivity1);
                backFromData.putExtra("mandarPermissionTF", permisosTF);
                startActivity(backFromData);
            }
        });


        Button botonData = findViewById(R.id.buttonSaveData2);
        botonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permisosTF = 1;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

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
                        tiempoPermissionActivity.setText(String.valueOf(currenTime));
                    }
                });

            }
        };

        timer = new Timer();
        timer.schedule(timerTask, 1, 1000);

    }
}