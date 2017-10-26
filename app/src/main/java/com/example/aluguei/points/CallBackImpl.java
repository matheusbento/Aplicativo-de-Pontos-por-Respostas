package com.example.aluguei.points;

/**
 * Created by ProBook on 09/08/2017.
 */

public class CallBackImpl implements CallBack {
    public void funcao(Object error, Object response){
        System.out.println(response);
    }
}
