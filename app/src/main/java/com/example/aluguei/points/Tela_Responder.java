package com.example.aluguei.points;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import static com.example.aluguei.points.R.id.campo_ranking;
import static com.example.aluguei.points.R.id.ra;
import static com.example.aluguei.points.R.id.visible;

public class Tela_Responder extends AppCompatActivity implements Runnable {
    Handler handler;
    ProgressDialog dialog;
    String ra;
    TextView questao1;
    TextView aa1;
    TextView bb1;
    TextView cc1;
    TextView dd1;
    TextView ee1;
    TextView msg1;
    LinearLayout lay_result1;
    Questao result = new Questao();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela__responder);

        lay_result1 = (LinearLayout) findViewById(R.id.lay_result);
        Intent it = getIntent();
        ra = it.getStringExtra("ra");
        //dialog = new ProgressDialog(this);
        //dialog = new ProgressDialog(this);


        Button btn_proximo1 = (Button) findViewById(R.id.btn_proximo);

        btn_proximo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendQuestionWS ws = new SendQuestionWS();
                try {

                    lay_result1.setVisibility(View.VISIBLE);
                    String resposta = "";
                    RadioGroup rd_group = (RadioGroup) findViewById(R.id.alternativas);
                    switch (rd_group.getCheckedRadioButtonId()){
                        case R.id.a:
                            resposta = "a";
                            break;
                        case R.id.b:
                            resposta = "b";
                            break;
                        case R.id.c:
                            resposta = "c";
                            break;
                        case R.id.d:
                            resposta = "d";
                            break;
                        case R.id.e:
                            resposta = "e";
                            break;
                    }
                    final Boolean result2 = ws.setQuestion(ra, resposta, result);
                    if(result2==true){
                        lay_result1.setBackground(getResources().getDrawable(R.color.resposta_certa));

                        msg1.setText("Resposta Correta");
                    }else{
                        lay_result1.setBackground(getResources().getDrawable(R.color.resposta_errada));
                        msg1.setText("Resposta Errada");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        });
        handler = new Handler();

        dialog = new ProgressDialog(this);
        dialog.setMessage("Processando...");
        dialog.setTitle("Aguarde!");
        dialog.show();
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        //String id = campo_id1.getText().toString();

        try{
            ReceiveQuestionWS ws = new ReceiveQuestionWS();
            questao1 = (TextView) findViewById(R.id.questao);
            aa1 = (TextView) findViewById(R.id.aa);
            bb1 = (TextView) findViewById(R.id.bb);
            cc1 = (TextView) findViewById(R.id.cc);
            dd1 = (TextView) findViewById(R.id.dd);
            ee1 = (TextView) findViewById(R.id.ee);
            msg1 = (TextView) findViewById(R.id.msg);
            result = ws.getQuestion(ra);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    questao1.setText(result.enunciado);
                   // questao1.setText("ASDASD");
                    aa1.setText("A) "+result.a);
                    bb1.setText("B) "+result.b);
                    cc1.setText("C) "+result.c);
                    dd1.setText("D) "+result.d);
                    ee1.setText("E) "+result.e);

                }
            });

        }catch (Exception ex){
            Log.e("TelaPrincipal", "Error", ex);
        }finally {
            dialog.dismiss();
        }
    }


}
