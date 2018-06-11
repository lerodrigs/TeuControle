package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Api.UserRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.internal.Util;

public class UserControllers
{
    private Context context;
    private UserPreferences userPreferences;
    private UserRequest userRequest;
    private MyDbAdapter myDbAdapter;

    public UserControllers(Context _context)
    {
        this.context = _context;
        this.userRequest = new UserRequest();
        this.userPreferences = new UserPreferences(context);
        this.myDbAdapter = new MyDbAdapter(context);
    }

    public String getToken(String email, String pass)
    {
        ApiRequest apiRequest = new ApiRequest();
        String token = apiRequest.getToken(email, pass);

        if(token != null)
        {
            userPreferences.set("token", token);
        }

        return token;
    }

    public JSONObject getUserInfo(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            UserRequest userRequest = new UserRequest();
            jObject = userRequest.getInfo(email);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public void start(String email) throws Exception
    {
        try
        {
            JSONObject jObject = getUserInfo(email);

            if(jObject != null)
            {
                insertDb(jObject);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public void insertDb(JSONObject jObject) throws Exception
    {
        try
        {
            if(getByEmailDb(jObject.getString("email")) == null)
            {
                String data_nascimento = Utils.getDateFromJObject(jObject, "data_nascimento");
                String validade_assinatura = Utils.getDateFromJObject(jObject, "validade_assinatura");

                String command = "INSERT INTO USUARIOS (" +
                        "ID_USUARIO, "  +
                        "NOME, "  +
                        "SEXO, " +
                        "ID_ESTADO, " +
                        "ID_CIDADE, " +
                        "DATA_NASCIMENTO, " +
                        "EMAIL, " +
                        "VALIDADE_ASSINATURA)" +
                        " VALUES (" +
                        ""+Utils.getValueJObject(jObject, "id_usuario") + "," +
                        "'"+Utils.getValueJObject(jObject, "nome") + "', " +
                        ""+Utils.getValueJObject(jObject, "sexo") + ", "+
                        ""+Utils.getValueJObject(jObject, "id_estado") + ", "+
                        ""+Utils.getValueJObject(jObject,"id_cidade")+ ", "+
                        ""+data_nascimento+ ", "+
                        "'"+Utils.getValueJObject(jObject, "email")+ "', "+
                        "'"+validade_assinatura+ "'); ";

                myDbAdapter.execCommand(command);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public JSONObject getByEmailDb(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {

            JSONArray jArray = new JSONArray();

            String query = "SELECT * FROM USUARIOS WHERE EMAIL = '"+email+"'";
            jArray = myDbAdapter.get(query);

            if((jArray.length()) > 0)
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

    public JSONObject getFromUserLogged()
    {
        JSONObject jObject = null;

        try
        {
            JSONArray jArray = new JSONArray();
            String email = userPreferences.get("email");

            jArray = myDbAdapter.get("SELECT EMAIL, NOME FROM USUARIOS WHERE EMAIL = '"+email+"'");

            if(jArray.length() > 0)
            {
                jObject = jArray.getJSONObject(0);
            }

        }
        catch (Exception e)
        {

        }

        return jObject;
    }

}
