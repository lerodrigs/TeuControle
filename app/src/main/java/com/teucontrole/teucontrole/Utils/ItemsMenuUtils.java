package com.teucontrole.teucontrole.Utils;
import com.teucontrole.teucontrole.Models.Item;
import com.teucontrole.teucontrole.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsMenuUtils
{
    public static List<Item> getMenuItems()
    {
        try
        {
            List<Item> items = new ArrayList<>();

            Item item = new Item();

            item.setDescricao("Lançamentos");
            item.setId(1);
            item.setImage(R.mipmap.home);

            items.add(item);

            item  = new Item();

            item.setDescricao("Configurações");
            item.setId(2);
            item.setImage(R.mipmap.settings);

            items.add(item);

            item = new Item();

            item.setDescricao("CheckLists");
            item.setId(3);
            item.setImage(R.mipmap.ic_checklist);

            items.add(item);

            return items;
        }
        catch (Exception e )
        {
            return null;
        }
    }
}
