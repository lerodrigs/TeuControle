package com.teucontrole.teucontrole.Actitivies;

import android.app.Activity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.app.AlertDialog;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teucontrole.teucontrole.Adapters.AdapterListViewMenuPerfil;
import com.teucontrole.teucontrole.Adapters.AdapterMenuItems;
import com.teucontrole.teucontrole.Controllers.PerfilController;
import com.teucontrole.teucontrole.Controllers.UserControllers;
import com.teucontrole.teucontrole.Fragments.CheckListFragment;
import com.teucontrole.teucontrole.Fragments.ConfiguracoesFragment;
import com.teucontrole.teucontrole.Fragments.CustomDatePickerFragment;
import com.teucontrole.teucontrole.Fragments.LancamentosFragment;
import com.teucontrole.teucontrole.Models.Item;
import com.teucontrole.teucontrole.R;
import com.teucontrole.teucontrole.TasksBackground.LoadInfoUserTask;
import com.teucontrole.teucontrole.TasksBackground.LoadPerfisTask;
import com.teucontrole.teucontrole.Utils.ItemsMenuUtils;
import com.teucontrole.teucontrole.Utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private static Activity context;
    private static FloatingActionButton fabReceita;
    private static FloatingActionButton fabDespesas;
    private static FloatingActionMenu fabMenuContasCartao;
    private static FloatingActionMenu fabMenuCategorias;
    private static FloatingActionButton fabFaturas;
    private static FloatingActionMenu fabCheckList;
    private static Toolbar toolbar;

    private DatePicker datePicker;
    private ImageView imgSetaMenuPerfil;
    private RelativeLayout relListViewPerfis;
    private ListView listViewPerfis;
    private PerfilController perfilController;
    private String id_perfil_selecionado;

    private LoadPerfisTask loadPerfisTask;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        try
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            context = this;
            perfilController = new PerfilController(context);
            id_perfil_selecionado = perfilController.getIdPerfilSelecionado();

            drawerLayout = findViewById(R.id.drawer_layout);

            toolbar = findViewById(R.id.toolbar);
            toolbar.setTitle("Home");

            setSupportActionBar(toolbar);

            ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
            drawerLayout.setDrawerListener(toogle);
            toogle.syncState();

            navigationView = findViewById(R.id.nav_view);
            ListView listviewMenu = navigationView.findViewById(R.id.listview_menu);

            //Menu lateral
            List<Item> items = ItemsMenuUtils.getMenuItems();
            AdapterMenuItems adapterMenuItems = new AdapterMenuItems(items, this);

            listviewMenu.setOnItemClickListener(itemClickListener);
            listviewMenu.setAdapter(adapterMenuItems);
            listviewMenu.setItemsCanFocus(false);

            //Menu de perfis
            listViewPerfis = navigationView.findViewById(R.id.listview_perfis);
            listViewPerfis.setOnItemClickListener(perfilClickListener);
            //loadPerfis();

            loadPerfisTask = new LoadPerfisTask(this, listViewPerfis, null);
            loadPerfisTask.execute();

            RelativeLayout relPerfis = navigationView.findViewById(R.id.rel_perfis);
            TextView lblMenuPerfis = navigationView.findViewById(R.id.lbl_perfilMenu);
            relListViewPerfis = navigationView.findViewById(R.id.rel_listviewPerfis);
            imgSetaMenuPerfil = navigationView.findViewById(R.id.setaPerfil);

            relPerfis.setOnClickListener(relativeLayoutPerfisClick);
            imgSetaMenuPerfil.setOnClickListener(relativeLayoutPerfisClick);
            lblMenuPerfis.setOnClickListener(relativeLayoutPerfisClick);

            //Informações do usuário na nav.
            TextView txtNome = navigationView.findViewById(R.id.nome);
            TextView txtemail = navigationView.findViewById(R.id.email);

            LoadInfoUserTask loadInfoUserTask = new LoadInfoUserTask(context, txtNome, txtemail);
            loadInfoUserTask.execute();

            //Floating Action Menus/Buttons
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

            fabCheckList = findViewById(R.id.fab_checklists);
            FloatingActionButton fabAddCheckList = findViewById(R.id.add_checklist);
            fabAddCheckList.setOnClickListener(fabCheckListClick);

            FloatingActionButton fabAddCheckListModelo = findViewById(R.id.add_checklist_modelo);
            fabAddCheckListModelo.setOnClickListener(fabCheckListModeloClick);

            setChoosedFragment(1);
            //fim Floating Action Menus/Buttons
        }
        catch(Exception e){ }
    }

      @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu)
    {
        try
        {
            android.view.MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu_toolbar, menu);
        }
        catch (Exception e){

        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem menuItem)
    {
        try
        {
            String option = menuItem.getTitle().toString();

            if(option.equals("Calendario"))
            {
                datePicker = new DatePicker(context);
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(context);

                builder.setView(datePicker);
                builder.setPositiveButton("ok", dtClickCalendario);
                builder.show();
            }
        }
        catch (Exception e) {}

        return super.onOptionsItemSelected(menuItem);
    }

    private AdapterView.OnItemClickListener perfilClickListener = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            try
            {
                AdapterListViewMenuPerfil adapter = loadPerfisTask.getAdapter();
                JSONObject jObject = adapter.getItem((int) position);

                if(jObject != null)
                {
                    id_perfil_selecionado = Utils.getValueJObject(jObject, "id_perfil");
                    setChoosedFragment(1);
                }
            }
            catch(Exception e){

            }
        }
    };

    private DialogInterface.OnClickListener dtClickCalendario = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {

            if(datePicker != null)
            {
                String day = "";
                String month = "";
                String data = "";

                if(datePicker.getDayOfMonth() <= 9)
                    day = "0" + datePicker.getDayOfMonth();
                else
                    day = String.valueOf(datePicker.getDayOfMonth());

                if(datePicker.getMonth() <= 9)
                    month = "0"+ datePicker.getMonth();
                else
                    month = String.valueOf(datePicker.getMonth());

                data = day + "/" + month + "/" + datePicker.getYear();

                /**/
            }
        }
    };

    public static void setToolbar(final String textToolbar)
    {
        context.runOnUiThread(new Runnable()
        {
            @Override
            public void run() {
                toolbar.setTitle(textToolbar);
            }
        });
    }

    //Exibe-esconde lista de perfis
    byte ctrlSeta =0;
    private View.OnClickListener relativeLayoutPerfisClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            if(ctrlSeta == 0)
            {
                imgSetaMenuPerfil.setImageResource(R.mipmap.seta_cima);
                relListViewPerfis.setVisibility(View.VISIBLE);
                ctrlSeta =1;
            }
            else{
                imgSetaMenuPerfil.setImageResource(R.mipmap.seta_baixo);
                relListViewPerfis.setVisibility(View.GONE);
                ctrlSeta =0;
            }
        }
    };

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
            resetFragmentManager();

            switch(id)
            {
                case 1:
                    chooseFloatingActionButton(0);
                    fragment = LancamentosFragment.NewInstance();
                    break;
                case 2:
                    chooseFloatingActionButton(6);
                    fragment = ConfiguracoesFragment.NewInstance();
                    break;

                case 3:
                    chooseFloatingActionButton(5);
                    fragment = CheckListFragment.newInstance();
                    break;
            }

            Bundle argments = new Bundle();
            argments.putString("id_perfil", id_perfil_selecionado);

            fragment.setArguments(argments);

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

    public void resetFragmentManager()
    {
        try
        {
            if(getSupportFragmentManager().getFragments() != null)
            {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                Fragment fragment = null;

                int qtdFragments = getSupportFragmentManager().getFragments().size();

                for(int c=0; c < qtdFragments; c++)
                {
                    fragment = getSupportFragmentManager().getFragments().get(c);

                    if(fragment != null)
                    {
                        fragmentTransaction.remove(fragment);
                    }
                }

                fragmentTransaction.commit();
            }
        }
        catch (Exception e){}
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
                    fabCheckList.hideMenuButton(true);

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
                            //fabFaturas.show(true);
                            break;

                        case 5:
                            fabCheckList.showMenuButton(true);
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

            Bundle bundle = new Bundle();
            bundle.putString("id_perfil", id_perfil_selecionado);

            adicionarReceitasIntent.putExtras(bundle);
            startActivity(adicionarReceitasIntent);
        }
    };

    private FloatingActionButton.OnClickListener fabDespesasClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            Intent despesas = new Intent(context, AdicionarDespesaActivity.class);

            Bundle bundle = new Bundle();
            bundle.putString("id_perfil", id_perfil_selecionado);

            despesas.putExtras(bundle);
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

    private DialogInterface.OnClickListener btnAdicionarCategoriaListener = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {

        }
    };

    private FloatingActionButton.OnClickListener fabCheckListClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = context.getLayoutInflater().inflate(R.layout.adicionar_checklist_modelo, null);

            TextView lbl_adicionar = view.findViewById(R.id.lbl_adicionar);
            lbl_adicionar.setText("Adicionar Checklist");

            builder.setView(view);
            builder.setPositiveButton(R.string.Adicionar, btnAddCheckList);
            builder.setNegativeButton(R.string.Cancelar, null);

            builder.create();
            builder.show();
        }
    };

    private FloatingActionButton.OnClickListener fabCheckListModeloClick = new FloatingActionButton.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View view = context.getLayoutInflater().inflate(R.layout.adicionar_checklist_modelo, null);

            TextView lbl_adicionar = view.findViewById(R.id.lbl_adicionar);
            lbl_adicionar.setText("Adicionar Modelo");

            builder.setView(view);
            builder.setPositiveButton(R.string.Adicionar, btnAddModelo);
            builder.setNegativeButton(R.string.Cancelar, null);

            builder.create();
            builder.show();
        }
    };

    private DialogInterface.OnClickListener btnAddCheckList = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            try
            {

            }
            catch (Exception e){}
        }
    };

    private DialogInterface.OnClickListener btnAddModelo = new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            try
            {
            }
            catch (Exception e){}
        }
    };
}
