package com.example.mvvmactivity.PostData.model;

import com.example.mvvmactivity.ApiInterface;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicesClass {

    private static String BASE_URL="https://medhvrushti.checkerslab.com";
   public static ApiInterface apiInterface;

    static ServicesClass servicesInstance;

    public ServicesClass()
    {
        apiInterface= new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    public static ServicesClass getServiceInstance()
    {
        if (servicesInstance==null)
        {
            servicesInstance=new ServicesClass();
        }
        return servicesInstance;

    }

    public Single<SendOtpModel> sendOtpService(String number)
    {
        return apiInterface.sendOtp(number);
    }



}
