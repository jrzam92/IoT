package com.accenture.iotparkingcar;

import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.accenture.source.Downloader;

import java.util.Timer;
import java.util.TimerTask;

public class StatusAct extends AppCompatActivity {

    String url="http://carpaking.000webhostapp.com/statusCajones.php";


    public Context c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        getSupportActionBar().setTitle("Estatus de Estacionamiento");
        final ListView lv=(ListView)findViewById(R.id.lv);
        final Downloader d =new Downloader(this,url,lv);
        d.execute();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                reload(d,lv);
                handler.postDelayed( this, 5 * 1000 );
            }
        }, 5000);
    }
    public void reload(Downloader d,ListView lv){
        d =new Downloader(this,url,lv);
        d.execute();
    }

}
