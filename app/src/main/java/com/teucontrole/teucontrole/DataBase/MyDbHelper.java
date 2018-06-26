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

            db.execSQL("CREATE TABLE IF NOT EXISTS TITULOS_STATUS(" +
                    "ID_TITULO_STATUS TEXT PRIMARY KEY NOT NULL," +
                    "NOME VARCHAR(50));");

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
                     "ID_PERFIL TEXT NOT NULL, " +
                     "ID_USUARIO INTEGER NOT NULL, " +
                     "NOME       VARCHAR(100), " +
                     "DESCRICAO  TEXT, " +
                     "IS_DEFAULT BOOLEAN," +
                     "PRIMARY KEY(ID_PERFIL, ID_USUARIO), " +
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

            db.execSQL("CREATE TABLE IF NOT EXISTS CONTAS_BANCARIAS ( " +
                    "ID_CONTA TEXT PRIMARY KEY NOT NULL, " +
                    "ID_USUARIO_CRIADOR INTEGER, " +
                    "NOME VARCHAR(120), " +
                    "ID_PERFIL TEXT NOT NULL, " +
                    "SALDO NUMERIC(15,2), " +
                    "ATIVA BOOLEAN, " +
                    "ID_CONTA_TIPO INTEGER, " +
                    "ID_CONTA_BANCARIA_TIPO INTEGER, " +
                    "NOME1 VARCHAR(120), " +
                    "NOME2 VARCHAR(120), " +
                    "DIA_FECHAMENTO INTENGER, " +
                    "DIA_VENCIMENTO INTENGER, " +
                    "LIMITE NUMERIC(15,2), " +
                    "ID_BANDEIRA INTEGER, " +
                    "FOREIGN KEY (ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL))");

            db.execSQL("CREATE TABLE IF NOT EXISTS RECEITAS (" +
                    "ID_RECEITA TEXT PRIMARY KEY NOT NULL, " +
                    "NOME VARCHA(100) NOT NULL, " +
                    "VALOR NUMERIC(15,2) NOT NULL, " +
                    "VALOR_RECEBIDO NUMERIC(15,2), " +
                    "DATA_VENCIMENTO DATETIME NOT NULL, " +
                    "DATA_PAGAMENTO DATETIME, " +
                    "ID_PERFIL TEXT NOT NULL, " +
                    "DATA_CADASTRO DATETIME, " +
                    "DATA_MODIFICACAO DATETIME, " +
                    "ID_CONTA TEXT NOT NULL, " +
                    "ID_RECEITA_RECORRENTE TEXT, " +
                    "ID_CATEGORIA_RECEITA TEXT, " +
                    "DESCRICAO VARCHAR(300), " +
                    "EXCECAO INT, " +
                    "DATA_ORIGINAL DATETIME, " +
                    "ID_TITULO_STATUS BYTE, " +
                    "CONTA_NOME VARCHAR(100), " +
                    "CATEGORIA_RECEITA_NOME VARCHAR(100), " +
                    "COR VARCHAR(20), " +
                    "ID_RECORRENTE_FREQUENCIA TEXT, " +
                    "INICIO DATETIME, " +
                    "TERMINO DATETIME, " +
                    "INTERVALO DATETIME, " +
                    "TOTAL_OCORRENCIAS INT, " +
                    "TIPO_FIM INT, " +
                    "FOREIGN KEY (ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL), " +
                    "FOREIGN KEY (ID_CATEGORIA_RECEITA) REFERENCES CATEGORIAS_RECEITAS (ID_CATEGORIA_RECEITA), " +
                    "FOREIGN KEY (ID_CONTA) REFERENCES CONTAS_BANCARIAS (ID_CONTA));");

            db.execSQL("CREATE TABLE IF NOT EXISTS FATURAS (" +
                    "ID_FATURA TEXT PRIMARY KEY NOT NULL, " +
                    "ID_PERFIL TEXT NOT NULL," +
                    "DATA_VENCIMENTO DATETIME, " +
                    "DATA_FECHAMENTO DATETIME, " +
                    "VALOR_TOTAL NUMERIC(15,2), " +
                    "VALOR_PAGO NUMERIC(15,2), " +
                    "DATA_PAGAMENTO DATETIME, " +
                    "ID_CONTA TEXT, " +
                    "DATA_CADASTRO DATETIME," +
                    "DATA_MODIFICACAO DATETIME, " +
                    "ID_CONTA_QUE_PAGA TEXT, " +
                    "ID_TITULO_STATUS INT, " +
                    "FOREIGN KEY (ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL), " +
                    "FOREIGN KEY (ID_CONTA) REFERENCES CONTAS_BANCARIAS (ID_CONTA)); ");

            db.execSQL("CREATE TABLE IF NOT EXISTS DESPESAS (" +
                    "ID_DESPESA TEXT PRIMARY KEY NOT NULL, " +
                    "NOME VARCHA(100) NOT NULL, " +
                    "VALOR NUMERIC(15,2) NOT NULL, " +
                    "VALOR_RECEBIDO NUMERIC(15,2), " +
                    "DATA_VENCIMENTO DATETIME NOT NULL, " +
                    "DATA_PAGAMENTO DATETIME, " +
                    "ID_PERFIL TEXT NOT NULL, " +
                    "DATA_CADASTRO DATETIME, " +
                    "DATA_MODIFICACAO DATETIME, " +
                    "ID_CONTA TEXT NOT NULL, " +
                    "ID_DESPESA_RECORRENTE TEXT, " +
                    "ID_CATEGORIA_DESPESA TEXT, " +
                    "DESCRICAO VARCHAR(300), " +
                    "EXCECAO INT, " +
                    "DATA_ORIGINAL DATETIME, " +
                    "ID_TITULO_STATUS BYTE, " +
                    "CONTA_NOME VARCHAR(100), " +
                    "CATEGORIA_DESPESA_NOME VARCHAR(100), " +
                    "COR VARCHAR(20), " +
                    "ID_RECORRENTE_FREQUENCIA TEXT, " +
                    "INICIO DATETIME, " +
                    "TERMINO DATETIME, " +
                    "INTERVALO DATETIME, " +
                    "TOTAL_OCORRENCIAS INT, " +
                    "TIPO_FIM INT," +
                    "ID_FATURA TEXT, " +
                    "MAE INT, " +
                    "FOREIGN KEY (ID_PERFIL) REFERENCES PERFIS_USUARIOS (ID_PERFIL), " +
                    "FOREIGN KEY (ID_CATEGORIA_DESPESA) REFERENCES CATEGORIAS_DESPESAS (ID_CATEGORIA_DESPESA), " +
                    "FOREIGN KEY (ID_CONTA) REFERENCES CONTAS_BANCARIAS (ID_CONTA), " +
                    "FOREIGN KEY (ID_FATURA) REFERENCES FATURA (ID_FATURA));");


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
