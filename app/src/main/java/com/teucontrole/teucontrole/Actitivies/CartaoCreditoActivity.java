package com.teucontrole.teucontrole.Actitivies;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.teucontrole.teucontrole.R;

public class CartaoCreditoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartao_credito);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Adicionar Cartão");
        toolbar.setTitleTextColor(Color.WHITE);

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.mipmap.icone_back_white);
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
            switch (option)
            {
                case "Salvar":
                    break;

                case "Remover":
                    break;

                default:
                    finish();
                    break;
            }
        }
        catch (Exception e) {}

        return super.onOptionsItemSelected(menuItem);
    }
}
