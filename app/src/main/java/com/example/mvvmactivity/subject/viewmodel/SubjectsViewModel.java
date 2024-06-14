package com.example.mvvmactivity.subject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmactivity.subject.model.SubjectService;
import com.example.mvvmactivity.subject.model.SubjectsModel;
import com.example.mvvmactivity.subscriptions.model.SubscriptionService;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.internal.schedulers.NewThreadScheduler;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SubjectsViewModel extends ViewModel {

    public MutableLiveData<List<SubjectsModel>> subjects=new MutableLiveData<List<SubjectsModel>>();
    public MutableLiveData<Boolean>  loading=new MutableLiveData<Boolean>();

    public SubjectService subjectService=SubjectService.getServiceInstance();

    public CompositeDisposable disposable=new CompositeDisposable();
    public void refresh()
    {
        fetchSubjectData();
    }

    private void fetchSubjectData() {
        loading.setValue(true);
        disposable.add(subjectService.getSubjectData()
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<SubjectsModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<SubjectsModel> subjectsModels) {
                         subjects.setValue(subjectsModels);
                         loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("Error ehile fetching subject data",e.getMessage());
                    }
                })
        );


    }


}
