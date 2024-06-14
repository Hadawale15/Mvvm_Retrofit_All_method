package com.example.mvvmactivity.subscriptions.model.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmactivity.R;
import com.example.mvvmactivity.subscriptions.model.SubscriptionsDataModel;

import java.util.List;

public class AdapterClass  extends RecyclerView.Adapter<AdapterClass.MyViewHolder> {

    List<SubscriptionsDataModel> list;
    Context context;


    public AdapterClass(List<SubscriptionsDataModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void updateSubscription(List<SubscriptionsDataModel> newList)
    {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.sl_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {


        holder.textView.setText(list.get(position).getSubscription_name());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_id);
        }
    }
}
