package com.teucontrole.teucontrole.Repository;

import android.content.Context;

import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class PerfilRepository
{
    private Context context;
    private UserPreferences userPreferences;
    private MyDbAdapter myDbAdapter;

    public PerfilRepository(Context _context)
    {
        this.context = _context;
        this.userPreferences = new UserPreferences(context);
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public String[] getIdPerfilUserLogged() throws  Exception
    {
        String[] ids = null;

        try
        {
            int id_usuario = 0;
            String query = "SELECT id_usuario FROM USUARIOS WHERE EMAIL = '"+userPreferences.get("email")+"'";

            JSONArray jArray = myDbAdapter.get(query);

            if(jArray != null && jArray.length() > 0)
            {
                id_usuario = jArray.getJSONObject(0).getInt("ID_USUARIO");
            }

            if(id_usuario > 0)
            {
                query = "SELECT id_perfil FROM PERFIS_USUARIOS WHERE ID_USUARIO = " +id_usuario+ ";";
                jArray = myDbAdapter.get(query);

                if(jArray != null && jArray.length() > 0)
                {
                    ids = new String[jArray.length()];

                    for(int c=0; c < jArray.length(); c++)
                    {
                        String id_perfil = jArray.getJSONObject(c).getString("ID_PERFIL");
                        ids[c] = id_perfil;
                    }
                }
            }
        }
        catch (Exception e )
        {
            throw e;
        }

        return ids;
    }

    public JSONObject getByIds(String id_perfil, int id_usuario) throws Exception
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

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            byte isDefault = 0;

            if(jObject.getBoolean("is_default"))
            {
                isDefault = 1;
            }

            String command = "INSERT INTO PERFIS_USUARIOS " +
                    "(ID_USUARIO, " +
                    "ID_PERFIL, " +
                    "NOME, " +
                    "DESCRICAO," +
                    "IS_DEFAULT) " +
                    " VALUES ( " +
                    ""+ Utils.getValueJObject(jObject, "id_usuario") + ", " +
                    "'"+Utils.getValueJObject(jObject, "id_perfil") + "', " +
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome"))  + ", " +
                    ""+Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao"))  + ", " +
                    ""+isDefault +
                    ");";

            result = myDbAdapter.execCommand(command);
        }
        catch (Exception e)
        {
            throw e;
        }
        return result;
    }

    public boolean update (JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            int isDefault = 0;

            if(jObject.getBoolean("is_default"))
            {
                isDefault = 1;
            }

            String command = "UPDATE PERFIS_USUARIOS " +
                             "SET NOME = " + Utils.checkStringForExec(Utils.getValueJObject(jObject, "nome"))+ ", " +
                             "DESCRICAO = "+ Utils.checkStringForExec(Utils.getValueJObject(jObject, "descricao")) + ", " +
                             "IS_DEFAULT = "+isDefault+" " +
                             "WHERE ID_PERFIL = '"+Utils.getValueJObject(jObject, "id_perfil")+"' AND " +
                             "ID_USUARIO =" + Utils.getValueJObject(jObject, "id_usuario");

            myDbAdapter.execCommand(command);
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }

    public boolean delete (String id_perfil, int id_user)
    {
        boolean result = false;

        try
        {
            String command = "DELETE FROM PERFIS_USUARIOS WHERE ID_PERFIL = '"+id_perfil+"' AND ID_USUARIO = "+id_user+";";
            myDbAdapter.execCommand(command);
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }



}
