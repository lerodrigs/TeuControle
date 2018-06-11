package com.teucontrole.teucontrole.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

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

    public JSONArray get(String command) throws Exception
    {
        JSONArray jsonArray = new JSONArray();
        ApiUtils apiUtils = new ApiUtils();

        try
        {
            SQLiteDatabase db  = myDbHelper.getReadableDatabase();
            Cursor cursor = db.rawQuery(command, null);

            if(cursor != null)
            {
                jsonArray = apiUtils.cursorToJSONArray(cursor);
            }

            db.close();
        }
        catch (Exception e)
        {
            throw e;
        }

        return jsonArray;
    }


    public void execCommand(String command)
    {
        try
        {
            SQLiteDatabase db = myDbHelper.getWritableDatabase();

            if(!db.isOpen())
            {
                return;
            }

            db.execSQL(command);
            db.close();
        }
        catch (SQLiteException e)
        {
            throw e;
        }
    }
}
