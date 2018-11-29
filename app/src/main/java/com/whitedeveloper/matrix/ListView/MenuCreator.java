package com.whitedeveloper.matrix.ListView;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.whitedeveloper.matrix.R;

public class MenuCreator implements SwipeMenuCreator
{

    private final Context context;

    public MenuCreator(Context context) {
        this.context = context;
    }

    @Override
    public void create(SwipeMenu menu)
    {
        menu.addMenuItem(getItemRemoving());
        menu.addMenuItem(getItemShowing());
    }

    private SwipeMenuItem getItemShowing()
    {
        SwipeMenuItem swipeMenuItem = new SwipeMenuItem(context);
        swipeMenuItem.setBackground(new ColorDrawable(ContextCompat.getColor(context,R.color.green)));
        swipeMenuItem.setWidth(200);
        swipeMenuItem.setTitle("Show");
        swipeMenuItem.setTitleSize(12);
        swipeMenuItem.setTitleColor(Color.WHITE);
        return swipeMenuItem;
    }

    private SwipeMenuItem getItemRemoving()
    {
        SwipeMenuItem swipeMenuItem = new SwipeMenuItem(context);
                swipeMenuItem.setBackground(new ColorDrawable(ContextCompat.getColor(context,R.color.red_item)));
                swipeMenuItem.setWidth(200);
                swipeMenuItem.setTitle("Remove");
                swipeMenuItem.setTitleSize(12);
                swipeMenuItem.setTitleColor(Color.WHITE);
        return swipeMenuItem;
    }
}