package com.teucontrole.teucontrole.Controllers;

import android.content.Context;

import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

public class UserControllers
{
    private Context context;
    private UserPreferences userPreferences;

    public UserControllers(Context _context)
    {
        this.context = _context;
        this.userPreferences = new UserPreferences(context);
    }

    public String login(String email, String pass)
    {
        try
        {
            ApiRequest apiRequest = new ApiRequest();
            String token = apiRequest.getToken(email, pass);

            if(token != null)
            {
                userPreferences.set("token", token);
            }

            return token;
        }
        catch (Exception e){return null;}
    }
}
