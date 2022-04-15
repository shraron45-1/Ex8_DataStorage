package com.example.ex8_sqlitedemo2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    private List<BookDetails> bookDetailsList = new ArrayList<>();


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db",null, 2);
        Button createDateBase = (Button) findViewById(R.id.createDatebase); //创建数据库
        createDateBase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//                ContentValues values = new ContentValues();
//                //组装第一条数据
//                values.put("category_name", "计算机学");
//                values.put("category_code", 101);
//                db.insert("Category",null,values);
//                values.clear();
//                values.put("category_name", "经济学");
//                values.put("category_code",1022);
//                db.insert("Category",null,values);
//                values.clear();
//                values.put("name", "The Da");
//                values.put("author", "Brown");
//                values.put("pages", 681);
//                values.put("price", 32.45);
//                values.put("category_id", 1);
//                db.insert("Book",null,values);
            }
        });
        Button add_book = (Button) findViewById(R.id.add_book);
        //进入数据填充页面
        add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });


        //主界面数据展示
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        Cursor bookCursor = writableDatabase.rawQuery("select name , category_name , price from Book,Category where Book.category_id = Category.id",null);
        if (bookCursor.moveToFirst()) {
            do {
                BookDetails bookDetails = new BookDetails();
                bookDetails.setName(bookCursor.getString(bookCursor.getColumnIndex("name")));;
                bookDetails.setCategory_name(bookCursor.getString(bookCursor.getColumnIndex("category_name")));
                bookDetails.setPrice(bookCursor.getString(bookCursor.getColumnIndex("price")));
                bookDetailsList.add(bookDetails);
            }while (bookCursor.moveToNext());
        }
        bookCursor.close();
        BookDetailsAdapter bookDetailsAdapter = new BookDetailsAdapter(MainActivity.this, R.layout.book_item, bookDetailsList);
        ListView listView = (ListView) findViewById(R.id.list_book);
        listView.setAdapter(bookDetailsAdapter);
    }
}