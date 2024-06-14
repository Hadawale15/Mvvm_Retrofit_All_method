package com.example.mvvmactivity.subject.view;

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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvvmactivity.ApiInterface;
import com.example.mvvmactivity.R;
import com.example.mvvmactivity.subject.model.SubjectsModel;
import com.example.mvvmactivity.subject.viewmodel.SubjectsViewModel;

import java.util.ArrayList;
import java.util.List;

public class AllSubjectActivity extends AppCompatActivity {

    RecyclerView allSubjectRecyclerView;
    SubjectsModel subjectsModel;
    SubjectAdapter adapter;
    SubjectsViewModel subjectsViewModel;
    private SwipeRefreshLayout swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_subject);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        allSubjectRecyclerView=findViewById(R.id.All_subject_recycler_view_id);
        swipeRefreshLayout=findViewById(R.id.swipeRefreshLayout_id);

        adapter=new SubjectAdapter(new ArrayList<>(),getApplicationContext());

        subjectsViewModel=new ViewModelProvider(this).get(SubjectsViewModel.class);
        subjectsViewModel.refresh();

        allSubjectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        allSubjectRecyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(() -> {
            subjectsViewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        observViewModel();


    }

    private void observViewModel() {
        subjectsViewModel.subjects.observe(this, new Observer<List<SubjectsModel>>() {
            @Override
            public void onChanged(List<SubjectsModel> subjectsModels) {
                if (subjectsModels != null)
                {
                    allSubjectRecyclerView.setVisibility(View.VISIBLE);
                    adapter.updateSubject(subjectsModels);
                }
            }
        });
    }
}