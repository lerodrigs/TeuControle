package com.teucontrole.teucontrole.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences
{
    private Context context;
    private SharedPreferences sharedPreferences;

    public UserPreferences(Context _context)
    {
        this.context = _context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void set(String key, String value)
    {
        try
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            if(get(key)!= null)
            {
                remove(key);
            }

            editor.putString(key, value);
            editor.commit();
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    public String get(String key)
    {
        try
        {
            return sharedPreferences.getString(key, null);
        }
        catch (Exception e )
        {
            e.printStackTrace();
            return null;
        }
    }

    public void remove(String key)
    {
        try
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove(key);
            editor.commit();
        }
        catch (Exception e){ }
    }

}
