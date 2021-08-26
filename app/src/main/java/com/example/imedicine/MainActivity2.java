package com.example.imedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity2 extends AppCompatActivity {

    MyDatabase database;
    ListView listView;
    MyAdapter myAdapter;
    Medicines clickedMed;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
        listView = findViewById(R.id.listView);
        database = new MyDatabase(MainActivity2.this);
        showAll(database);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedMed = (Medicines) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                intent.putExtra("id", clickedMed.getMedId());
                intent.putExtra("name", clickedMed.getMedName());
                intent.putExtra("date", clickedMed.getMedDate());
                intent.putExtra("time", clickedMed.getMedTime());
                startActivity(intent);
            }
        });
    }
    private void showAll(MyDatabase database) {
        myAdapter = new MyAdapter(MainActivity2.this, database.getAllMeds());
        listView.setAdapter(myAdapter);
    }
}