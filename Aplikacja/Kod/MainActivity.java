package com.darasdev.mobiledataterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;

import com.darasdev.mobiledataterminal.Scanner.ScannerBarcodeActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    TerminalDatabaseHelper terminalDatabase = new TerminalDatabaseHelper(this);
    DataDAO dataDAO = new DataDAO(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }



    void showDataBase(){
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    void showAddProductLayout(){
        Intent intent = new Intent(this, AddProductActivity.class);
        startActivity(intent);
    }

    void scannerBarCode(){
        Intent intent = new Intent(this, ScannerBarcodeActivity.class);
        startActivity(intent);
    }

    void testActiv(){
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }


    public void showDataOnClick(View view) {
        showDataBase();
    }
    public void addDataOnClick(View view) {
        showAddProductLayout();
    }
    public void scanBarcodeOnClick(View view) { scannerBarCode();}
    public void testOnClick(View view) { testActiv();}
}
