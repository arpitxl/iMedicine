package com.example.imedicine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner setTime;
    String[] times = new String[]{
            "Morning",
            "Afternoon",
            "Evening",
            "Night"
    };
    EditText setName, setDate;
    Button btnSave, btnShow;
    MyDatabase database;
    Medicines medicines;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setName = findViewById(R.id.medName);
        setDate = findViewById(R.id.date);
        setTime = findViewById(R.id.time);
        btnSave = findViewById(R.id.delete);
        btnShow = findViewById(R.id.show);
        database = new MyDatabase(getBaseContext());

        adapter = new ArrayAdapter<>(this, R.layout.spinner_xml, times);
        setTime.setAdapter(adapter);
        final Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            private void updateLabel() {
                String myFormat = "dd/MM/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                setDate.setText(sdf.format(myCalendar.getTime()));
            }
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        setDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setName.getText().toString().equals("") || setDate.getText().toString().equals("") || setTime.getSelectedItem().toString().equals("")) {
                    medicines = new Medicines();
                    Toast.makeText(MainActivity.this, "Fields cannot be empty :(", Toast.LENGTH_SHORT).show();
                }
                else {
                    database = new MyDatabase(getBaseContext());
                    SQLiteDatabase db = database.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    medicines = new Medicines(-1, setName.getText().toString(), setDate.getText().toString(), setTime.getSelectedItem().toString());
                    String str = medicines.getMedName();
                    String cap = str.substring(0, 1).toUpperCase() + str.substring(1);
                    cv.put("mName", cap);
                    cv.put("mDate", medicines.getMedDate());
                    cv.put("mTime", medicines.getMedTime());
                    long insert = db.insert("iMedicine", null, cv);
                    Toast.makeText(MainActivity.this, "Medicine has been saved", Toast.LENGTH_SHORT).show();
                    db.close();
                }
            }
        });
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}