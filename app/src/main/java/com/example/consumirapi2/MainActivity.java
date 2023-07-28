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

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView superListView;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_guardar = findViewById(R.id.btn_Guardar);
        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                superListView = findViewById(R.id.superListView);
                getSuperHeroes();
            }
        });

    }

    public void getSuperHeroes() {
        Call<List<Product>> call = ApiClient.getInstance().getMyApi().getsuperHeroes();
        call.enqueue(new Callback<List<Product>>() {

            //aca se tiene que hacer que el arreglo, se guarde en la DB.

            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> myheroList = response.body();
                String[] oneHeroes = new String[myheroList.size()];

                for (int i = 0; i < myheroList.size(); i++) {
                    oneHeroes[i] = myheroList.get(i).getName();

                    //myheroList es donde se almacenan los datos
                    //Name es cada uno de los nombres almacenados
                    String data = myheroList.get(i).getName();
                    MyDataBaseHelper myDB = new MyDataBaseHelper(MainActivity.this);

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
}