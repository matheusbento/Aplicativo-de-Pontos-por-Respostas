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

public class WrongWS {

    public WrongWS(){


    }
    public String getWrong(String ra) throws IOException, XmlPullParserException{
        /*SoapObject soap = new SoapObject("http://ws/", "getErros");
        soap.addProperty("ra", ra);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://172.16.105.74:8080/ServiceWS/WSErrosAcertos?wsdl");

        httpTrans.call("getErros", envelope);;

        Object resultado =  envelope.getResponse();
        //String resultado2= (String) envelope.getResponse();
        return resultado.toString();*/
        return "0";
    }
}
