package com.teucontrole.teucontrole.Actitivies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.teucontrole.teucontrole.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

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
                    //Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    Intent loginIntent = new Intent(getApplicationContext(), MainActivity.class);
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
