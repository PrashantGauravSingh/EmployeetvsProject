package com.andtechno.singh.psheet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFacade {
    private static final int TIME_OUT = 60;
    DemoApiServices api;

    public ApiFacade() {
        setupRetrofit(getGson(false), getOkHttpClient());
    }

    public ApiFacade(boolean serializeNulls) {
        setupRetrofit(getGson(serializeNulls), getOkHttpClient());
    }

    private void setupRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.checkgaadi.com/reporting/vrm/api/test_new/int/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
        api = retrofit.create(DemoApiServices.class);
    }

    private OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .build();
    }

    private Gson getGson(boolean serializeNulls) {
        GsonBuilder builder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        if (serializeNulls)
            builder.serializeNulls();
        return builder.create();
    }

    public DemoApiServices getApiService() {
        return api;
    }

}