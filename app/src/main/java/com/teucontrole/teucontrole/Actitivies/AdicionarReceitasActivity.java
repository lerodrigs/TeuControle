package com.teucontrole.teucontrole.Actitivies;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teucontrole.teucontrole.Controllers.CategoriaController;
import com.teucontrole.teucontrole.Controllers.ReceitaController;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.TasksBackground.LoadCategoriaDialogTask;
import com.teucontrole.teucontrole.TasksBackground.LoadReceitaTask;
import com.teucontrole.teucontrole.TasksBackground.ProcessReceitaTask;
import com.teucontrole.teucontrole.Utils.MaskUtils;
import com.teucontrole.teucontrole.Utils.MonetaryMask;

import org.json.JSONObject;

import java.util.UUID;

public class AdicionarReceitasActivity extends AppCompatActivity
{
    private EditText txt_nome;
    private EditText txt_valor;
    private EditText txt_valor_recebido;
    private TextView txt_conta;
    private TextView txt_categoria;
    private TextView txt_data_vencimento;
    private TextView txt_data_pagamento;
    private TextView txt_situacao;
    private TextView txt_descricao;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builderDialog;
    private Toolbar toolbar;
    private Activity context;
    private ProcessReceitaTask processReceitaTask;

    private String id_perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_receitas);

        try
        {
            context = this;

            toolbar = findViewById(R.id.toolbar);
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

            txt_nome = findViewById(R.id.nome_receita);

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
            //txt_valor.addTextChangedListener(new MonetaryMask(txt_valor));

            txt_valor_recebido = findViewById(R.id.txt_valor_recebido);
            //txt_valor_recebido.addTextChangedListener(new MonetaryMask(txt_valor_recebido));

            txt_descricao = findViewById(R.id.txt_descricao);

            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

            if(bundle != null)
            {
                String id_receita = bundle.getString("id_receita");
                id_perfil = bundle.getString("id_perfil");

                View view = findViewById(R.id.rootViewReceitas);

                toolbar.setTitle("Receita");
                loadReceita(id_receita, id_perfil, view); //Carrega Receita Selecionada
            }

        }
        catch(Exception e )
        {
            Log.e("AddReceitasLoad", e.getMessage());
        }

    }

    public void loadReceita(String id_receita, String id_perfil, View view)
    {
        try
        {
            LoadReceitaTask load = new LoadReceitaTask(id_receita, id_perfil, context, view);
            load.execute();
        }
        catch (Exception e){ }
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
            View view = context.getLayoutInflater().inflate(R.layout.dialog_items, null);
            LoadCategoriaDialogTask categoriaDialogTask = new LoadCategoriaDialogTask(context, view, true, id_perfil);
            categoriaDialogTask.execute();

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
                    save();
                    break;

                case "Remover":
                    remover();
                    break;

                default:
                    finish();
                    break;
            }
        }
        catch (Exception e) {}

        return super.onOptionsItemSelected(menuItem);
    }

    private boolean save()
    {
        try
        {
            JSONObject receita = createJSONObject();
            processReceitaTask = new ProcessReceitaTask(context, receita, 1);

            UUID id_receita = UUID.randomUUID(); //GUID id
            receita.put("id_receita", receita);

            processReceitaTask.execute();
        }
        catch (Exception e){

        }

        return true;
    }

    private boolean remover()
    {
        try
        {
            JSONObject receita = createJSONObject();
            processReceitaTask = new ProcessReceitaTask(context, receita, 3);

            processReceitaTask.execute();
        }
        catch (Exception e){

        }

        return true;
    }

    private boolean update()
    {
        try
        {
            JSONObject receita = createJSONObject();
            processReceitaTask = new ProcessReceitaTask(context, receita, 3);

            processReceitaTask.execute();
        }
        catch (Exception e){

        }

        return true;
    }

    public JSONObject createJSONObject() throws Exception
    {
        JSONObject jObject = new JSONObject();

        try
        {
            jObject.put("nome", txt_nome.getText());
            jObject.put("valor", txt_valor.getText().toString().replaceAll(",", "."));
            jObject.put("valor_recebido", txt_valor.getText().toString().replaceAll(",", "."));
            jObject.put("descricao", txt_descricao.getText());
        }
        catch (Exception e){
            throw e;
        }

        return jObject;
    }

}
