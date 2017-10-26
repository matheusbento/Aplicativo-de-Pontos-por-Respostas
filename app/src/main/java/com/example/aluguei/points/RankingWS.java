package com.example.aluguei.points;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by humbe on 08/08/2017.
 */

public class RankingWS {

    public RankingWS(){


    }
    public void register(CallBack callback) {
        callback.funcao(null, null);
    }

    public Object getRanking() throws IOException, XmlPullParserException{
        SoapObject soap = new SoapObject("http://localhost:5488/topten.wsdl", "getTopTen");
        soap.addProperty("args", null);
        CallBack callBack = new CallBackImpl();
        soap.addProperty("callback", callBack);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://172.16.105.120:5488/topten?wsdl");

        httpTrans.call("getTopTen", envelope);

        Object resultado =  envelope.getResponse();

        System.out.println(resultado.toString());

        return resultado;
    }
}
