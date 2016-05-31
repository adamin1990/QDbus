package com.adamin.android.qdbus.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.adamin.android.qdbus.domain.BusLineDomain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam on 2016/5/31.
 */
public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    private static DBManager dbManager;

    private DBManager(Context context) {

        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    public static DBManager getInstance(Context context) {
       if(dbManager==null){
           dbManager=new DBManager(context);

       }
        return dbManager;
    }

    /**
     * 添加数据
     *
     * @param busLineDomain
     */
    public void add(BusLineDomain busLineDomain) {
        db.beginTransaction();
        if (queryexit(busLineDomain)) {
        } else {
            db.execSQL("INSERT INTO buslinedomain VALUES(?,?,?,?,?)", new Object[]{
                    busLineDomain.getGuid(), busLineDomain.getLineName(),
                    busLineDomain.getBStation(), busLineDomain.getEStation(),
                    busLineDomain.getDirect()
            });
        }

        db.setTransactionSuccessful();  //设置事务成功完成
        db.endTransaction();

    }


    /**
     * query all persons, return list
     *
     * @return List<Person>
     */
    public boolean queryexit(BusLineDomain busLineDomain) {
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            if (c.getString(c.getColumnIndex("guid")) != null && (busLineDomain.getGuid().equals(c.getString(c.getColumnIndex("guid"))))) {
                return true;
            }
        }
        c.close();
        return false;
    }


    /**
     * query all persons, return cursor
     *
     * @return Cursor
     */
    public Cursor queryTheCursor() {
        Cursor c = db.rawQuery("SELECT * FROM buslinedomain", null);
        return c;
    }

    /**
     * close database
     */
    public void closeDB() {
        db.close();
    }


    /**
     * delete
     *
     * @param person
     */
    public void delete(BusLineDomain person) {
        db.delete("buslinedomain", "guid"+"="+person.getGuid(), null);
    }

    /**
     * 查询列表
     *
     * @return
     */
    public List<BusLineDomain> query() {
        ArrayList<BusLineDomain> persons = new ArrayList<BusLineDomain>();
        Cursor c = queryTheCursor();
        while (c.moveToNext()) {
            BusLineDomain person = new BusLineDomain();
            person.setGuid(c.getString(c.getColumnIndex("guid")));
            person.setBStation(c.getString(c.getColumnIndex("bstation")));
            person.setEStation(c.getString(c.getColumnIndex("estation")));
            person.setLineName(c.getString(c.getColumnIndex("linename")));
            person.setDirect(c.getInt(c.getColumnIndex("direct")));

            persons.add(person);
        }
        c.close();
        return persons;
    }
}
