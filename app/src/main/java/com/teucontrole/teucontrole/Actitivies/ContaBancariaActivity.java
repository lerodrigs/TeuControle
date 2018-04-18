package com.teucontrole.teucontrole.Actitivies;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.MaskUtils;

public class ContaBancariaActivity extends AppCompatActivity
{

    private EditText nomeContaBancaria;
    private EditText txtDescricao;
    private EditText txtSaldo;

    private TextView tipoConta;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conta_bancaria);

        try
        {
            Toolbar toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Adicionar conta");
            toolbar.setTitleTextColor(Color.WHITE);

            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.icone_back_white);

            nomeContaBancaria = findViewById(R.id.nome_conta_bancaria);
            txtDescricao = findViewById(R.id.txt_descricao);

            tipoConta = findViewById(R.id.txt_tipo_conta);
            tipoConta.setOnClickListener(tipoContaClick);

            txtSaldo = findViewById(R.id.txt_saldo);
            txtSaldo.addTextChangedListener(new MaskUtils(txtSaldo, "##.##", true));
        }
        catch (Exception e) {}
    }

    public View.OnClickListener tipoContaClick = new View.OnClickListener()
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
