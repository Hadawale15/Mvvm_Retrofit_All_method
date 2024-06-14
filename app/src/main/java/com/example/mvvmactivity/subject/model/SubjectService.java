package com.example.mvvmactivity.subject.model;

import com.example.mvvmactivity.ApiInterface;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubjectService {


    static  SubjectService serviceInstance;
    public static final String BASE_URL="https://medhvrushti.checkerslab.com";

     ApiInterface instance;

    public SubjectService() {
        instance=new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build().create(ApiInterface.class);

    }
    public static SubjectService getServiceInstance()
    {
        if (serviceInstance==null)
        {
            serviceInstance=new SubjectService();
        }
        return serviceInstance;
    }

    public Single<List<SubjectsModel>> getSubjectData()
    {
        return instance.getSubjectData();
    }
}
