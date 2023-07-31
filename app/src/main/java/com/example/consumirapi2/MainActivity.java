package com.example.consumirapi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.TextView;

import com.example.consumirapi2.models.Product;
import com.example.consumirapi2.network.ApiClient;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView superListView; //Usar para mostrar los datos.
    Button btn_guardar;
    MyDataBaseHelper db;
    ArrayList<String> listItems;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new MyDataBaseHelper(this);
        listItems = new ArrayList<>();
        superListView = findViewById(R.id.superListView);


        viewData();

        btn_guardar = findViewById(R.id.btn_Guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSuperHeroes();
            }
        });

    }

    private void viewData() {
        Cursor cursor = db.viewDatos();

        //Si al realizar la cuenta es igual a cero, entonces no hay datos para mostrar
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No hay datos para mostrar", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                listItems.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
            superListView.setAdapter(adapter);
        }
    }

    public void getSuperHeroes() {
        Call<List<Product>> call = ApiClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();

                    //myheroList es el array donde se almacenan los datos
                    //Name es cada uno de los nombres almacenados
                    //Se convierte el array a texto
                    String data = myheroList.get(i).getName();

                    MyDataBaseHelper myDB = new MyDataBaseHelper(MainActivity.this);
                    //Se insertan los datos en la base de datos.
                    myDB.addRegistro(data);

                }

                superListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, oneHeroes));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ha ocurrido un error. ", Toast.LENGTH_LONG).show();
            }

        });
    }

//    public void verRegistros(){
//        MyDataBaseHelper db = new MyDataBaseHelper(MainActivity.this);
//        SQLiteDatabase sqlitedb = db.getWritableDatabase();
//
//    }



}