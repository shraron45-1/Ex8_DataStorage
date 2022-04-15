package com.example.ex8_sqlitedemo2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db",null, 2);
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBookActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button addDate = (Button) findViewById(R.id.add_database);
        addDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                values.put("category_id", 1);
                db.insert("Book", null, values);//输入第一条数据
                values.clear();
                //第二条
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                values.put("category", 2);
                db.insert("Book", null, values);//输入第二条数据
            }
        });
        Button updateDate = (Button) findViewById(R.id.update_database);
        updateDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                db.update("Book", values, "name = ?",
                        new String[]{"The Da Vinci Code"});
            }
        });
        Button deleteDate = (Button) findViewById(R.id.delete_database);
        deleteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book","pages > ?",
                        new String[]{"500"});
            }
        });
        Button queryDate = (Button) findViewById(R.id.query_database);
        queryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,null,
                        null,null,null,null);
                if (cursor.moveToFirst()) {
                    do {
                        //
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
    }
}