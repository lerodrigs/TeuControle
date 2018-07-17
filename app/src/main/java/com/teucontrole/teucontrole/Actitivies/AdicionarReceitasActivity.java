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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teucontrole.teucontrole.Controllers.CategoriaController;
import com.teucontrole.teucontrole.Controllers.ReceitaController;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.TasksBackground.LoadCategoriaDialogTask;
import com.teucontrole.teucontrole.TasksBackground.LoadContaDialogTask;
import com.teucontrole.teucontrole.TasksBackground.LoadReceitaTask;
import com.teucontrole.teucontrole.TasksBackground.LoadSituacaoDialogTask;
import com.teucontrole.teucontrole.TasksBackground.ProcessReceitaTask;
import com.teucontrole.teucontrole.Utils.MaskUtils;
import com.teucontrole.teucontrole.Utils.MonetaryMask;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONObject;

import java.util.UUID;

public class AdicionarReceitasActivity extends AppCompatActivity
{
    private EditText txt_nome;
    private EditText txt_valor;
    private EditText txt_valor_recebido;
    private TextView txt_id_conta;
    private TextView txt_conta;
    private TextView txt_id_categoria;
    private TextView txt_categoria;
    private TextView txt_data_vencimento;
    private TextView txt_data_pagamento;
    private TextView txt_id_situacao;
    private TextView txt_situacao;
    private TextView txt_descricao;

    private AlertDialog alertDialog;
    private AlertDialog.Builder builderDialog;
    private LinearLayout rootView;

    private Toolbar toolbar;
    private Activity context;

    private ProcessReceitaTask processReceitaTask;
    private LoadCategoriaDialogTask loadCategoriaDialogTask;
    private LoadSituacaoDialogTask loadSituacaoDialogTask;
    private LoadContaDialogTask loadContaDialogTask;

