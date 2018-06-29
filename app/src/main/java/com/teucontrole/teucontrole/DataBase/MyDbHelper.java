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
                    "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "email VARCHAR(100) NOT NULL, " +
                    "pass  TEXT NOT NULL);");

            db.execSQL("CREATE TABLE IF NOT EXISTS TITULOS_STATUS(" +
                    "id_titulo_status TEXT PRIMARY KEY NOT NULL," +
                    "nome VARCHAR(50));");

            db.execSQL("CREATE TABLE IF NOT EXISTS ESTADOS (" +
                    "id_estado INTEGER PRIMARY KEY NOT NULL, " +
                    "nome VARCHAR(80), " +
                    "uf VARCHAR(5));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CIDADES (" +
                    "id_cidade INTEGER PRIMARY KEY NOT NULL, " +
                    "id_estado INTEGER, " +
                    "nome VARCHAR(80)," +
                    "FOREIGN KEY (id_estado) REFERENCES ESTADOS (id_estado));");

            db.execSQL("CREATE TABLE IF NOT EXISTS USUARIOS (" +
                    "id_usuario      INTEGER PRIMARY KEY NOT NULL, " +
                    "nome            VARCHAR(80)," +
                    "sexo            VARCHAR(1), " +
                    "id_estado       INTEGER, " +
                    "id_cidade       INTEGER, " +
                    "data_nascimento DATE, " +
                    "email           VARCHAR(100), " +
                    "validade_assinatura DATE, " +
                    "FOREIGN KEY (id_estado) REFERENCES ESTADOS (id_estado), " +
                    "FOREIGN KEY (id_cidade) REFERENCES CIDADES (id_cidade) );");

            db.execSQL("CREATE TABLE IF NOT EXISTS PERFIS_USUARIOS (" +
                     "id_perfil TEXT NOT NULL, " +
                     "id_usuario INTEGER NOT NULL, " +
                     "nome       VARCHAR(100), " +
                     "descricao  TEXT, " +
                     "is_default BOOLEAN," +
                     "PRIMARY KEY(id_perfil, id_usuario), " +
                     "FOREIGN KEY (id_usuario) REFERENCES USUARIOS (id_usuario));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORIAS_RECEITAS (" +
                     "id_categoria_receita TEXT PRIMARY KEY NOT NULL, " +
                     "id_perfil TEXT NOT NULL, " +
                     "nome VARCHAR(150), " +
                     "descricao VARCHAR(300), " +
                     "cor VARCHAR(10), " +
                     "FOREIGN KEY(id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CATEGORIAS_DESPESAS (" +
                    "id_categoria_despesa TEXT PRIMARY KEY NOT NULL, " +
                    "id_perfil TEXT NOT NULL, " +
                    "nome VARCHAR(150), " +
                    "descricao VARCHAR(300), " +
                    "cor VARCHAR(10), " +
                    "FOREIGN KEY(id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil));");

            db.execSQL("CREATE TABLE IF NOT EXISTS CONTAS_BANCARIAS ( " +
                    "id_conta TEXT PRIMARY KEY NOT NULL, " +
                    "id_usuario_criador INTEGER, " +
                    "nome VARCHAR(120), " +
                    "id_perfil TEXT NOT NULL, " +
                    "saldo NUMERIC(15,2), " +
                    "ativa BOOLEAN, " +
                    "id_conta_tipo INTEGER, " +
                    "id_conta_bancaria_tipo INTEGER, " +
                    "nome1 VARCHAR(120), " +
                    "nome2 VARCHAR(120), " +
                    "dia_fechamento INTENGER, " +
                    "dia_vencimento INTENGER, " +
                    "limite NUMERIC(15,2), " +
                    "id_bandeira INTEGER, " +
                    "FOREIGN KEY (id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil))");

            db.execSQL("CREATE TABLE IF NOT EXISTS RECEITAS (" +
                    "id_receita TEXT PRIMARY KEY NOT NULL, " +
                    "nome VARCHA(100) NOT NULL, " +
                    "valor NUMERIC(15,2) NOT NULL, " +
                    "valor_recebido NUMERIC(15,2), " +
                    "data_vencimento DATETIME NOT NULL, " +
                    "data_pagamento DATETIME, " +
                    "id_perfil TEXT NOT NULL, " +
                    "data_cadastro DATETIME, " +
                    "data_modificacao DATETIME, " +
                    "id_conta TEXT NOT NULL, " +
                    "id_receita_recorrente TEXT, " +
                    "id_categoria_receita TEXT, " +
                    "descricao VARCHAR(300), " +
                    "excecao INT, " +
                    "data_original DATETIME, " +
                    "id_titulo_status BYTE, " +
                    "conta_nome VARCHAR(100), " +
                    "categoria_receita_nome VARCHAR(100), " +
                    "cor VARCHAR(20), " +
                    "id_recorrente_frequencia TEXT, " +
                    "inicio DATETIME, " +
                    "termino DATETIME, " +
                    "intervalo DATETIME, " +
                    "total_ocorrencias INT, " +
                    "tipo_fim INT, " +
                    "FOREIGN KEY (id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil), " +
                    "FOREIGN KEY (id_categoria_receita) REFERENCES CATEGORIAS_RECEITAS (id_categoria_receita), " +
                    "FOREIGN KEY (id_conta) REFERENCES CONTAS_BANCARIAS (id_conta));");

            db.execSQL("CREATE TABLE IF NOT EXISTS FATURAS (" +
                    "id_fatura TEXT PRIMARY KEY NOT NULL, " +
                    "id_perfil TEXT NOT NULL," +
                    "data_vencimento DATETIME, " +
                    "data_fechamento DATETIME, " +
                    "valor_total NUMERIC(15,2), " +
                    "valor_pago NUMERIC(15,2), " +
                    "data_pagamento DATETIME, " +
                    "id_conta TEXT, " +
                    "data_cadastro DATETIME," +
                    "data_modificacao DATETIME, " +
                    "id_conta_que_paga TEXT, " +
                    "id_titulo_status INT, " +
                    "FOREIGN KEY (id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil), " +
                    "FOREIGN KEY (id_conta) REFERENCES CONTAS_BANCARIAS (id_conta)); ");

            db.execSQL("CREATE TABLE IF NOT EXISTS DESPESAS (" +
                    "id_despesa TEXT PRIMARY KEY NOT NULL, " +
                    "nome VARCHA(100) NOT NULL, " +
                    "valor NUMERIC(15,2) NOT NULL, " +
                    "valor_pago NUMERIC(15,2), " +
                    "data_vencimento DATETIME NOT NULL, " +
                    "data_pagamento DATETIME, " +
                    "id_perfil TEXT NOT NULL, " +
                    "data_cadastro DATETIME, " +
                    "data_modificacao DATETIME, " +
                    "id_conta TEXT, " +
                    "id_despesa_recorrente TEXT, " +
                    "id_categoria_despesa TEXT, " +
                    "descricao VARCHAR(300), " +
                    "excecao INT, " +
                    "data_original DATETIME, " +
                    "id_titulo_status BYTE, " +
                    "conta_nome VARCHAR(100), " +
                    "categoria_despesa_nome VARCHAR(100), " +
                    "cor VARCHAR(20), " +
                    "id_recorrente_frequencia TEXT, " +
                    "inicio DATETIME, " +
                    "termino DATETIME, " +
                    "intervalo DATETIME, " +
                    "total_ocorrencias INT, " +
                    "tipo_fim INT," +
                    "id_fatura TEXT, " +
                    "mae INT, " +
                    "FOREIGN KEY (id_perfil) REFERENCES PERFIS_USUARIOS (id_perfil), " +
                    "FOREIGN KEY (id_categoria_despesa) REFERENCES CATEGORIAS_DESPESAS (id_categoria_despesa), " +
                    "FOREIGN KEY (id_conta) REFERENCES CONTAS_BANCARIAS (id_conta), " +
                    "FOREIGN KEY (id_fatura) REFERENCES FATURA (id_fatura));");


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
