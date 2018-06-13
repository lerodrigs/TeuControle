package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.CategoriaRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoriaController
{
    private MyDbAdapter myDbAdapter;
    private Context context;
    private CategoriaRequest categoriaRequest;
    private StringBuilder strCommand;

    private PerfilController perfilController;

    public CategoriaController(Context _context)
    {
        this.context = _context;
        this.myDbAdapter = new MyDbAdapter(context);
        this.categoriaRequest = new CategoriaRequest(context);

        this.perfilController = new PerfilController(context);
    }

    public void start() throws Exception
    {
        try
        {
            String id_perfil = perfilController.getIdPerfilUserLogged();

            JSONArray jArrayCategoriasDespesas = getCategoriasDespesas(id_perfil);
            processToDB(jArrayCategoriasDespesas, false);

            JSONArray jArrayCategoriasReceitas = getCategoriasReceitas(id_perfil);
            processToDB(jArrayCategoriasReceitas, true);
        }
        catch (Exception e){
            throw e;
        }
    }

    public boolean processToDB(JSONArray jArray, boolean receita) throws Exception
    {
        boolean response = false;
        try
        {
            if(jArray != null && jArray.length() > 0)
            {
                for(int c=0; c < jArray.length(); c++)
                {
                    JSONObject jObject = jArray.getJSONObject(c);

                    if(jObject != null)
                    {
                        insertDB(jObject, receita);
                    }
                }
            }
        }
        catch (Exception e){
            throw e;
        };

        return response;
    }

    public JSONArray getCategoriasDespesas(String id_perfil) throws Exception
    {
        JSONArray jArray = new JSONArray();

        try
        {
            jArray = categoriaRequest.getCategoriasReceitas(id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONArray getCategoriasReceitas(String id_perfil) throws Exception
    {
        JSONArray jArray = null;

        try
        {
            jArray = categoriaRequest.getCategoriasReceitas(id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public boolean insertDB(JSONObject jsonObject, boolean receita) throws Exception
    {
        boolean response = false;

        try
        {
            strCommand = new StringBuilder();

            if(jsonObject != null)
            {
                if(receita)
                {
                    strCommand.append("INSERT INTO CATEGORIAS_RECEITAS ");
                    strCommand.append("( ");
                }
                else
                {
                    strCommand.append("INSERT INTO CATEGORIAS_DESPESAS ");
                    strCommand.append("( ");
                }

                myDbAdapter.execCommand(strCommand.toString());
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return response;
    }

    public boolean updateDB(JSONObject jObject, boolean receita) throws Exception
    {
        boolean response = false;

        try
        {
            strCommand = new StringBuilder();

            if(receita)
            {
                strCommand.append("UPDATE CATEGORIAS_RECEITAS");
            }
            else {
                strCommand.append("UPDATE CATEGORIAS_DESPESAS");
            }

            myDbAdapter.execCommand(strCommand.toString());

            response = true;
        }
        catch (Exception e )
        {
            throw e;
        }

        return response;
    }
}
