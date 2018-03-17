package com.teucontrole.teucontrole.Actitivies;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

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
    ListView listView;
    int id_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
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

        setChoosedFragment(1);
        id_fragment = 1;
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
                    fragment = LancamentosFragment.NewInstance();
                    break;
                case 2:
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
}
