package com.example.aluguei.points;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by humbe on 08/08/2017.
 */

public class ReceiveQuestionWS {

    public ReceiveQuestionWS(){


    }
    public Questao getQuestion(String ra) throws IOException, XmlPullParserException{
        SoapObject soap = new SoapObject("http://services/", "getQuestao");
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://172.16.105.140:8080/WebService/PerguntasService?WSDL");

        httpTrans.call("getQuestao", envelope);;

        SoapObject resultado = (SoapObject)  envelope.getResponse();
        Questao question = new Questao();
        question.enunciado = resultado.getProperty("enunciado").toString();
        question.id = resultado.getProperty("id").toString();
        question.correta = resultado.getProperty("correta").toString();
        question.a = resultado.getProperty("a").toString();
        question.b = resultado.getProperty("b").toString();
        question.c = resultado.getProperty("c").toString();
        question.d = resultado.getProperty("d").toString();
        question.e = resultado.getProperty("e").toString();
        //String resultado2= (String) envelope.getResponse();
        return question;
    }
}
