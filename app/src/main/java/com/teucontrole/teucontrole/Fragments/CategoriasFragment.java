package com.teucontrole.teucontrole.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.teucontrole.teucontrole.Controllers.CategoriaController;
import com.teucontrole.teucontrole.Controllers.PerfilController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoriasFragment extends Fragment
{

    private ListView listViewReceitas;
    private ListView listViewDespesas;
    private TextView txtSemRegistroReceita;
    private TextView txtSemRegistroDespesa;
    private RelativeLayout relCategoriaReceita;
    private RelativeLayout relCategoriaDespesa;
    private ImageView imgIconReceita;
    private ImageView imgIconDespesa;
    private FrameLayout frameLayoutReceita;
    private FrameLayout frameLayoutDespesa;

    private CategoriaController categoriaReceitaController;
    private CategoriaController categoriaDespesaController;
    private String id_perfil;

    public static CategoriasFragment NewInstance()
    {
        CategoriasFragment fragment = new CategoriasFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.categoriaReceitaController = new CategoriaController(getContext(), true);
        this.categoriaDespesaController = new CategoriaController(getContext(), false);

        if (getArguments() != null) {
            id_perfil = getArguments().getString("id_perfil");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_categorias, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstancedState)
    {
        listViewReceitas = view.findViewById(R.id.categorias_receitas);
        listViewReceitas.setOnItemClickListener(clickCategoriaReceita);
        listViewReceitas.setItemsCanFocus(false);

        listViewDespesas = view.findViewById(R.id.categorias_despesas);
        listViewReceitas.setOnItemClickListener(clickCategoriaDespesa);
        listViewDespesas.setItemsCanFocus(false);

        txtSemRegistroReceita = view.findViewById(R.id.txt_sem_registro_cat_receitas);
        txtSemRegistroDespesa = view.findViewById(R.id.txt_sem_registro_cat_despesas);

        relCategoriaReceita = view.findViewById(R.id.rel_clickable_categoria_receita);
        relCategoriaReceita.setOnClickListener(relClickReceita);

        relCategoriaDespesa = view.findViewById(R.id.rel_clickable_categoria_despesa);
        relCategoriaReceita.setOnClickListener(relClickDespesa);

        imgIconReceita = view.findViewById(R.id.add_blue_categoria_receita);
        imgIconDespesa = view.findViewById(R.id.add_blue_categoria_despesa);

        frameLayoutReceita = view.findViewById(R.id.frameLayoutListViewCatReceitas);
        frameLayoutDespesa = view.findViewById(R.id.frameLayoutListViewCatDespesas);

        loadListView();

        super.onViewCreated(view, savedInstancedState);
    }

    private View.OnClickListener relClickReceita = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            if(frameLayoutReceita.getVisibility() == View.VISIBLE)
                frameLayoutReceita.setVisibility(View.GONE);
            else
                frameLayoutReceita.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener relClickDespesa = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(frameLayoutDespesa.getVisibility() == View.VISIBLE)
                frameLayoutDespesa.setVisibility(View.GONE);
            else
                frameLayoutDespesa.setVisibility(View.VISIBLE);
        }
    };

    private AdapterView.OnItemClickListener clickCategoriaReceita = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    private AdapterView.OnItemClickListener clickCategoriaDespesa = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        }
    };

    private void loadListView()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    JSONArray jArrayCatReceitas = categoriaReceitaController.getCategorias(id_perfil);
                    String[] categoriasReceitas;

                    if(jArrayCatReceitas!= null && jArrayCatReceitas.length() > 0)
                    {
                        categoriasReceitas = new String[jArrayCatReceitas.length()];
                        for(int i=0; i < jArrayCatReceitas.length(); i++)
                        {
                            JSONObject jObject = jArrayCatReceitas.getJSONObject(i);
                            if(jObject != null)
                                categoriasReceitas[i] = jObject.getString("nome");
                        }
                    }
                    else
                        categoriasReceitas = new String[0];

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categoriasReceitas);
                    listViewReceitas.setAdapter(arrayAdapter);
                }
                catch (Exception e)
                {
                    Log.e("catReceitas", e.getMessage());
                }

                try{
                    JSONArray jArrayCatDespesas = categoriaDespesaController.getCategorias(id_perfil);
                    String[] categoriasDespesas;

                    if(jArrayCatDespesas!= null && jArrayCatDespesas.length() > 0)
                    {
                        categoriasDespesas = new String[jArrayCatDespesas.length()];
                        for(int i=0; i < jArrayCatDespesas.length(); i++)
                        {
                            JSONObject jObject = jArrayCatDespesas.getJSONObject(i);
                            if(jObject != null)
                                categoriasDespesas[i] = jObject.getString("nome");

                        }
                    }
                    else
                        categoriasDespesas = new String[0];

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categoriasDespesas);
                    listViewDespesas.setAdapter(arrayAdapter);
                }
                catch (Exception e)
                {
                    Log.e("catDespesas", e.getMessage());
                }
            }
        });

        thread.start();
    }
}
