package com.example.pubudupraneethgunasekaraassignment2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pubudupraneethgunasekaraassignment2.adapter.TaskRecyclerViewAdapter;
import com.example.pubudupraneethgunasekaraassignment2.databinding.ActivityNotificationDetailsBinding;
import com.example.pubudupraneethgunasekaraassignment2.model.Task;
import com.example.pubudupraneethgunasekaraassignment2.utils.TaskUtils;

import java.util.ArrayList;

public class NotificationDetailsActivity extends AppCompatActivity {

    ActivityNotificationDetailsBinding detailsBinding;
    private TaskRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_notificationdetails);
        detailsBinding = ActivityNotificationDetailsBinding.inflate(getLayoutInflater());
        View view = detailsBinding.getRoot();

        setContentView(view);
        init();
    }

    private void init() {

        setLayout();
        Intent notificationIntent = getIntent();
        String taskId = notificationIntent.getStringExtra("TaskId");
        String taskName = notificationIntent.getStringExtra("TaskName");

        detailsBinding.taskId.setText(taskId);
        detailsBinding.taskName.setText(taskName);
    }

    private void setLayout() {
        detailsBinding.wrcViewTasks.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        detailsBinding.wrcViewTasks.setLayoutManager(mLayoutManager);
    }

    private void updateTaskAdapter() {
        TaskUtils.getAllTasks(this);
        if (TaskUtils.getAllTasks(this) != null) {
            bindAdapter(TaskUtils.getAllTasks(this));
        }
    }


    private void bindAdapter(ArrayList<Task> taskArray) {
        mAdapter = new TaskRecyclerViewAdapter(taskArray, getApplicationContext());
        detailsBinding.wrcViewTasks.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();
        updateTaskAdapter();
    }

}