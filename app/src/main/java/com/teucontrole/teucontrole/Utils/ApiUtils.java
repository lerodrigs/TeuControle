package com.teucontrole.teucontrole.Utils;


import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.teucontrole.teucontrole.Actitivies.SplashScreenActivity.context;

public class ApiUtils
{
    public String InputStreamToString (InputStream inputStream) throws Exception
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder builder = new StringBuilder();
        String line = null;

        try
        {
            while((line = bufferedReader.readLine()) != null)
            {
                builder.append(line);
            }
        }
        catch (Exception e )
        {
            Log.e("Error", e.getMessage());
            throw e;
        }

        return builder.toString();
    }

    public JSONArray cursorToJSONArray(Cursor cursor) throws Exception
    {
        JSONArray jsonArray = new JSONArray();

        try
        {
            if(cursor.getCount() == 0)
            {
                return jsonArray;
            }

            cursor.moveToFirst();

            while(!cursor.isAfterLast())
            {

                JSONObject jObject = new JSONObject();

                for(int c=0; c < cursor.getColumnCount(); c++)
                {
                    jObject.put(cursor.getColumnName(c), cursor.getString(c));
                }

                jsonArray.put(jObject);
                cursor.moveToNext();
            }
        }
        catch (Exception e )
        {
            throw e;
        }

        return jsonArray;
    }
}
