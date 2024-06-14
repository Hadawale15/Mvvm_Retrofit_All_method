package com.example.mvvmactivity.subscriptions.model.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmactivity.subscriptions.model.SubscriptionService;
import com.example.mvvmactivity.subscriptions.model.SubscriptionsDataModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class MyViewModel extends ViewModel {

    public MutableLiveData<List<SubscriptionsDataModel>> countries=new MutableLiveData<List<SubscriptionsDataModel>>();
    public MutableLiveData<Boolean> countryLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    public void refresh()
    {
        fetchSubscriptionData();
    }


    public SubscriptionService subscriptionService=SubscriptionService.getInstance();
    private CompositeDisposable disposable=new CompositeDisposable();
    private void fetchSubscriptionData() {
        countryLoadError.setValue(false);
        loading.setValue(true);
       // CountryServices.getInstance();

        disposable.add(subscriptionService.getSubscriptions()
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<SubscriptionsDataModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<SubscriptionsDataModel> countryModels) {
                        countries.setValue(countryModels);
                        countryLoadError.setValue(false);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }


}
