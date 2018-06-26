package com.teucontrole.teucontrole.Actitivies;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.teucontrole.teucontrole.R;

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
                    com.teucontrole.teucontrole.Controllers.UserControllers userController = new com.teucontrole.teucontrole.Controllers.UserControllers(getApplicationContext());

                    Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    //Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(loginIntent);
                    finish();
                }

            }).start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
