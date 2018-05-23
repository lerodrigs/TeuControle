package com.teucontrole.teucontrole.Actitivies;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teucontrole.teucontrole.Api.ApiRequest;
import com.teucontrole.teucontrole.Controllers.UserControllers;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.SharedPreferences.UserPreferences;

public class LoginActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passEditText;
    Dialog dialog;
    LinearLayout rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        rootView = (LinearLayout) findViewById(R.id.login_view);

        emailEditText = (EditText) findViewById(R.id.txt_email);
        passEditText = (EditText) findViewById(R.id.txt_senha);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(btnLoginOnClick);

        View view = getLayoutInflater().inflate(R.layout.loader, null);

        dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }

    private View.OnClickListener btnLoginOnClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            try
            {
                dialog.show();

                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        String email = emailEditText.getText().toString();
                        String pass = passEditText.getText().toString();

                        if(email.isEmpty() || pass.isEmpty())
                        {
                            runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    dialog.dismiss();

                                    Snackbar.make(rootView, "Informe seu login corretamente!", Snackbar.LENGTH_LONG)
                                            .setAction("OK", snackBarBtn )
                                            .show();
                                }
                            });

                            return;
                        }

                        UserControllers userControllers = new UserControllers(getApplicationContext());
                        String token = userControllers.getToken(email, pass);

                        if(token == null)
                        {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    dialog.dismiss();

                                    Snackbar.make(rootView, "Email ou senha incorreta!", Snackbar.LENGTH_LONG)
                                            .setAction("OK", snackBarBtn )
                                            .show();
                                }
                            });

                            return;
                        }

                        Intent intentMain = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intentMain);
                        finish();

                    }
                }).start();
            }
            catch (Exception e )
            {
                e.printStackTrace();
            }
        }
    };

   private View.OnClickListener snackBarBtn = new View.OnClickListener()
   {
       @Override
       public void onClick(View v)
       {

       }
   };

}
