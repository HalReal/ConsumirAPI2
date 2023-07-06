package com.example.consumirapi2.network;

import com.example.consumirapi2.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    //URL de la API
    String BASE_URL = "https://jsonplaceholder.typicode.com";
    //Accion a realizar (en este caso un get)
    @GET("/users")
    Call<List<Product>> getsuperHeroes();
}