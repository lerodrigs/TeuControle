package com.teucontrole.teucontrole.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

public class MyDbHelper extends SQLiteOpenHelper
{
    private Context context;
    private static int dbVersion = 1;
    private static String dbName = "teuControle_db";

    public MyDbHelper (Context _context)
    {
        super(_context, dbName, null, dbVersion);
        this.context = _context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            StringBuffer scriptDb = new StringBuffer();

            scriptDb.append("CREATE TABLE IF NOT EXISTS USERINFO  (");
            scriptDb.append(" ID INTEGER PRIMARY KEY AUTO INCREMENT NOT NULL, EMAIL VARCHAR(100) NOT NULL, PASS NTEXT NOT NULL, ");
            scriptDb.append(" ISLOGGED VARCHAR(1) NOT NULL);");

            db.execSQL(scriptDb.toString());
        }
        catch (Exception e )
        {
            e .printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {

        }
        catch (Exception e )
        {
            e .printStackTrace();
        }
    }
}
