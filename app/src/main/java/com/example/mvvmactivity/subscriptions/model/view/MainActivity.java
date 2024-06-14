package com.example.mvvmactivity.subscriptions.model.view;

import static com.example.mvvmactivity.R.*;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmactivity.R;
import com.example.mvvmactivity.subscriptions.model.SubscriptionsDataModel;
import com.example.mvvmactivity.subscriptions.model.viewmodel.MyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //@SerializedName(value = "Recycler_view_id")
    RecyclerView recyclerView ;
    AdapterClass adapterClass;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(id.Recycler_view_id);
        adapterClass=new AdapterClass(new ArrayList<>(),MainActivity.this);

        myViewModel =new ViewModelProvider(this).get(MyViewModel.class);
        myViewModel.refresh();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterClass);
        observerViewModel();
    }
    private void observerViewModel() {
        myViewModel.countries.observe(this, new Observer<List<SubscriptionsDataModel>>() {
            @Override
            public void onChanged(List<SubscriptionsDataModel> countryModels) {
                if (countryModels != null) {
                    recyclerView.setVisibility(View.VISIBLE);
                    adapterClass.updateSubscription(countryModels);
                }
            }
        });
//        listViewModel.countryLoadError.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean) {
//                if (aBoolean != null) {
//                    listError.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
//                }
//            }
//        });
//        listViewModel.loading.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean isLoading) {
//                if (isLoading != null) {
//                    progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
//                    if (isLoading) {
//                        listError.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.GONE);
//                    }
//                }
//            }
//        });
    }
}