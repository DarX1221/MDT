package com.darasdev.mobiledataterminal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TerminalDatabaseHelper terminalDatabase = new TerminalDatabaseHelper(this);
    }



    void showDataBase(){
        Intent intent = new Intent(this, DataActivity.class);
        startActivity(intent);
    }

    public void showDataOnClick(View view) {
        showDataBase();
    }
    public void addDataOnClick(View view) {
    }


}
