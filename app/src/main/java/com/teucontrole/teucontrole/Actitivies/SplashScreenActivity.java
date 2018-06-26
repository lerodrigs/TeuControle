package com.teucontrole.teucontrole.Actitivies;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.teucontrole.teucontrole.Controllers.UserControllers;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

public class SplashScreenActivity extends AppCompatActivity {


    public static Activity context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;
        userLogged();
    }

    public void userLogged()
    {
        try
        {
            new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        UserControllers userController = new UserControllers(getApplicationContext());
                        UserPreferences userPreferences = new UserPreferences(getApplicationContext());
                        Intent intent = null;

                        if(userController.getFromUserLogged() != null && userPreferences.get("isLogged").equals("S"))
                            intent = new Intent(context, MainActivity.class);
                        else
                            intent = new Intent(context, LoginActivity.class);

                        startActivity(intent);
                        finish();

                    }
                    catch (Exception e) {

                    }
                }

            }).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
