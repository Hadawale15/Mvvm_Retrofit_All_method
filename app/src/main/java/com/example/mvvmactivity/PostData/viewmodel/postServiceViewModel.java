package com.example.mvvmactivity.PostData.viewmodel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmactivity.ApiInterface;
import com.example.mvvmactivity.PostData.model.SendOtpModel;
import com.example.mvvmactivity.PostData.model.ServicesClass;
import com.example.mvvmactivity.subject.model.SubjectsModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class postServiceViewModel extends ViewModel {

    public MutableLiveData<String> number=new MutableLiveData<String>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    public ServicesClass servicesClass=ServicesClass.getServiceInstance();

    public CompositeDisposable compositeDisposable=new CompositeDisposable();

    public void addData(String number) {
        loading.setValue(false);
        compositeDisposable.add(servicesClass.sendOtpService(number)
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<SendOtpModel>() {
                    @Override
                    public void onSuccess(@NonNull SendOtpModel sendOtpModel) {
                        loading.setValue(false);
                        Log.d("NewResponse",sendOtpModel.getMessage());
                    }

                    @Override
                    public void onError(@NonNull Throwable c) {
                        Log.d("NewResponseError",c.getMessage());
                    }
                }));
    }

}
