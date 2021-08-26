package com.example.imedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    TextView viewName, viewDate, viewTime;
    Button btnDelete, btnBack;
    Medicines medicines;
    MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent1 = getIntent();
        int medId = intent1.getIntExtra("id", 0);
        String medName = intent1.getStringExtra("name");
        String medDate = intent1.getStringExtra("date");
        String medTime = intent1.getStringExtra("time");

        btnBack = findViewById(R.id.btnBack2);
        viewName = findViewById(R.id.viewName);
        viewDate = findViewById(R.id.viewDate);
        viewTime = findViewById(R.id.viewTime);
        btnDelete = findViewById(R.id.delete);

        viewName.setText(medName);
        viewDate.setText(medDate);
        viewTime.setText(medTime);
        medicines = new Medicines();
        
        database = new MyDatabase(getBaseContext());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Recycle")
            @Override
            public void onClick(View v) {
                try {
                    SQLiteDatabase db = database.getWritableDatabase();
                    db.delete("iMedicine", "id = " + medId, null);
                    Toast.makeText(MainActivity3.this, "Medicine Deleted", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Toast.makeText(MainActivity3.this, "" + e, Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });

    }
}