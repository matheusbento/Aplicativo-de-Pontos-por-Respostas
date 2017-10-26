package com.example.aluguei.points;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by humbe on 08/08/2017.
 */

public class ProgressWS {

    public ProgressWS(){


    }
    public String getProgress(String ra) throws IOException, XmlPullParserException{
        SoapObject soap = new SoapObject("http://calculator.me.org/", "getProgress");
        soap.addProperty("ra", ra);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://172.16.105.239:8080/Progress/ProgressWSService?wsdl");

        httpTrans.call("getProgress", envelope);;

        Object resultado =  envelope.getResponse();
        //String resultado= (String) envelope.getResponse();
        return resultado.toString();
        //int teste= Integer.parseInt(envelope.getResponse().toString());
        //Log.e(Integer.toString(teste), "error");
        //return "semcu";
    }
}
