package com.teucontrole.teucontrole.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teucontrole.teucontrole.Actitivies.MainActivity;
import com.teucontrole.teucontrole.Adapters.AdapterViewPage;
import com.teucontrole.teucontrole.R;

public class LancamentosFragment extends Fragment
{
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private String id_perfil_selecionado;

    public static LancamentosFragment NewInstance()
    {
        LancamentosFragment fragment = new LancamentosFragment();
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        if(getArguments() != null)
        {
            id_perfil_selecionado = getArguments().getString("id_perfil");
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {
        try
        {
            return layoutInflater.inflate(R.layout.fragment_lancamentos, viewGroup, false);
        }
        catch (Exception e )
        {
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        try
        {
            MainActivity.setToolbar("Home");

            tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
            viewPager = (ViewPager) view.findViewById(R.id.pager);

            setupViewPager(viewPager, getFragmentManager());

            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setOnTabSelectedListener(tabSelectedListener);
        }
        catch (Exception e ) { }
    }

    private void setupViewPager(ViewPager viewPager, FragmentManager fragmentManager)
    {
        AdapterViewPage adapter = new AdapterViewPage(fragmentManager);

        Bundle argments = new Bundle();
        argments.putString("id_perfil", id_perfil_selecionado);

        android.support.v4.app.Fragment receitasFragment =ReceitasFragment.NewInstance();
        receitasFragment.setArguments(argments);

        android.support.v4.app.Fragment despesasFragment =ReceitasFragment.NewInstance();
        despesasFragment.setArguments(argments);

        android.support.v4.app.Fragment contasCartoesFragment = ContasCartoesFragment.NewInstance();
        contasCartoesFragment.setArguments(argments);

        android.support.v4.app.Fragment categoriasFragment = CategoriasFragment.NewInstance();
        categoriasFragment.setArguments(argments);

        android.support.v4.app.Fragment faturasFragment = FaturasFragment.NewInstance();
        faturasFragment.setArguments(argments);

        adapter.addFragment(receitasFragment, "Receitas");
        adapter.addFragment(despesasFragment, "Despesas");
        adapter.addFragment(contasCartoesFragment, "Contas e Cartões");
        adapter.addFragment(categoriasFragment, "Categorias");
        adapter.addFragment(faturasFragment, "Faturas");

        viewPager.setAdapter(adapter);
    }

    private TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener()
    {
        @Override
        public void onTabSelected(TabLayout.Tab tab)
        {
            int position = tab.getPosition();

            MainActivity.chooseFloatingActionButton(position);
            viewPager.setCurrentItem(position, true);
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

}