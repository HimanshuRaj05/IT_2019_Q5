package com.intreve.it_2019_q5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


//IMPORTANT: READ THE COMMENTS

//Answer to this exam question is the whole MyDatabaseHelper class code

//Writing MainActivity code in the exam is mostly optional for this question, not necessary

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnDelete;

    MyDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnInsert=findViewById(R.id.btnInsert);
        btnDelete=findViewById(R.id.btnDelete);
        myDB=new MyDatabaseHelper(MainActivity.this);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.insertRecords();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDB.deleteRecords("Mike");
            }
        });
    }
}