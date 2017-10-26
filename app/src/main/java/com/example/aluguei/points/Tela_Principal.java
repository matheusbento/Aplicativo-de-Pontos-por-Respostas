package com.example.aluguei.points;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Tela_Principal extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , Runnable, GridView.OnClickListener{
    TextView resultado ;
    Handler handler;
    ProgressDialog dialog;
    String ra;
    ProgressBar mProgressBar;
    TextView id_text;
    TextView menuid;
    TextView progresso;
    TextView campo_hit;
    TextView campo_wrong;
    TextView campo_ranking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__principal);
        resultado = (TextView) findViewById(R.id.text_resultado);
        id_text = (TextView) findViewById(R.id.text_id);
        progresso = (TextView) findViewById(R.id.text_porcentagem);
        menuid = (TextView) findViewById(R.id.menu_id);
        campo_hit = (TextView) findViewById(R.id.campo_certas);
        campo_wrong = (TextView) findViewById(R.id.campo_erradas);
        campo_ranking = (TextView) findViewById(R.id.campo_ranking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Intent it = getIntent();

        ra = it.getStringExtra("ra");
        handler = new Handler();

        dialog = new ProgressDialog(this);
        //dialog = new ProgressDialog(this);
        dialog.setMessage("Processando...");
        dialog.setTitle("Aguarde!");
        dialog.show();
        Thread t = new Thread(this);
        t.start();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela__principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            // Handle the camera action
        } else if (id == R.id.nav_responder) {
            Intent responder = new Intent(this, Tela_Responder.class );
            responder.putExtra("ra", ra);
            startActivity(responder);
        }else if (id == R.id.nav_ranking10) {
            Intent responder = new Intent(this, Tela_Ranking.class );
            responder.putExtra("ra", ra);
            startActivity(responder);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void run() {
        //String id = campo_id1.getText().toString();

        try{
            ProgressWS ws = new ProgressWS();
            final String resultado2 = ws.getProgress(ra);
            mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
            progresso.setText(resultado2+"%");
            id_text.setText("Olá, "+ra);

            HitWS wshit = new HitWS();
            final String result_hit = wshit.getHit(ra);

            WrongWS wswrong = new WrongWS();
            final String result_wrong = wswrong.getWrong(ra);

            PosicaoWS wsranking = new PosicaoWS();
            final String result_ranking = wsranking.getRanking(ra);

            handler.post(new Runnable() {
                @Override
                public void run() {

                    mProgressBar.setProgress(Integer.parseInt(resultado2));
                    campo_hit.setText(result_hit);
                    campo_wrong.setText(result_wrong);
                    campo_ranking.setText(result_ranking);
                }
            });

        }catch (Exception ex){
            Log.e("TelaPrincipal", "Error", ex);
        }finally {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {

    }
}
