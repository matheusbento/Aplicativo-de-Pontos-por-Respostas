package com.example.aluguei.points;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * Created by humbe on 08/08/2017.
 */

public class LoginWS {

    public LoginWS(){


    }
    public Boolean efetuarLogin(String ra, String password) throws IOException, XmlPullParserException{
        //SoapObject soap = new SoapObject("http://calculator.me.org/", "efetuarLogin");
        //soap.addProperty("ra", ra);
        //soap.addProperty("senha", password);
        //SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        ////envelope.setOutputSoapObject(soap);
       // HttpTransportSE httpTrans = new HttpTransportSE("http://192.168.0.17:8080/CalculatorApp/CalculatorWSService?wsdl");

       // httpTrans.call("efetuarLogin", envelope);;

        //Object resultado =  envelope.getResponse();
        //String resultado= (String) envelope.getResponse();
        //return (Boolean)resultado;

        //int teste= Integer.parseInt(envelope.getResponse().toString());
        //Log.e(Integer.toString(teste), "error");
        //return "semcu";
        return true;
    }
}
