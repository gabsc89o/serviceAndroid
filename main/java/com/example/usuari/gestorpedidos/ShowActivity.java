package com.example.usuari.gestorpedidos;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        DBProductos adp=new DBProductos(this);
        Cursor c = adp.recuperarPedido();
        String[] columnas = new String[]{"producto","unidades"};
        int[] vistas = new int[]{R.id.tvProducto,R.id.tvUnidades};
        SimpleCursorAdapter sc = new SimpleCursorAdapter(
                this,
                R.layout.list_controls,
                c,
                columnas,
                vistas,
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );
        ListView listas=(ListView) findViewById(R.id.lvPedidos);
        listas.setAdapter(sc);
        adp.close();
    }
    public void regresar(View v){
        this.finish();
    }
}
