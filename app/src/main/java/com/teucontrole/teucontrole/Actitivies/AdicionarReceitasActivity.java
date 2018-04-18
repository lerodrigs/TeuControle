package com.teucontrole.teucontrole.Actitivies;

import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.MaskUtils;

public class AdicionarReceitasActivity extends AppCompatActivity
{
    private TextView txt_conta;
    private TextView txt_categoria;
    private TextView txt_data_vencimento;
    private TextView txt_data_pagamento;
    private TextView txt_situacao;
    private TextView txt_descricao;

    private EditText txt_valor;
    private EditText txt_valor_recebido;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builderDialog;

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

            txt_conta = findViewById(R.id.txt_conta);
            txt_conta.setOnClickListener(txtContaClick);

            txt_categoria = findViewById(R.id.txt_categoria);
            txt_categoria.setOnClickListener(txtCategoriaClick);

            txt_data_vencimento = findViewById(R.id.txt_dt_vencimento);
            txt_data_vencimento.setOnClickListener(txtDataVencimentoClick);

            txt_data_pagamento = findViewById(R.id.txt_dt_pagamento);
            txt_data_pagamento.setOnClickListener(txtDataPagamentoClick);

            txt_situacao = findViewById(R.id.txt_selecione_situacao);
            txt_situacao.setOnClickListener(txtSituacaoClick);

            txt_valor = findViewById(R.id.txt_valor);
            txt_valor.addTextChangedListener(new MaskUtils(txt_valor, "##.##", true));

            txt_valor_recebido = findViewById(R.id.txt_valor_recebido);
            txt_valor_recebido.addTextChangedListener(new MaskUtils(txt_valor_recebido, "##.##", true));

            txt_descricao = findViewById(R.id.txt_descricao);
        }
        catch(Exception e ){}

    }

    public View.OnClickListener txtContaClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

    public View.OnClickListener txtCategoriaClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

    public View.OnClickListener txtDataVencimentoClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

    public View.OnClickListener txtDataPagamentoClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

    public View.OnClickListener txtSituacaoClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

        }
    };

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
