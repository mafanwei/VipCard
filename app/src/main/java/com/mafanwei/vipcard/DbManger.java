package com.mafanwei.vipcard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mafanwei on 2018/2/6.
 */

public class DbManger {
   static SqlHelp sqlHelp;
   static SQLiteDatabase dbing;
    public static SQLiteDatabase getSQLiteDatabase(Context context)
    {

        if(dbing==null)
        {
            sqlHelp=new SqlHelp(context);
            dbing=sqlHelp.getWritableDatabase();
        }

        return dbing;
    }

    public static List<Map<String,Object>> getVipCardAll(SQLiteDatabase db){
        List<Map<String,Object>> temp_list=new ArrayList<>();
        Cursor cursor;
        cursor=db.query("vipcard",null,null,null,null,null,null);
        while (cursor.moveToNext())
        {
            Map<String,Object> temp_item=new HashMap<>();
            temp_item.put("_id",cursor.getString(cursor.getColumnIndex("_id")));
            temp_item.put("name",cursor.getString(cursor.getColumnIndex("name")));
            temp_item.put("content",cursor.getString(cursor.getColumnIndex("content")));
            temp_item.put("uptime","最后更新于:"+cursor.getString(cursor.getColumnIndex("uptime")));
            temp_list.add(temp_item);
        }
        return temp_list;
    }

    public static void saveVipCard(SQLiteDatabase db, ContentValues values){
        db.insert("vipcard",null,values);
    }

    public static void deleteVipCard(SQLiteDatabase db,String id){
        db.delete("vipcard","_id=?",new String[]{id});
    }

    public static Map<String,Object> getVipCardById(SQLiteDatabase db,String _id) {
        Map<String,Object> temp_item=new HashMap<>();
        Cursor cursor;
        cursor= db.rawQuery("select * from vipcard where _id=?",new String[]{_id});
        while (cursor.moveToNext())
        {
            temp_item.put("_id",cursor.getString(cursor.getColumnIndex("_id")));
            temp_item.put("name",cursor.getString(cursor.getColumnIndex("name")));
            temp_item.put("content",cursor.getString(cursor.getColumnIndex("content")));
            temp_item.put("uptime",cursor.getString(cursor.getColumnIndex("uptime")));
        }
        return temp_item;
    }
}
