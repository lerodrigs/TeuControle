package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Api.UserRequest;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

import org.json.JSONObject;

public class UserControllers
{
    private Context context;
    private UserPreferences userPreferences;
    private ApiRequest apiRequest;

    public UserControllers(Context _context)
    {
        this.context = _context;
        this.apiRequest = new ApiRequest();
        this.userPreferences = new UserPreferences(context);
    }

    public String getToken(String email, String pass)
    {
        try
        {
            String token = apiRequest.getToken(email, pass);

            if(token != null)
            {
                userPreferences.set("token", token);
            }

            return token;
        }
        catch (Exception e){return null;}
    }

    public JSONObject getUserInfo(String email)
    {
        try
        {
            UserRequest userRequest = new UserRequest();
            return userRequest.getInfo(email);
        }
        catch (Exception e) { return null;}
    }
}
