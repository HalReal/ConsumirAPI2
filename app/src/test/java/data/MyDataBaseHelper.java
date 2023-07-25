package data;//import

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
