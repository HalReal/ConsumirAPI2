package com.example.consumirapi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.TextView;

import com.example.consumirapi2.models.Product;
import com.example.consumirapi2.network.ApiClient;

import org.json.JSONArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView superListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        superListView = findViewById(R.id.superListView);
        getSuperHeroes();

//        //aca luego de hacer el fetch de la API, se guardará en la base de datos de sqlite
//        String apiData = "name";
//
//        MyDataBaseHelper databaseHelper = new My DataBaseHelper(this);
//        SQLiteDatabase db = databaseHelper.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(MyDataBaseHelper.COLUMN_NAME);
//
//        long newRowId = db.insert(MyDataBaseHelper.TABLE_NAME, null, values);
//        SQLiteDatabase dbRead = databaseHelper.getReadableDatabase();
//
//        String[] projection = {databaseHelper.COLUMN_NAME};
//        Cursor cursor = dbRead.query(DatabaseHelper.TABLE_NAME, projection, null, null, null, null, null);
//
//        String apiDataFromDB = "";
//
//        if(cursor.moveToFirst()) {
//            apiDataFromDB = cursor.getString(getColumnIndex(DatabaseHelper.COLUMN_NAME));
//        }
//
//    cursor.close();

    }

    private void getSuperHeroes() {
        Call<List<Product>> call = ApiClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<Product>>() {

            //aca se tiene que hacer que el arreglo, se guarde en la DB.

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();

                    String data = myheroList.get(i).getName();

                    System.out.println("Capturando Datos ===>"+ data);
                }
// 1
                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error. ", Toast.LENGTH_LONG).show();
            }

        });
    }
}