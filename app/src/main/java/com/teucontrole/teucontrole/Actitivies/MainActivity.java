package com.teucontrole.teucontrole.Actitivies;

import android.app.Activity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.teucontrole.teucontrole.Adapters.AdapterMenuItems;
import com.teucontrole.teucontrole.Fragments.ConfiguracoesFragment;
import com.teucontrole.teucontrole.Fragments.LancamentosFragment;
import com.teucontrole.teucontrole.Models.Item;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.Utils.ItemsMenuUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    static Activity context;
    static FloatingActionButton fabReceita;
    static FloatingActionButton fabDespesas;
    static FloatingActionMenu fabMenuContasCartao;
    static FloatingActionMenu fabMenuCategorias;
    static FloatingActionButton fabFaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.setDrawerListener(toogle);
        toogle.syncState();

        navigationView = findViewById(R.id.nav_view);
        ListView listviewMenu = navigationView.findViewById(R.id.listview_menu);

        List<Item> items = ItemsMenuUtils.getMenuItems();
        AdapterMenuItems adapterMenuItems = new AdapterMenuItems(items, this);

        listviewMenu.setOnItemClickListener(itemClickListener);
        listviewMenu.setAdapter(adapterMenuItems);
        listviewMenu.setItemsCanFocus(false);

        fabReceita = findViewById(R.id.fab_receitas);
        fabReceita.setOnClickListener(fabReceitasClick);

        fabDespesas = findViewById(R.id.fab_despesas);
        fabDespesas.setOnClickListener(fabDespesasClick);

        fabMenuContasCartao = findViewById(R.id.fab_contas_cartao);
        fabMenuCategorias = findViewById(R.id.fab_categorias);

        FloatingActionButton fabCategoriaReceita = findViewById(R.id.fab_cat_receita);
        fabCategoriaReceita.setOnClickListener(fabCategoriaReceitaClick);

        FloatingActionButton fabCategoriaDespesa = findViewById(R.id.fab_cat_despesa);
        fabCategoriaDespesa.setOnClickListener(fabCategoriaDespesaClick);

        FloatingActionButton fabAddContaBancaria = findViewById(R.id.adicionar_conta_bancaria);
        fabAddContaBancaria.setOnClickListener(fabAddContaBancariaClick);

        FloatingActionButton fabAddCartaoCredito = findViewById(R.id.adicionar_cartao_de_credito);
        fabAddCartaoCredito.setOnClickListener(fabAddCartaoCreditoClick);

        fabFaturas = findViewById(R.id.fab_faturas);
        fabFaturas.setOnClickListener(fabFaturasClick);

        setChoosedFragment(1);
    }

    private ListView.OnItemClickListener itemClickListener = new ListView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            setChoosedFragment((int) id);
        }
    };

    public void setChoosedFragment(int id)
    {
        try
        {
            android.support.v4.app.Fragment fragment = null;

            switch(id)
            {
                case 1:
                    chooseFloatingActionButton(0);
                    fragment = LancamentosFragment.NewInstance();
                    break;
                case 2:
                    chooseFloatingActionButton(5);
                    fragment = ConfiguracoesFragment.NewInstance();
                    break;
            }

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.contentMain, fragment)
                    .commit();

            drawerLayout.closeDrawer(GravityCompat.START, true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void chooseFloatingActionButton(final int id)
    {
        try
        {
            context.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    fabReceita.hide(true);
                    fabDespesas.hide(true);
                    fabMenuContasCartao.hideMenuButton(true);
                    fabMenuCategorias.hideMenuButton(true);
                    fabFaturas.hide(true);

                    switch (id)
                    {
                        case 0:
                            fabReceita.show(true);
                            break;

                        case 1:
                            fabDespesas.show(true);
                            break;

                        case 2:
                            fabMenuContasCartao.showMenuButton(true);
                            break;

                        case 3:
                            fabMenuCategorias.showMenuButton(true);
                            break;

                        case 4:
                            fabFaturas.show(true);
                            break;
                    }
                }
            });

        }
        catch (Exception e ) { }
    }

    private FloatingActionButton.OnClickListener fabReceitasClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent adicionarReceitasIntent= new Intent(context, AdicionarReceitasActivity.class);
            startActivity(adicionarReceitasIntent);
        }
    };

    private FloatingActionButton.OnClickListener fabDespesasClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent despesas = new Intent(context, AdicionarDespesaActivity.class);
            startActivity(despesas);
        }
    };

    private FloatingActionButton.OnClickListener fabCategoriaReceitaClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = context.getLayoutInflater().inflate(R.layout.adicionar_categorias, null);

            TextView lbl_categoria = view.findViewById(R.id.lbl_categoria);
            lbl_categoria.setText(lbl_categoria.getText() + " (Receitas)");

            builder.setView(view);
            builder.setPositiveButton(R.string.Adicionar, btnAdicionarCategoriaListener);
            builder.setNegativeButton(R.string.Cancelar, null);

            builder.create();
            builder.show();
        }
    };

    private FloatingActionButton.OnClickListener fabCategoriaDespesaClick= new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = context.getLayoutInflater().inflate(R.layout.adicionar_categorias, null);

            TextView lbl_categoria = view.findViewById(R.id.lbl_categoria);
            lbl_categoria.setText(lbl_categoria.getText() + " (Despesas)");

            builder.setView(view);
            builder.setPositiveButton(R.string.Adicionar, btnAdicionarCategoriaListener);
            builder.setNegativeButton(R.string.Cancelar, null);

            builder.create();
            builder.show();
        }
    };

    private FloatingActionButton.OnClickListener fabAddContaBancariaClick= new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent contaBancaria = new Intent(context, ContaBancariaActivity.class);
            startActivity(contaBancaria);
        }
    };

    private FloatingActionButton.OnClickListener fabAddCartaoCreditoClick= new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent cartaoDeCredito = new Intent(context, CartaoCreditoActivity.class);
            startActivity(cartaoDeCredito);
        }
    };

    private FloatingActionButton.OnClickListener fabFaturasClick= new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent faturas = new Intent(context, AdicionarFaturasActivity.class);
            startActivity(faturas);
        }
    };

    public void addNewCategoria(final boolean isReceita)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                if(isReceita)
                {

                }
                else
                {

                }
            }
        }).start();
    }

    private DialogInterface.OnClickListener btnAdicionarCategoriaListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            addNewCategoria(true);
        }
    };
}
