package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoriaRepository
{
    private Context context;
    private UserPreferences userPreferences;
    private MyDbAdapter myDbAdapter;

    private StringBuilder strBuilder;
    private String table;
    private String pk;

    public CategoriaRepository(Context _context, boolean receita)
    {
        this.context = _context;
        this.userPreferences = new UserPreferences(context);
        this.myDbAdapter = new MyDbAdapter(context);

        this.strBuilder = new StringBuilder();
        this.table = "CATEGORIAS_";
        this.pk = "id_categoria_";

        if(receita){
            table = table + "RECEITAS";
            pk = pk + "receita";
        }
        else {
            table = table + "DESPESAS";
            pk = pk + "despesa";
        }
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            strBuilder = new StringBuilder();

            strBuilder.append("INSERT INTO "+table+" (");
            strBuilder.append(pk +", ");
            strBuilder.append("ID_PERFIL, ");
            strBuilder.append("NOME, ");
            strBuilder.append("DESCRICAO, ");
            strBuilder.append("COR ) VALUES ( ");
            strBuilder.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, pk)) + ", ");
            strBuilder.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "id_perfil")) + ", ");
            strBuilder.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) + ", ");
            strBuilder.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao")) + ", ");
            strBuilder.append(Utils.checkStringForExec(Utils.getValueJObject(jObject, "cor")) + "); ");

            result = myDbAdapter.execCommand(strBuilder.toString());
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            strBuilder = new StringBuilder();

            strBuilder.append("UPDATE " +table+" ");
            strBuilder.append("SET NOME = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome")) +", ");
            strBuilder.append("DESCRICAO = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao")) +", ");
            strBuilder.append("COR = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "cor"))+" ");
            strBuilder.append("WHERE " +pk+ " = " +Utils.checkStringForExec(Utils.getValueJObject(jObject, pk)));

            result = myDbAdapter.execCommand(strBuilder.toString());
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }

    public boolean delete(String id_categoria) throws Exception
    {
        boolean result = false;

        try
        {
            strBuilder = new StringBuilder();
            strBuilder.append("DELETE FROM "+ table + "WHERE "+pk + "= '" + id_categoria + "';");

            result = myDbAdapter.execCommand(strBuilder.toString());
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }

    public JSONObject getCategoria(String id_categoria) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            strBuilder = new StringBuilder();
            strBuilder.append("SELECT * FROM "+table+ " WHERE "+pk+ " = '" + id_categoria + "';");

            JSONArray jArray = myDbAdapter.get(strBuilder.toString());

            if(jArray != null && jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public JSONArray getCategorias(String id_perfil) throws Exception
    {
        JSONArray jsonArray = null;

        try
        {
           strBuilder = new StringBuilder();
           strBuilder.append("SELECT * FROM " + table + " WHERE id_perfil = '" +id_perfil+ "';");

           jsonArray = myDbAdapter.get(strBuilder.toString());
        }
        catch (Exception e)
        {
            throw e;
        }

        return jsonArray;
    }


}
