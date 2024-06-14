package com.example.mvvmactivity;

import com.example.mvvmactivity.PostData.model.SendOtpModel;
import com.example.mvvmactivity.subject.model.SubjectsModel;
import com.example.mvvmactivity.subscriptions.model.SubscriptionsDataModel;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/cil/main_subscriptions/get/all")
    Single<List<SubscriptionsDataModel>> getSubscriptionDetails();

    @GET("/api/v1/cil/subject/get/all")
    Single<List<SubjectsModel>> getSubjectData();

    @POST("/api/v1/cil/user-auth/otp/send")
    Single<SendOtpModel> sendOtp(@Query("mobile_number") String mobileNumber);




}
