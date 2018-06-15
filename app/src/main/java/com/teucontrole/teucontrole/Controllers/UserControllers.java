package com.teucontrole.teucontrole.Controllers;

import android.content.Context;
import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Api.UserRequest;
import com.teucontrole.teucontrole.DataBase.MyDbAdapter;
import com.teucontrole.teucontrole.Repository.UserRepository;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserControllers
{
    private Context context;
    private UserRequest userRequest;
    private UserRepository userRepository;

    public UserControllers(Context _context)
    {
        this.context = _context;
        this.userRequest = new UserRequest(context);
        this.userRepository = new UserRepository(context);
    }

    public String getToken(String email, String pass)
    {
        return userRequest.getToken(email, pass);
    }

    public JSONObject requestUserInfo(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
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
            JSONObject jObject = requestUserInfo(email);

            if(jObject != null)
            {
                insert(jObject);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    public boolean insert(JSONObject jObject) throws Exception
    {
        boolean result = false;

        try
        {
            if(getByEmail(jObject.getString("email")) == null)
                result = userRepository.insert(jObject);
            else
                result = update(jObject);
        }
        catch (Exception e)
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
            if(getByEmail(jObject.getString("email")) != null)
            {
                result = userRepository.update(jObject);
            }
        }
        catch (Exception e )
        {
            throw e;
        }

        return result;
    }

    public JSONObject getByEmail(String email) throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = userRepository.getByEmail(email);
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

    public JSONObject getFromUserLogged() throws Exception
    {
        JSONObject jObject = null;

        try
        {
            jObject = userRepository.getFromUserLogged();
        }
        catch (Exception e)
        {
            throw e;
        }

        return jObject;
    }

}
