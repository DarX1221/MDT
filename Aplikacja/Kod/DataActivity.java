package com.darasdev.mobiledataterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

    //  Activity used to show Data

public class DataActivity extends AppCompatActivity {
    private SQLiteDatabase database;
    private Cursor cursor;
    ArrayList<Product> productList = new ArrayList<>();
    DataDAO dataDAO = new DataDAO(this);

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Toast.makeText(this, "Delete product by click", Toast.LENGTH_LONG).show();
        reloadProductList();
    }


    ArrayList<Product> productList2;
    void reloadProductList(){
        productList2 = dataDAO.getAllProduct();

        recyclerView = findViewById(R.id.myRC);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(productList2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    void addProduct(){
        Product product = new Product();
        product.name = "name";
        product.description = "description";
        product.code = "123";

        dataDAO.insertProduct(product);
        reloadProductList();
    }


    void deleteProduct(long id){
        dataDAO.deleteProduct(id);
        reloadProductList();
    }


        @Override
        protected void onDestroy () {
            super.onDestroy();
            try {
                cursor.close();
                database.close();
            }
            catch(Exception ex){ }
        }


    public void productRowClick(View view) {
        //  delete product
        RecyclerView.ViewHolder holder = this.recyclerView.getChildViewHolder(view);
        int pos = holder.getLayoutPosition();
        long id = productList2.get(pos).getID();
        dataDAO.deleteProduct(id);
        reloadProductList();
    }

    public void addProductOnClickDA(View view) {
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }
}
