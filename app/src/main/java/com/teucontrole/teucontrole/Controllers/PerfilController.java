package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import android.util.Log;

import com.teucontrole.teucontrole.Api.PerfilRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Repository.PerfilRepository;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PerfilController
{
    private Context context;
    private PerfilRequest perfilRequest;
    private UserPreferences userPreferences;
    private PerfilRepository perfilRepository;

    public PerfilController(Context _context)
    {
        this.context = _context;
        this.perfilRequest = new PerfilRequest(_context);
        this.perfilRepository = new PerfilRepository(context);
        this.userPreferences = new UserPreferences(context);
    }

    public JSONArray requestAll() throws Exception
    {
        JSONArray jsonArray = new JSONArray();

        try
        {
            jsonArray = perfilRequest.getAll();
        }
        catch (Exception e )
        {
            Log.e("Error", e.getMessage());
            throw e;
        }

        return jsonArray;
    }

    public JSONObject requestById(String id_perfil)
    {
        try
        {
            JSONObject jObject = perfilRequest.getById(id_perfil);

            return jObject;
        }
        catch (Exception e ){ return null; }
    }

    public String getPerfilDefault() throws Exception
    {
        String id_perfil = null;

        try
        {
            id_perfil = perfilRepository.getPerfilDefault();
        }
        catch (Exception e){
            throw e;
        }

        return id_perfil;
    }

    public String getIdPerfilSelecionado() throws Exception
    {
        String id_perfil_selecionado =null;

        try
        {
            id_perfil_selecionado = userPreferences.get("id_perfil_selecionado");
        }
        catch (Exception e){
            throw e;
        }

        return id_perfil_selecionado;
    }

    public void setIdPerfilSelecionado(String id_perfil_selecionado) throws Exception
    {
        try
        {
            if(id_perfil_selecionado != null && !id_perfil_selecionado.isEmpty())
                userPreferences.set("id_perfil_selecionado", id_perfil_selecionado);
        }
        catch (Exception e){
            throw e;
        }
    }

    public void start() throws Exception
    {
        try
        {
            JSONArray jArray = requestAll();

            if(jArray != null && jArray.length() > 0)
            {
                for (int c=0; c < jArray.length(); c++)
                {
                    JSONObject jsonObject = jArray.getJSONObject(c);

                    if(jsonObject != null)
                    {
                        insert(jsonObject);
                    }
                }
            }
        }
        catch (Exception e )
        {
            Log.e("Error", e.getMessage());
            throw e;
        }
    }

    public JSONObject getByIds(String id_perfil, int id_usuario) throws Exception
    {
        JSONObject jsonObject = null;

        try
        {
            jsonObject = perfilRepository.getByIds(id_perfil, id_usuario);
        }
        catch (Exception e )
        {
            throw e;
        }

        return jsonObject;
    }

    public String[] getIdPerfilUserLogged() throws Exception
    {
        String[] id_perfil = null;

        try
        {
            id_perfil = perfilRepository.getIdPerfilUserLogged();
        }

        catch (Exception e)
        {
            throw e;
        }

        return id_perfil;
    }

    public boolean insert(JSONObject jsonObject) throws Exception
    {
        boolean result = false;

        try
        {
            if(getByIds(jsonObject.getString("id_perfil"), jsonObject.getInt("id_usuario")) == null)
                result = perfilRepository.insert(jsonObject);
            else
                result = update(jsonObject);
        }
        catch (Exception e )
        {
            throw  e;
        }

        return result;
    }

    public boolean update(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            int id_user = Integer.parseInt(Utils.getValueJObject(jObject, "id_usuario"));
            String id_perfil = Utils.getValueJObject(jObject, "id_perfil");

            if(getByIds(id_perfil, id_user) != null)
            {
                result = perfilRepository.update(jObject);
            }
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public boolean deleteFromDB(String id_perfil, int id_user)
    {
        boolean result = false;

        try
        {
            result = perfilRepository.delete(id_perfil, id_user);
        }
        catch (Exception e)
        {
            throw e;
        }

        return result;
    }

    public JSONArray getPerfis()
    {
        JSONArray jArray = null;

        try
        {
            jArray = perfilRepository.getPerfis();
        }
        catch (Exception e){

        }

        return jArray;
    }

}
