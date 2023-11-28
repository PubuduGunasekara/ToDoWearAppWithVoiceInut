package com.example.pubudupraneethgunasekaraassignment2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pubudupraneethgunasekaraassignment2.databinding.TaskRowBinding;
import com.example.pubudupraneethgunasekaraassignment2.model.Task;


import java.util.List;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    TaskRowBinding rowBinding;
    private List<Task> mTasks;
    Context context;

    public TaskRecyclerViewAdapter(List<Task> mTasks, Context context) {
        super();
        this.mTasks = mTasks;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //return null;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        rowBinding = TaskRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bindView(mTasks.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TaskRowBinding recyclerRowBinding;

        public ViewHolder(TaskRowBinding taskRowBinding) {
            super(taskRowBinding.getRoot());
            this.recyclerRowBinding = taskRowBinding;
        }

        void bindView(final Task taskDetails, final int position) {
            recyclerRowBinding.taskId.setText(taskDetails.getTaskId());
            recyclerRowBinding.taskName.setText(taskDetails.getTaskName());
            recyclerRowBinding.taskDate.setText(taskDetails.getDueDate());
            recyclerRowBinding.taskTime.setText(taskDetails.getTime());

        }


    }
}
