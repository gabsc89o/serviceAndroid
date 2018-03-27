package com.example.usuari.gestorpedidos;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.MainThread;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class ServiceGestor extends Service {
    Timer timer;
    public ServiceGestor() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Ingresando a la base de datos...",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Task().execute(new Void[]{null});
        iniciarServicio();
        return START_STICKY;
    }
    private void iniciarServicio(){
        timer = new Timer();
        timer.schedule(new MiTimer(),0,500);
    }
    private  class MiTimer extends TimerTask {

        @Override
        public void run() {
            Date d = new Date();
            DateFormat df = DateFormat.getTimeInstance(DateFormat.LONG);
            String resultado = df.format(d);
            MainActivity.manejador2.obtainMessage(0,0,0,resultado).sendToTarget();

        }
    }
    private class Task extends AsyncTask<Void, Integer, Long> {

        @Override
        protected Long doInBackground(Void... voids) {
            long result = 0;
            if(MainActivity.stat==1)
                grabar();
            for (int i =0;i<=100;i++){
                result+=i;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return result;
        }
        public void grabar(){
            DBProductos adp=new DBProductos(getBaseContext());
            adp.altaPedido(MainActivity.prod,MainActivity.uni);
            adp.close();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            MainActivity.manejador.obtainMessage(0,values[0],0,null).sendToTarget();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            if (MainActivity.stat==0)
                Toast.makeText(getBaseContext(),"Cargado",Toast.LENGTH_LONG).show();
            else
            Toast.makeText(getBaseContext(),"Pedido Registrado",Toast.LENGTH_LONG).show();
        }
    }
}
