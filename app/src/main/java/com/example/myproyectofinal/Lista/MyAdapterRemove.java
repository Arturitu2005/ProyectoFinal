package com.example.myproyectofinal.Lista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.myproyectofinal.Json.MyCuenta;
import com.example.myproyectofinal.R;

import java.io.Serializable;
import java.util.List;

public class MyAdapterRemove extends BaseAdapter implements Serializable {
    private List<MyCuenta> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private int []imagenes = {R.drawable.editbutton,R.drawable.removebutton};

    public MyAdapterRemove(List<MyCuenta> list, Context context) {
        this.list = list;
        this.context = context;
        if( context != null)
        {
            layoutInflater = LayoutInflater.from(context);
        }
    }

    public boolean isEmptyOrNull( ) {
        return list == null || list.size() == 0;
    }

    @Override
    public int getCount() {
        if(isEmptyOrNull())
        {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        if(isEmptyOrNull())
        {
            return null;
        }
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView = null;
        view = layoutInflater.inflate(R.layout.activity_list_view_actividad_borrar, null );
        imageView = view.findViewById(R.id.imageViewLB1);
        imageView.setImageResource(list.get(i).getImage());

        return view;
    }
}