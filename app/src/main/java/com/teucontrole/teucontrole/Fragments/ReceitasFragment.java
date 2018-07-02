package com.teucontrole.teucontrole.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.teucontrole.teucontrole.Actitivies.MainActivity;
import com.teucontrole.teucontrole.Adapters.AdapterListViewReceitas;
import com.teucontrole.teucontrole.Controllers.ReceitaController;
import com.teucontrole.teucontrole.R;

import org.json.JSONArray;

import java.util.Date;

public class ReceitasFragment extends Fragment
{
    private static ListView listview;
    private static AdapterListViewReceitas adapter;

    private static TextView txtSemRegistro;
    private static ProgressBar progressBar;

    private static ReceitaController receitaController;
    private static Activity context;
    private static String id_perfil_selecionado;

    public static ReceitasFragment NewInstance()
    {
        ReceitasFragment fragment = new ReceitasFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments() != null)
        {
            id_perfil_selecionado = getArguments().getString("id_perfil");
        }

        context = getActivity();
        receitaController = new ReceitaController(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_receitas, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle bundle)
    {
        listview = (ListView) view.findViewById(R.id.listviewReceitas);
        listview.setOnItemClickListener(clickListView);

        txtSemRegistro = view.findViewById(R.id.txt_sem_receitas);
        progressBar = view.findViewById(R.id.progressBar1);

        carregaReceitas(null, id_perfil_selecionado);

        super.onViewCreated(view, bundle);
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

        }
    };

    public static JSONArray requestReceitas(final Date date, String id_perfil)
    {
        JSONArray result = null;

        try
        {
            //result = receitaController.requestReceitas(date, date, id_perfil);
        }
        catch (Exception e){

        }

        return result;
    }

    public static void carregaReceitas(final Date data, final String _id_perfil)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    JSONArray receitas = receitaController.getList(data, _id_perfil);
                    updateListView(receitas);
                }
                catch (Exception e){

                }
            }
        }).start();
    }

    public static void txtSemRegistro(final JSONArray jReceitas)
    {
        try
        {
            context.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    if(jReceitas != null && jReceitas.length() > 0)
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

    public static void updateListView(final JSONArray list)
    {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    if(adapter == null || adapter.equals(null))
                        adapter = new AdapterListViewReceitas(list, context);

                    listview.setAdapter(adapter);
                    adapter.updateListView(list);

                    showProgressBar(false);
                    txtSemRegistro(list);
                }
                catch (Exception e)
                {
                    Log.e("ReceitasF_LN128", e.getMessage());
                }
            }
        });
    }
}
