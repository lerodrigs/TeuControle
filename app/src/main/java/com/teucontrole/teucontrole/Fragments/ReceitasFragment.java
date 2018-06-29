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
    private static ReceitaController receitaController;
    private static Activity context;

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

        carregaReceitas(null);

        super.onViewCreated(view, bundle);
    }

    private AdapterView.OnItemClickListener clickListView = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {

        }
    };

    public static void carregaReceitas(final Date data)
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    JSONArray receitas = receitaController.getList(data);
                    updateListView(receitas);
                }
                catch (Exception e){

                }
            }
        }).start();
    }

    public static void updateListView(final JSONArray list)
    {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    if(list != null && list.length() > 0)
                    {
                        if(adapter == null)
                        {
                            adapter = new AdapterListViewReceitas(list, context);
                            listview.setAdapter(adapter);
                        }

                        adapter = (AdapterListViewReceitas) listview.getAdapter();
                        adapter.updateListView(list);
                    }
                }
                catch (Exception e) {
                }
            }
        });
    }

    public static JSONArray requestReceitas(final Date date)
    {
        JSONArray result = null;

        try
        {
            //result = receitaController.requestReceitas(date, date);
        }
        catch (Exception e){

        }

        return result;
    }
}
