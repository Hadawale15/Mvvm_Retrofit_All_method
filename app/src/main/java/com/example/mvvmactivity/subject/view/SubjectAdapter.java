package com.example.mvvmactivity.subject.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmactivity.R;
import com.example.mvvmactivity.subject.model.SubjectsModel;
import com.example.mvvmactivity.subscriptions.model.SubscriptionsDataModel;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    List<SubjectsModel> subjectsModelList;
    Context context;

    public SubjectAdapter(List<SubjectsModel> subjectsModelList, Context context) {
        this.subjectsModelList = subjectsModelList;
        this.context = context;
    }
    public void updateSubject(List<SubjectsModel> newList)
    {
        subjectsModelList.clear();
        subjectsModelList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sl_layout,parent,false);

        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
holder.Bind(subjectsModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return subjectsModelList.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView subjectName;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName=itemView.findViewById(R.id.text_id);
        }

        void Bind(SubjectsModel subjects)
        {

            subjectName.setText(subjects.getSubject_name());
        }
    }



}
