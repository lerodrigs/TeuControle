package com.teucontrole.teucontrole.Actitivies;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.teucontrole.teucontrole.R;

public class AdicionarDespesaActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_despesa);

        try
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setBackgroundColor(getResources().getColor(R.color.theme_red_primaryDark));
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setTitle("Adicionar Despesa");

            setSupportActionBar(toolbar);
            setTheme(R.style.MyTheme_Red);

            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icone_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme_red_primaryDark));
            }
        }
        catch (Exception e){}
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        try
        {
            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_toolbar_criacao, menu);
        }
        catch (Exception e){}

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        try
        {
            String option = menuItem.getTitle().toString();

            switch(option)
            {
                case "Remover":
                    break;

                case "Salvar":
                    break;

                default:
                    finish();
                    break;
            }
        }
        catch (Exception e){}

        return super.onOptionsItemSelected(menuItem);
    }


}
