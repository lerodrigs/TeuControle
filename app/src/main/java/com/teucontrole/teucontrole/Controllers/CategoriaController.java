package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.CategoriaRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoriaController
{
    private Context context;
    private CategoriaRequest categoriaRequest;
    private PerfilController perfilController;

    public CategoriaController(Context _context)
    {
        this.context = _context;
        this.categoriaRequest = new CategoriaRequest(context);
        this.perfilController = new PerfilController(context);
    }

    public void start() throws Exception
    {
        try
        {
            String[] ids = perfilController.getIdPerfilUserLogged();
            String id_perfil = null;

            //revisar.

            for(int c=0; c < ids.length; c++ )
            {
                id_perfil = ids[c];
                JSONArray jArrayCategoriasDespesas = requestCategoriasDespesas(id_perfil);
                processToDB(jArrayCategoriasDespesas, false);

                jArrayCategoriasDespesas = null;
            }

            for(int c=0; c < ids.length; c++)
            {
                id_perfil = ids[0];
                JSONArray jArrayCategoriasReceitas = requestCategoriasReceitas(id_perfil);
                processToDB(jArrayCategoriasReceitas, true);

                jArrayCategoriasReceitas = null;
            }
        }
        catch (Exception e)
        {
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

    public JSONArray requestCategoriasDespesas(String id_perfil) throws Exception
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

    public JSONArray requestCategoriasReceitas(String id_perfil) throws Exception
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
            response = true;
        }
        catch (Exception e )
        {
            throw e;
        }

        return response;
    }
}