    private String id_perfil;
    private String id_receita;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_receitas);

        try
        {
            context = this;
            rootView = findViewById(R.id.rootViewReceitas);

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

            txt_id_conta = findViewById(R.id.id_conta);
            txt_conta = findViewById(R.id.txt_conta);
            txt_conta.setOnClickListener(txtContaClick);

            txt_id_categoria = findViewById(R.id.id_categoria);
            txt_categoria = findViewById(R.id.txt_categoria);
            txt_categoria.setOnClickListener(txtCategoriaClick);

            txt_data_vencimento = findViewById(R.id.txt_dt_vencimento);
            txt_data_vencimento.setOnClickListener(txtDataVencimentoClick);

            txt_data_pagamento = findViewById(R.id.txt_dt_pagamento);
            txt_data_pagamento.setOnClickListener(txtDataPagamentoClick);

            txt_id_situacao = findViewById(R.id.id_situacao);
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
                id_perfil = bundle.getString("id_perfil");
                id_receita = bundle.getString("id_receita");

                if(id_receita != null){
                    loadReceita(id_receita, id_perfil, findViewById(R.id.rootViewReceitas));
                    toolbar.setTitle("Receita");
                }
            }

            this.loadCategoriaDialogTask = new LoadCategoriaDialogTask(context, txt_categoria, true, id_perfil);
            this.loadSituacaoDialogTask = new LoadSituacaoDialogTask(txt_situacao, context);
            this.loadContaDialogTask = new LoadContaDialogTask(context, txt_conta, id_perfil);

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
            loadContaDialogTask.execute();
        }
    };

    public View.OnClickListener txtCategoriaClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            loadCategoriaDialogTask.execute();
        }
    };

    public View.OnClickListener txtSituacaoClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            loadSituacaoDialogTask.execute();
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
            JSONObject receita = createJSONObject();

            int process = 1;

            if(option.equals("Salvar"))
            {
                if(this.id_receita == null)
                    process = 1;
                else
                    process = 2;
            }
            else if(option.equals("Remover")){
                process = 3;
            }
            else {
                process = 0;
            }

            if(process ==0){
                finish();
                return true;
            }

            processReceitaTask = new ProcessReceitaTask(context, receita, process, rootView);
            processReceitaTask.execute();
        }
        catch (Exception e) {}

        return super.onOptionsItemSelected(menuItem);
    }

    public JSONObject createJSONObject() throws Exception
    {
        JSONObject jObject = new JSONObject();

        try
        {
            JSONObject categoria = loadCategoriaDialogTask.getItemSelected();
            JSONObject situacao = loadSituacaoDialogTask.getSituacaoSelecionada();
            JSONObject conta = loadContaDialogTask.getItemSelected();

            String nome = txt_nome.getText().toString();
            String valor = txt_valor.getText().toString();
            String valor_recebido = txt_valor_recebido.getText().toString();
            String data_vencimento = txt_data_vencimento.getText().toString();
            String data_pagamento = txt_data_pagamento.getText().toString();
            String descricao = txt_descricao.getText().toString();

            String _idSituacao = txt_id_situacao.getText().toString();
            String _situacao = txt_situacao.getText().toString();

            String _idCategoria = txt_id_categoria.getText().toString();
            String _categoriaNome = txt_categoria.getText().toString();

            String _idConta = txt_id_conta.getText().toString();
            String _conta = txt_conta.getText().toString();

            if(!_idCategoria.equals("") && !_idCategoria.equals(null)){
                jObject.put("id_categoria_receita", _idCategoria);
                jObject.put("categoria_receita_nome", _categoriaNome);
            }
            else if(categoria != null) {
                jObject.put("id_categoria_receita", categoria.getString("id_categoria_receita"));
                jObject.put("categoria_receita_nome", categoria.getString("nome"));
            }
            else {
                jObject.put("id_categoria_receita", JSONObject.NULL);
                jObject.put("categoria_receita_nome", JSONObject.NULL);
            }

            if(!_idConta.equals("") && !_idConta.equals(null)){
                jObject.put("id_conta", _idConta);
                jObject.put("conta_nome", _conta);
            }
            else if(conta != null) {
                jObject.put("id_conta", conta.getString("id_conta"));
                jObject.put("conta_nome", conta.getString("nome"));
            }
            else {
                jObject.put("id_conta", JSONObject.NULL);
                jObject.put("conta_nome", JSONObject.NULL);
            }

            if(!_situacao.equals("") && !_situacao.equals(null))
            {
                switch (_situacao)
                {
                    case "Aguardando":
                        jObject.put("id_titulo_status", 1);
                        break;

                    case "Pago":
                        jObject.put("id_titulo_status", 2);
                        break;

                    case "Cancelado":
                        jObject.put("id_titulo_status", 3);
                        break;
                }
            }
            else if(situacao != null)
                jObject.put("id_titulo_status", situacao.getString("id_titulo_status"));
            else
                jObject.put("id_titulo_status", 1);


            if(nome.equals(""))
                nome = null;

            jObject.put("nome", nome == null ? JSONObject.NULL : nome);

            if(valor.equals(""))
                valor = null;

            jObject.put("valor", valor == null ? JSONObject.NULL : valor);

            if(valor_recebido.equals(""))
                valor_recebido = null;

            jObject.put("valor_recebido", valor_recebido == null ? JSONObject.NULL : valor_recebido);

            if(data_vencimento.equals(""))
                data_vencimento = null;

            jObject.put("data_vencimento", data_vencimento == null ? JSONObject.NULL : Utils.genericFormatDate(data_vencimento, "yyyy-mm-dd"));

            if(data_pagamento.equals(""))
                data_pagamento = null;

            jObject.put("data_pagamento", data_pagamento == null ? JSONObject.NULL : Utils.genericFormatDate(data_pagamento, "yyyy-mm-dd"));

            if(descricao.equals(""))
                descricao = null;

            jObject.put("descricao", descricao == null ? JSONObject.NULL : descricao);
            jObject.put("id_perfil", this.id_perfil);

            jObject.put("id_receita", this.id_receita == null ? UUID.randomUUID() : this.id_receita);

        }
        catch (Exception e){
            throw e;
        }

        return jObject;
    }

}
