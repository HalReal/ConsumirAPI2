package com.example.consumirapi2.models;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("name")
    private String superName;

    public Product(String name) {
        this.superName = name;
    }

    public String getName() {
        return superName;
    }
}
