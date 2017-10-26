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

public class SendQuestionWS {

    public SendQuestionWS(){


    }
    public Boolean setQuestion(String ra, String resposta, Questao questao) throws IOException, XmlPullParserException{
        SoapObject soap = new SoapObject("http://services/", "setQuestao");
        soap.addProperty("ra", ra);
        soap.addProperty("resposta", resposta);
        soap.addProperty("objeto", questao);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soap);
        HttpTransportSE httpTrans = new HttpTransportSE("http://172.16.105.140:8080/WebService/PerguntasService?WSDL");

        httpTrans.call("setQuestao", envelope);;

        Object resultado =  envelope.getResponse();
        return (Boolean) resultado;
    }
}
