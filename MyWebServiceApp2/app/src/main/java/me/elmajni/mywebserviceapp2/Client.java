package me.elmajni.mywebserviceapp2;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static final String ROOT_URL = "http://192.168.0.102:8090/";
    private static Retrofit retrofit = null;

    public static  ApiInterface getRetrofitClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(ROOT_URL).addConverterFactory(
                    GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
