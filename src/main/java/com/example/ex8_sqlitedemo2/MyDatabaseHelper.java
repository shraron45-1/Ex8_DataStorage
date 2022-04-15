package com.example.ex8_sqlitedemo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement,"
            + "author text,"
            + "price real,"
            + "pages integer,"
            + "name text,"
            + "category_id integer)";
    public static final String CREATE_CATEGORY = "create table Category ("
            +"id integer primary key autoincrement,"
            +"category_name text,"
            +"category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);

        ContentValues values = new ContentValues();
        //组装第一条数据
        values.put("category_name", "计算机学");
        values.put("category_code", 101);
        db.insert("Category",null,values);

        values.clear();
        values.put("category_name", "经济学");
        values.put("category_code",1022);
        db.insert("Category",null,values);

        values.clear();
        values.put("name", "The Da");
        values.put("author", "Brown");
        values.put("pages", 681);
        values.put("price", 32.45);
        values.put("category_id", 1);
        db.insert("Book",null,values);

        Toast.makeText(mContext, "Create succeeded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.disableWriteAheadLogging();
    }
}
