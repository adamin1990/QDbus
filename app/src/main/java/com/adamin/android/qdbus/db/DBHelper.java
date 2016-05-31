package com.adamin.android.qdbus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adam on 2016/5/31.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="qdbus.db";
    private static final int DB_VERSION=1;

   public DBHelper(Context context){
       super(context,DB_NAME,null,DB_VERSION);
   }
/*{
 "lineName": "隧道7路",
 "bStation": "利津路客运站",
 "eStation": "灵山卫公交枢纽站",
 "Guid": "300007",
 "Direct": 0
 }*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS buslinedomain" +
                "(guid VARCHAR PRIMARY KEY, linename VARCHAR,bstation VARCHAR," +
                "estation VARCHAR,direct INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
