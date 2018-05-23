package com.teucontrole.teucontrole.Utils;


import android.database.Cursor;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ApiUtils
{
    public String InputStreamToString (InputStream inputStream)
    {
        try
        {
            BufferedReader bufferedReader;
            StringBuffer stringBuffer = new StringBuffer();
            String line;

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuffer.append(line);
            }

            return stringBuffer.toString();

        }
        catch (Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public JSONArray cursorToJSONArray(Cursor cursor)
    {
        try
        {
            JSONArray jsonArray = new JSONArray();
            cursor.moveToFirst();

            while(cursor.isAfterLast() == false)
            {
                JSONObject jObject = new JSONObject();

                for(int c=0; c <= cursor.getColumnCount(); c++)
                {
                    jObject.put(cursor.getColumnName(c), cursor.getString(c));
                }

                jsonArray.put(jObject);
            }

            return jsonArray;
        }
        catch (Exception e )
        {
            return null;
        }
    }

}
