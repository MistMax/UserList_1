package com.example.secondapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userBase.db";

    public UserBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+UserDbSchema.UserTable.NAME+" (" +
                "_id integer primary key autoincrement, " +
                UserDbSchema.Cols.UUID+", " +
                UserDbSchema.Cols.USERNAME+", " +
                UserDbSchema.Cols.USERLASTNAME+", " +
                UserDbSchema.Cols.PHONE+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
