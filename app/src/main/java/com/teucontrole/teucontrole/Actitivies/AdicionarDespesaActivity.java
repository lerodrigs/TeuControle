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
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.MaskUtils;

public class AdicionarDespesaActivity extends AppCompatActivity
{

    private EditText txtNomeDespesa;
    private EditText txtValor;
    private EditText txtValorRecebido;
    private EditText txtDescricao;

    private TextView txtConta;
    private TextView txtCategoria;
    private TextView txtDataVencimento;
    private TextView txtDataPagamento;
    private TextView txtSituacao;

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

            txtNomeDespesa = findViewById(R.id.txt_nome_receita);

            txtValor = findViewById(R.id.txt_valor);
            txtValor.addTextChangedListener(new MaskUtils(txtValor, "##.##", true));

            txtValorRecebido = findViewById(R.id.txt_valor_recebido);
            txtValorRecebido.addTextChangedListener(new MaskUtils(txtValorRecebido, "##.##", true));

            txtConta = findViewById(R.id.txt_conta);
            txtConta.setOnClickListener(txtContaClick);

            txtCategoria = findViewById(R.id.txt_categoria);
            txtCategoria.setOnClickListener(txtCategoriaClick);

            txtDataVencimento = findViewById(R.id.txt_dt_vencimento);
            txtDataVencimento.setOnClickListener(txtDataVencimentoClick);

            txtDataPagamento = findViewById(R.id.txt_dt_pagamento);
            txtDataPagamento.setOnClickListener(txtDataPagamentoClick);

            txtSituacao = findViewById(R.id.txt_situacao);
            txtSituacao.setOnClickListener(txtSituacaoClick);

        }
        catch (Exception e){}
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
