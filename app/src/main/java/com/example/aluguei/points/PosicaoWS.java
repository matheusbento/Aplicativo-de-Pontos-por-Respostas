package com.example.aluguei.points;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by humbe on 08/08/2017.
 */

public class PosicaoWS {

    public PosicaoWS(){


    }
    public String getRanking(String ra) throws IOException, XmlPullParserException{
        //SoapObject soap = new SoapObject("http://calculator.me.org/", "getRanking");
        //soap.addProperty("ra", ra);
        //SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        ////envelope.setOutputSoapObject(soap);
       // HttpTransportSE httpTrans = new HttpTransportSE("http://192.168.0.17:8080/CalculatorApp/CalculatorWSService?wsdl");

       // httpTrans.call("getRanking", envelope);;

        //Object resultado =  envelope.getResponse();
        //String resultado= (String) envelope.getResponse();
        //return (Boolean)resultado;

        //int teste= Integer.parseInt(envelope.getResponse().toString());
        //Log.e(Integer.toString(teste), "error");
        return "2";
    }
}
