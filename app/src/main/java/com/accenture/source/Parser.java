package com.accenture.source;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import com.accenture.adapter.MyCustomerAdapter;
import com.accenture.bean.Estacionamiento;
import com.accenture.iotparkingcar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by raulzamora on 06/11/17.
 */

public class Parser extends AsyncTask<Void,Integer,Integer> {
    Context c;
    ListView lv;
    String data;
    ProgressDialog pd;
    ArrayList<Estacionamiento> estacionamientoList=new ArrayList<>();
    MyCustomerAdapter myCustomerAdapter;

    public Parser(Context c, String data, ListView lv) {
        this.c = c;
        this.lv = lv;
        this.data = data;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parse();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Parser");
        pd.setMessage("Matching data... Please wait!!!");
//        pd.show();
    }

    @Override
    protected void onPostExecute(Integer s) {
        super.onPostExecute(s);
        if(s==1){
            myCustomerAdapter=new MyCustomerAdapter(c, R.layout.parking_list,estacionamientoList);
            lv.setAdapter(myCustomerAdapter);
            myCustomerAdapter.notifyDataSetChanged();
            final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        myCustomerAdapter.notifyDataSetChanged();
                        handler.postDelayed( this, 5 * 1000 );
                    }
                }, 5000);

        }else{
            Toast.makeText(c,"No hay matching entre los datos",Toast.LENGTH_SHORT).show();
        }
        pd.dismiss();
    }
    private int parse(){
        try {
            JSONArray ja=new JSONArray(data);
            JSONObject jo=null;
            estacionamientoList.clear();
            for(int i=0;i<ja.length();i++){
                jo=ja.getJSONObject(i);
                String cajon=jo.getString("Cajon");
                String tiempo=jo.getString("tiempo");
                String dia=jo.getString("dia");
                String status=jo.getString("status");
                estacionamientoList.add(new Estacionamiento(cajon,dia,tiempo,status));
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
