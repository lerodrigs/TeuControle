package com.teucontrole.teucontrole.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.teucontrole.teucontrole.Utils.ApiUtils;

import org.json.JSONArray;
import org.json.JSONObject;

public class MyDbAdapter
{
    private MyDbHelper myDbHelper;

    public MyDbAdapter (Context _context)
    {
        this.myDbHelper = new MyDbHelper(_context);
    }

    public JSONArray get(String command)
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            ApiUtils apiUtils = new ApiUtils();

            SQLiteDatabase db  = myDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(command, null);

            if(cursor != null)
            {
                jsonArray = apiUtils.cursorToJSONArray(cursor);
            }

            return jsonArray;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public void insert(String command)
    {
        SQLiteDatabase db = myDbHelper.getWritableDatabase();

        if(db.isOpen())
        {
            try
            {
                db.beginTransaction();
                db.execSQL(command);
                db.endTransaction();
                db.setTransactionSuccessful();
            }
            catch (SQLiteException e )
            {
                db.endTransaction();
                e.printStackTrace();
            }
        }

    }

    public void update (String command)
    {

    }

    public void delete(String command)
    {

    }


}
