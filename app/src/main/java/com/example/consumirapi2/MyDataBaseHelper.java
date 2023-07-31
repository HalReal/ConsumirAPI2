package com.example.consumirapi2;//import

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

//Notas a mi: Para la clase MyDataBaseHelper la cual es


public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DATABASE_NAME = "ConsumirAPI2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "names";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_name";

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryToCreateTable = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT);";

        //Pide que el código anterior sea leído como un query de SQLite
        db.execSQL(queryToCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se encarga de manejar el esquema de la base de datos si es que se da un cambio en la misma
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addRegistro(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Fallo al guardar el dato en la base de datos", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Dato guardado correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    //Mostrar datos
    public Cursor viewDatos() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
}
