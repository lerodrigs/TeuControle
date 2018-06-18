package com.teucontrole.teucontrole.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

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

            db.execSQL("CREATE TABLE IF NOT EXISTS USER_INFO (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "EMAIL VARCHAR(100) NOT NULL, " +
                    "PASS  TEXT NOT NULL);");

            db.execSQL("CREATE TABLE IF NOT EXISTS ESTADOS (" +
                    "ID_ESTADO INTEGER PRIMARY KEY NOT NULL, " +
                    "NOME VARCHAR(80), " +
                    "UF VARCHAR(5));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CIDADES (" +
                    "ID_CIDADE INTEGER PRIMARY KEY NOT NULL, " +
                    "ID_ESTADO INTEGER, " +
                    "NOME VARCHAR(80)," +
                    "FOREIGN KEY (ID_ESTADO) REFERENCES ESTADOS (ID_ESTADO));");

            db.execSQL("CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "ID_USUARIO      INTEGER PRIMARY KEY NOT NULL, " +
                    "NOME            VARCHAR(80)," +
                    "SEXO            VARCHAR(1), " +
                    "ID_ESTADO       INTEGER, " +
                    "ID_CIDADE       INTEGER, " +
                    "DATA_NASCIMENTO DATE, " +
                    "EMAIL           VARCHAR(100), " +
                    "VALIDADE_ASSINATURA DATE, " +
                    "FOREIGN KEY (ID_ESTADO) REFERENCES ESTADOS (ID_ESTADO), " +
                    "FOREIGN KEY (ID_CIDADE) REFERENCES CIDADES (ID_CIDADE) );");

            db.execSQL("CREATE TABLE IF NOT EXISTS PERFIS_USUARIOS (" +
                    "ID_PERFIL_USUARIO INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "ID_PERFIL TEXT NOT NULL, " +
                    "ID_USUARIO INTEGER NOT NULL, " +
                    "NOME       VARCHAR(100), " +
                    "DESCRICAO  TEXT, " +
                    "IS_DEFAULT BOOLEAN, " +
                    "FOREIGN KEY (ID_USUARIO) REFERENCES USUARIOS (ID_USUARIO));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORIAS_RECEITAS (" +
                "ID_CATEGORIA_RECEITA TEXT PRIMARY KEY NOT NULL, " +
                "ID_PERFIL TEXT NOT NULL, " +
                "NOME VARCHAR(150), " +
                "DESCRICAO VARCHAR(300), " +
                "COR VARCHAR(10), " +
                "FOREIGN KEY(ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORIAS_DESPESAS (" +
                    "ID_CATEGORIA_DESPESA TEXT PRIMARY KEY NOT NULL, " +
                    "ID_PERFIL TEXT NOT NULL, " +
                    "NOME VARCHAR(150), " +
                    "DESCRICAO VARCHAR(300), " +
                    "COR VARCHAR(10), " +
                    "FOREIGN KEY(ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL));");


        }
        catch (Exception e )
        {
            Log.e("Error", e.getMessage());
            throw e;
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
            Log.e("Error", e.getMessage());
            throw e;
        }
    }

}
