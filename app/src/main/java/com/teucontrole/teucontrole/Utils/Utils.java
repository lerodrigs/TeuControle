package com.teucontrole.teucontrole.Utils;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils
{
    public static String isNullOrEmpty(String word)
    {
        try
        {
            if (word.isEmpty())
                return null;
            else
                return word;

        }
        catch (Exception e )
        {
            throw e;
        }
    }

    public static String DateFormatting(Date date, String format)
    {
        String formatted = null;

        try
        {
            DateFormat dateFormat = new SimpleDateFormat(format);
            formatted = dateFormat.format(date);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally
        {
            return formatted;
        }
    }

    public static String getValueJObject(JSONObject _object, String key) throws Exception
    {
        String value = null;

        try
        {
            if(_object.getString(key) != null && !_object.getString(key).contains("null"))
            {
                value = _object.getString(key);
            }
        }
        catch(Exception e)
        {
            throw e;
        }

        return value;
    }

    public static String getDateFromJObject(JSONObject jsonObject, String key) throws Exception
    {
        String data = null;

        try
        {
            data = getValueJObject(jsonObject, key);

            if(data != null)
            {
                data = data.substring(0,10);
            }

        }
        catch (Exception e)
        {
            throw e;
        }

        return data;
    }



}
