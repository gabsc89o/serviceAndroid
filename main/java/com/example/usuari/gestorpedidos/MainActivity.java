package com.example.usuari.gestorpedidos;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String prod;
    public static int uni,stat;
    static ProgressBar progreso;
    static TextView tvtiempo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stat=0;
        progreso = (ProgressBar) this.findViewById(R.id.progressBar);
        progreso.setMax(100);
        tvtiempo = (TextView) this.findViewById(R.id.tvClock);
    }
    public void iniciar(View v){
        prod=((EditText) this.findViewById(R.id.edtProducto)).getText().toString();
        String u=((EditText) this.findViewById(R.id.edtUnidades)).getText().toString();
        uni = Integer.parseInt(u);
        stat=1;
        startService(new Intent(this,ServiceGestor.class));

    }
    public void ver(View v){
        Intent i = new Intent(this,ShowActivity.class);
        this.startActivity(i);
    }
    @Override
    protected void onResume() {
        stat=0;
        startService(new Intent(this,ServiceGestor.class));
        super.onResume();
    }

    @Override
    protected void onPause() {

        stopService(new Intent(this,ServiceGestor.class));
        ((EditText) this.findViewById(R.id.edtProducto)).setText("");
        ((EditText) this.findViewById(R.id.edtUnidades)).setText("");
        super.onPause();
    }
    public static Handler manejador=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int valor=msg.arg1;
            progreso.setProgress(valor);
        }
    };
    public static Handler manejador2=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String time = (String) msg.obj;
            tvtiempo.setText(time);
        }
    };
}
