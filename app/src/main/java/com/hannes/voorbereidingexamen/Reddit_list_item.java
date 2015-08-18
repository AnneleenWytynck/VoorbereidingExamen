package com.hannes.voorbereidingexamen;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.hannes.voorbereidingexamen.Interfaces.OnItemSelectListener;

public class Reddit_list_item extends ListFragment {

    private OnItemSelectListener onItemSelectListener;


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onItemSelectListener = (OnItemSelectListener) activity;
        } catch(ClassCastException e){
            throw new ClassCastException("Class must implement OnItemSelectListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        onItemSelectListener.OnItemSelect(position);
    }
}
