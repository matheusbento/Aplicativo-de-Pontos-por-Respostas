package com.example.aluguei.points;

import android.app.ProgressDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.aluguei.points.R.id.campo_ranking;

public class Tela_Ranking extends AppCompatActivity implements Runnable{
    Handler handler;
    TextView text;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__ranking);
        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

         text = (TextView) findViewById(R.id.texto);
        dialog= new ProgressDialog(this);
        //dialog = new ProgressDialog(this);
        dialog.setMessage("Processando...");
        dialog.setTitle("Aguarde!");
        dialog.show();
        Thread t = new Thread(this);
        t.start();
        //text.setText(resultado.toString());
        //ArrayList array = (ArrayList) resultado;
        //for(int i=0;i<array.size();i++){
        //    TextView tv=new TextView(this);
        //    // or get your TextView from array
        //    tv.setText(" "+array.get(i).nome);
         //   ll.addView(tv);
        //}
        //setContentView(ll);
    }

    @Override
    public void run() {
        try{
            RankingWS ws = new RankingWS();
            final Object resultado = ws.getRanking();


            handler.post(new Runnable() {
                @Override
                public void run() {
                    ArrayList array = (ArrayList) resultado;
                    //for(int i=0;i<array.size();i++){
                    //    TextView tv=new TextView(this);
                    //    // or get your TextView from array
                    //    tv.setText(" "+array.get(i).nome);
                    //   ll.addView(tv);
                    //}
                    //setContentView(ll);
                    text.setText(array.size());
                }
            });

        }catch (Exception ex){
            Log.e("TelaPrincipal", "Error", ex);
        }finally {
            dialog.dismiss();
        }
    }
}
