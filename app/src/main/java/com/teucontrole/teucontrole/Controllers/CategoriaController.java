package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.CategoriaRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Repository.CategoriaRepository;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoriaController
{
    private Context context;
    private CategoriaRequest categoriaRequest;
    private PerfilController perfilController;
    private CategoriaRepository categoriaRepository;

    private String pk;
    private boolean isReceita;

    public CategoriaController(Context _context, boolean _receita)
    {
        this.context = _context;
        this.categoriaRequest = new CategoriaRequest(context);
        this.perfilController = new PerfilController(context);
        this.categoriaRepository = new CategoriaRepository(context, _receita);

        this.pk = "id_categoria_";
        this.isReceita = _receita;

        if(isReceita)
            pk = pk + "receita";
        else
            pk = pk + "despesa";

    }

    public void start() throws Exception
    {
        try
        {
            String[] ids = perfilController.getIdPerfilUserLogged();
            String id_perfil = null;

            for(int c=0; c < ids.length; c++)
            {
                id_perfil = ids[c];
                JSONArray jArrayCategoriasReceitas = requestCategoriasReceitas(id_perfil);
                processToDB(jArrayCategoriasReceitas);
            }

            this.isReceita = false;
            this.categoriaRepository = new CategoriaRepository(context, isReceita);
            this.pk = "id_categoria_despesa";

            for(int c=0; c < ids.length; c++ )
            {
                id_perfil = ids[c];
                JSONArray jArrayCategoriasDespesas = requestCategoriasDespesas(id_perfil);
                processToDB(jArrayCategoriasDespesas);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public boolean processToDB(JSONArray jArray) throws Exception
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
                        insert(jObject);
                    }
                }
            }
        }
        catch (Exception e){
            throw e;
        }

        return response;
    }

    public JSONArray requestCategoriasDespesas(String id_perfil) throws Exception
    {
        JSONArray jArray = new JSONArray();

        try
        {
            jArray = categoriaRequest.getCategoriasDespesas(id_perfil);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jArray;
    }

    public JSONArray requestCategoriasReceitas(String id_perfil) throws Exception
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

    public boolean insert(JSONObject jsonObject) throws Exception
    {
        boolean response = false;

        try
        {
            if(getCategoria(jsonObject.getString(pk)) == null)
                categoriaRepository.insert(jsonObject);
            else
                categoriaRepository.update(jsonObject);
        }
        catch (Exception e)
        {
             throw e;
        }

        return response;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean response = false;

        try
        {
            if(getCategoria(jObject.getString(pk)) != null)
                response = update(jObject);
            else
                response = insert(jObject);
        }
        catch (Exception e )
        {
            throw e;
        }

        return response;
    }

    public JSONObject getCategoria(String id_categoria) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = categoriaRepository.getCategoria(id_categoria);
        }
        catch (Exception e )
        {
            throw e;
        }

        return jObject;
    }

  public JSONArray getCategorias(String id_perfil) throws Exception
  {
    JSONArray jArray = null;

    try
    {
        jArray = categoriaRepository.getCategorias(id_perfil);
    }
    catch (Exception e)
    {
        throw e;
    }

    return jArray;
  }
}
