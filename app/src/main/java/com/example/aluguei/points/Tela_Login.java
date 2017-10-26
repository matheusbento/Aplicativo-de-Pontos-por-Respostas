package com.example.aluguei.points;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Tela_Login extends AppCompatActivity implements Runnable, GridView.OnClickListener{

    // UI references.
    Button btn_enviar1;
    EditText ra;
    EditText senha;
    Handler handler;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__login);
        // Set up the login form.
        btn_enviar1 = (Button) findViewById(R.id.btn_logar);
        ra = (EditText) findViewById(R.id.ra);
        senha = (EditText) findViewById(R.id.senha);
        handler = new Handler();
        ra.setText("a120069");
        btn_enviar1.setOnClickListener(this);

    }
    @Override
    public void run() {
        String ra1 = ra.getText().toString();
        String senha1 = senha.getText().toString();

        try{
            LoginWS ws = new LoginWS();
            final Boolean resultado2 = ws.efetuarLogin(ra1, senha1);

            final Intent activity_pagina_principal =new Intent(this, Tela_Principal.class);
            activity_pagina_principal.putExtra("ra", ra1);


            handler.post(new Runnable() {
                @Override
                public void run() {
                    if(resultado2==true) {
                        startActivity(activity_pagina_principal);
                    }
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
        dialog = new ProgressDialog(this);
        dialog.setMessage("Processando...");
        dialog.setTitle("Aguarde!");
        dialog.show();
        Thread t = new Thread(this);
        t.start();
    }
}

