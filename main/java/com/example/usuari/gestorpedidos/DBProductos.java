package com.example.usuari.gestorpedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by usuari on 27/03/2018.
 */

public class DBProductos {
    private SQLiteDatabase db=null;
    private DBHelper dbhelper=null;
    Context context;
    public DBProductos(Context ctx){
        this.context = ctx;
        dbhelper = new DBHelper(context);
        db = dbhelper.getWritableDatabase();
    }
    public void close(){
        dbhelper.close();
    }
    public  long altaPedido(String producto, int unidades){
        ContentValues initialValues = new ContentValues();
        initialValues.put("producto",producto);
        initialValues.put("unidades",unidades);
        return db.insert("pedidos",null,initialValues);
    }
    public Cursor recuperarPedido(){
        return db.query("pedidos", new String[]{"_id","producto","unidades"},null,null,null,null,null);
    }
}
