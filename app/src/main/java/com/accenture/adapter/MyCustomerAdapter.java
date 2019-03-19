package com.accenture.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.accenture.bean.Estacionamiento;
import com.accenture.iotparkingcar.R;

import java.util.ArrayList;

/**
 * Created by raulzamora on 06/11/17.
 */

public class MyCustomerAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Estacionamiento> est;

    public MyCustomerAdapter(Context context, int textViewResourceId, ArrayList<Estacionamiento> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
        this.est = objects;
    }
    private class ViewHolder{
        TextView estCajon;
        TextView estTiempo;
        TextView estDia;
        ImageView estStatus;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.parking_list, null);
            holder = new ViewHolder();
            holder.estCajon = (TextView) convertView.findViewById(R.id.textview1);
            holder.estDia = (TextView) convertView.findViewById(R.id.textview2);
            holder.estTiempo = (TextView) convertView.findViewById(R.id.textview3);
            holder.estStatus = (ImageView) convertView.findViewById(R.id.imageView1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        Estacionamiento estacionamiento = est.get(position);
        if (estacionamiento.getStatus().equals("disponible")) {
            holder.estCajon.setText("Cajon: " + estacionamiento.getCajon());
            holder.estTiempo.setText("Tiempo: " + estacionamiento.getTiempo());
            holder.estDia.setText("Dia: " + estacionamiento.getDia());
            holder.estStatus.setImageResource(R.drawable.disponible);
        } else if (estacionamiento.getStatus().equals("ocupado")) {
            holder.estCajon.setText("Cajon: " + estacionamiento.getCajon());
            holder.estTiempo.setText("Tiempo: " + estacionamiento.getTiempo());
            holder.estDia.setText("Dia: " + estacionamiento.getDia());
            holder.estStatus.setImageResource(R.drawable.ocupado);
        }
        return convertView;
    }

}
