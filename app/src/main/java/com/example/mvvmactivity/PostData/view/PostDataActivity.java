package com.example.mvvmactivity.PostData.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmactivity.PostData.viewmodel.postServiceViewModel;
import com.example.mvvmactivity.R;
import com.example.mvvmactivity.subject.model.SubjectsModel;
import com.example.mvvmactivity.subject.viewmodel.SubjectsViewModel;

import java.util.List;

public class PostDataActivity extends AppCompatActivity {


    Button post;

    postServiceViewModel serviceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_post_data);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        post=findViewById(R.id.PostData_button_id);
        serviceViewModel=new ViewModelProvider(this).get(postServiceViewModel.class);


        post.setOnClickListener(view ->{
            observViewModel();


        });
    }

    private void observViewModel() {

        serviceViewModel.addData("919307257088");
//
//       // serviceViewModel.addData("mobile_number","919307257088");
//        serviceViewModel.number.observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(String s) {
//                Log.d("adas","success2");
//
//            }
//        });
    }
}