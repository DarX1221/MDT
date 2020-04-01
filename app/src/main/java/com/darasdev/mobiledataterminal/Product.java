package com.darasdev.mobiledataterminal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.PointerIcon;
import android.view.View;
import android.widget.TextView;

public class Product  {
    View view;
    TextView nameTextView;
    TextView descripitonTextView;
    TextView codeTextView;

    public Product(String name, String description, int code){
        this.name = name;
        this.description = description;
        this.code = code;
    }
    public Product(){
        this.name = name;
        this.description = description;
        this.code = code;
    }

    public void setLayoutViews(){

    }


    int id, code;
    String name, type, description;

    void setID(int id){
        this.id = id;
    }
    int getID(){
        return id;
    }


    void setName(String name){
        this.name = name;
    }
    String getName(){
        return name;
    }


    void setDescription(String description){
        this.description = description;
    }
    String getDescription(){
        return description;
    }


    void setType(String type){
        this.type = type;
    }
    String getType(){
        return type;
    }


    void setCode(int code){
        this.code = code;
    }
    int getCode(){
        return code;
    }


    @Override
    public String toString() {
        return name;
    }



}




/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
    }

*/


