package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import android.util.Log;

import com.teucontrole.teucontrole.Api.PerfilRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PerfilController
{
    private Context context;
    private PerfilRequest perfilRequest;
    private MyDbAdapter myDbAdapter;
    private UserPreferences userPreferences;

    public PerfilController(Context _context)
    {
        this.context = _context;
        this.perfilRequest = new PerfilRequest(_context);
        this.myDbAdapter = new MyDbAdapter(context);
        this.userPreferences = new UserPreferences(context);
    }

    public JSONArray getAll() throws Exception
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

    public JSONObject getById(String id_perfil)
    {
        try
        {
            JSONObject jObject = perfilRequest.getById(id_perfil);

            return jObject;
        }
        catch (Exception e ){ return null; }
    }

    public void start() throws Exception
    {
        try
        {
            JSONArray jArray = getAll();

            if(jArray != null && jArray.length() > 0)
            {
                for (int c=0; c < jArray.length(); c++)
                {
                    JSONObject jsonObject = jArray.getJSONObject(c);

                    if(jsonObject != null)
                    {
                        insertDb(jsonObject);
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

    public JSONObject getByIdsDb(String id_perfil, int id_usuario) throws Exception
    {
        JSONObject jsonObject = null;

        try
        {
            JSONArray jArray = null;
            jArray = myDbAdapter.get("SELECT * FROM PERFIS_USUARIOS WHERE ID_PERFIL = '"+id_perfil+"' AND ID_USUARIO = "+ id_usuario);

            if(jArray != null && jArray.length() > 0)
            {
                jsonObject = jArray.getJSONObject(0);
            }
        }
        catch (Exception e )
        {
            throw e;
        }

        return jsonObject;
    }

    public String getIdPerfilUserLogged() throws Exception
    {
        String id_perfil = null;

        try
        {
            String query = "SELECT ID_PERFIL FROM PERFIS_USUARIO WHERE EMAIL ='"+userPreferences.get("email")+"";
            JSONArray jArray = myDbAdapter.get(query);

            if(jArray != null && jArray.length() > 0)
            {
                id_perfil = jArray.getJSONObject(0).getString("id_perfil");
            }
        }

        catch (Exception e)
        {
            throw e;
        }

        return id_perfil;
    }

    public void insertDb(JSONObject jsonObject) throws Exception
    {
        try
        {
            int isDefault = 0;

            if(getByIdsDb(jsonObject.getString("id_perfil"), jsonObject.getInt("id_usuario")) == null)
            {
                if(jsonObject.getBoolean("is_default"))
                {
                    isDefault = 1;
                }

                String command = "INSERT INTO PERFIS_USUARIOS " +
                        "( ID_USUARIO, " +
                        "ID_PERFIL, " +
                        "NOME, " +
                        "DESCRICAO," +
                        "IS_DEFAULT) " +
                        " VALUES ( " +
                        ""+jsonObject.getInt("id_usuario") + "," +
                        "'"+jsonObject.getString("id_perfil") + "'," +
                        "'"+jsonObject.getString("nome")  + "'," +
                        "'"+jsonObject.getString("descricao")  + "'," +
                        ""+isDefault +
                        ");";

                myDbAdapter.execCommand(command);
            }
        }
        catch (Exception e )
        {
            throw  e;
        }
    }

    public boolean updateDB()
    {
        return true;
    }

    public boolean deleteDB()
    {
        return true;
    }

}
