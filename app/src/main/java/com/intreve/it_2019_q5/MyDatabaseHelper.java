package com.intreve.it_2019_q5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    Context context;

    private static String DATABASE_NAME="student.db";
    private static int DATABASE_VERSION=1;
    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS student (roll_no INTEGER PRIMARY KEY, name VARCHAR, gender CHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS student");
        onCreate(db);
    }


    //Inserting student records
    public void insertRecords(){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        String toastText="Inserted entries:\n";

        int[] student_roll_no={1,2,3};
        String[] student_name={"Steve", "Jennie", "Mike"};
        char[] student_gender={'M','F','M'};

        for(int i=0; i<student_roll_no.length; i++){
            contentValues.put("roll_no", student_roll_no[i]);
            contentValues.put("name", student_name[i]);
            contentValues.put("gender", String.valueOf(student_gender[i]));
            toastText+="Roll no. "+ student_roll_no[i]+ ", Name: "+student_name[i]+ ", Gender: "+student_gender[i]+"\n";

            db.insert("student",null,contentValues);
            contentValues.clear();

        }

        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();

    }


    //Record deletion
    public void deleteRecords(String nameToDelete) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "name=?";
        String[] whereArgs = {nameToDelete};

        // Cursor object points to the row for which this query is executed
        Cursor cursor = db.query("student", null, whereClause, whereArgs, null, null, null);

        // Check if the cursor has data
        if (cursor.moveToFirst()) {
            // Retrieve data from the cursor
            int rollNoIndex = cursor.getColumnIndex("roll_no");
            int nameIndex = cursor.getColumnIndex("name");
            int genderIndex = cursor.getColumnIndex("gender");

            int rollNo = cursor.getInt(rollNoIndex);
            String name = cursor.getString(nameIndex);
            String gender = cursor.getString(genderIndex);

            // Delete the record
            int rowsDeleted = db.delete("student", whereClause, whereArgs);

            if (rowsDeleted > 0) {
                // Show a Toast with the retrieved data
                Toast.makeText(context, "Deleted record: Roll No: " + rollNo + ", Name: " + name + ", Gender: " + gender, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to delete record", Toast.LENGTH_SHORT).show();
            }
        } else {
            // No data found in the cursor
            Toast.makeText(context, "No record found", Toast.LENGTH_SHORT).show();
        }

        cursor.close(); // Close the cursor to avoid resource leaks
    }

}
