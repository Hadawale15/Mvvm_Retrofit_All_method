package com.example.mvvmactivity.subscriptions.model;

import com.example.mvvmactivity.ApiInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubscriptionService {

    String BASE_URL="https://medhvrushti.checkerslab.com";

    static SubscriptionService instance;

    public ApiInterface apiInterface;

    public SubscriptionService() {
        apiInterface=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
                .create(ApiInterface.class);
    }
    public static SubscriptionService getInstance()
    {
        if (instance==null)
        {
            instance=new SubscriptionService();
        }
        return instance;
    }
    public Single<List<SubscriptionsDataModel>> getSubscriptions()
    {
        return apiInterface.getSubscriptionDetails();
    }
}
