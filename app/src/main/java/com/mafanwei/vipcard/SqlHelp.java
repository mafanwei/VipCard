package com.mafanwei.vipcard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mafanwei on 2018/2/6.
 */

public class SqlHelp extends SQLiteOpenHelper {

    public SqlHelp(Context context)
    {
        super(context,"mfwmade", null, 1);
    }


    public SqlHelp(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table vipcard ( _id integer primary key autoincrement , name varchar(200) , content varchar(200) , uptime date ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
