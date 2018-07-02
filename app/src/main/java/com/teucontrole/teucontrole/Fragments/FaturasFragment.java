package com.teucontrole.teucontrole.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.teucontrole.teucontrole.Adapters.AdapterListViewDespesas;
import com.teucontrole.teucontrole.Adapters.AdapterListViewFaturas;
import com.teucontrole.teucontrole.Controllers.FaturaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;

import java.util.Date;

public class FaturasFragment extends Fragment
{
    private static ListView listview;
    private static AdapterListViewFaturas adapter;

    private static Activity context;

    private static ProgressBar progressBar;
    private static TextView txtSemRegistro;

    private static FaturaController faturaController;
    private static String id_perfil_selecionado;

    public static FaturasFragment NewInstance()
    {
        FaturasFragment fragment = new FaturasFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        this.context = getActivity();
        this.faturaController = new FaturaController(context);

        if(getArguments() != null)
        {
            id_perfil_selecionado = getArguments().getString("id_perfil");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_faturas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        listview = view.findViewById(R.id.listview_fatura);
        listview.setOnItemClickListener(clickListView);

        txtSemRegistro = view.findViewById(R.id.txt_sem_faturas);
        progressBar = view.findViewById(R.id.progressBar1);

        carregaFaturas(null, id_perfil_selecionado);

        super.onViewCreated(view, bundle);
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

        }
    };

    public static void carregaFaturas(final Date data, final String id_perfil)
    {
        Thread threadLoadDespesas = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    JSONArray jFaturas = faturaController.getList(data, id_perfil);
                    refreshListView(jFaturas);
                }
                catch (Exception e){

                }
            }
        });

        threadLoadDespesas.start();
    }

    public static void txtSemRegistro(final JSONArray jFaturas)
    {
        try
        {
            context.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if(jFaturas != null && jFaturas.length() > 0)
                        txtSemRegistro.setVisibility(View.GONE);
                    else
                        txtSemRegistro.setVisibility(View.VISIBLE);
                }
            });
        }
        catch (Exception e){ }
    }

    public static void showProgressBar(final boolean visible)
    {
        try
        {
            context.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if(visible)
                        progressBar.setVisibility(View.VISIBLE);
                    else
                        progressBar.setVisibility(View.GONE);
                }
            });
        }
        catch (Exception e){ }
    }

    public static void refreshListView(final JSONArray list)
    {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    if(adapter == null)
                        adapter = new AdapterListViewFaturas(list, context);

                    listview.setAdapter(adapter);
                    adapter.updateListView(list);

                    showProgressBar(false);
                    txtSemRegistro(list);
                }
                catch (Exception e) {
                }
            }
        });
    }

}
