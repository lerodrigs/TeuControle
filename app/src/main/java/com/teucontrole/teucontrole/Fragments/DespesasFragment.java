package com.teucontrole.teucontrole.Fragments;

import android.app.Activity;
import android.content.Context;
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
import com.teucontrole.teucontrole.Adapters.AdapterListViewReceitas;
import com.teucontrole.teucontrole.Controllers.DespesaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;

import java.util.Date;

public class DespesasFragment extends Fragment {

    private static Activity context;

    private static TextView txtSemRegistro;
    private static ProgressBar progressBar;

    private static ListView listview;
    private static AdapterListViewDespesas adapter;

    private static DespesaController despesaController;
    private static String id_perfil;

    public static DespesasFragment NewInstance()
    {
        DespesasFragment fragment = new DespesasFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        context = getActivity();
        despesaController = new DespesaController(context);

        if(getArguments() != null)
        {
            id_perfil = getArguments().getString("id_perfil");
        }

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_despesas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        listview = view.findViewById(R.id.listview_despesa);
        listview.setOnItemClickListener(clickListView);

        txtSemRegistro = view.findViewById(R.id.txt_sem_despesas);
        progressBar = view.findViewById(R.id.progressBar1);

        carregaDespesas(null, id_perfil);

        super.onViewCreated(view, bundle);
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

        }
    };

    public static void carregaDespesas(final Date data, final String _id_perfil)
    {
        Thread threadLoadDespesas = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    JSONArray jDespesas = despesaController.getList(data, _id_perfil);
                    refreshListView(jDespesas);
                }
                catch (Exception e){

                }
            }
        });

        threadLoadDespesas.start();
    }

    public static void txtSemRegistro(final JSONArray jDespesas)
    {
        try
        {
            context.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if(jDespesas != null && jDespesas.length() > 0)
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
                        adapter = new AdapterListViewDespesas(list, context);

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
