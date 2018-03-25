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
import android.widget.EditText;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.MaskUtils;

public class AdicionarReceitasActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_receitas);

        try
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setBackgroundColor(getResources().getColor(R.color.theme_green_primary));

            toolbar.setTitle("Adicionar Receita");

            setTheme(R.style.MyTheme_Green);
            setSupportActionBar(toolbar);

            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.icone_back_white);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.setStatusBarColor(ContextCompat.getColor(this, R.color.theme_green_primaryDark));
            }

            TextView txt_conta = findViewById(R.id.txt_conta);
            TextView txt_categoria = findViewById(R.id.txt_categoria);
            TextView txt_data_vencimento = findViewById(R.id.txt_dt_vencimento);
            TextView txt_data_pagamento = findViewById(R.id.txt_dt_pagamento);
            TextView txt_situacao = findViewById(R.id.txt_selecione_situacao);

            EditText txt_valor = findViewById(R.id.txt_valor);
            txt_valor.addTextChangedListener(new MaskUtils(txt_valor, "##.##", true));

            EditText txt_valor_recebido = findViewById(R.id.txt_valor_recebido);
            txt_valor_recebido.addTextChangedListener(new MaskUtils(txt_valor_recebido, "##.##", true));

            TextView txt_descricao = findViewById(R.id.txt_descricao);
        }
        catch(Exception e ){}

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
