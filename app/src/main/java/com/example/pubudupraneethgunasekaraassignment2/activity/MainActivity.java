package com.example.pubudupraneethgunasekaraassignment2.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;


import com.example.pubudupraneethgunasekaraassignment2.R;
import com.example.pubudupraneethgunasekaraassignment2.adapter.TaskRecyclerViewAdapter;
import com.example.pubudupraneethgunasekaraassignment2.databinding.ActivityMainBinding;
import com.example.pubudupraneethgunasekaraassignment2.model.Task;
import com.example.pubudupraneethgunasekaraassignment2.utils.TaskUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;
    private static final String CHANNEL_ID = "CHANNEL_01";
    private List<Task> myTask = new ArrayList<>();
    private TaskRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        init();
    }

    private void init() {
        mainBinding.addTask.setOnClickListener(this);
        mainBinding.trigNoti.setOnClickListener(this);
        createNotificationChannel();


        setLayout();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {


            String channelName = getString(R.string.channel_name);
            String channelDescription = getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mainBinding.addTask.getId()) {
            Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
            startActivity(intent);

        }
        if (v.getId() == mainBinding.trigNoti.getId()) {

            Intent intent = new Intent(this, NotificationDetailsActivity.class);
            intent.putExtra("TaskId", "John Tosland, John Leiska");
            intent.putExtra("TaskName", "3621");
            //intent.putExtra("AGENDA", agendaText);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_IMMUTABLE);


            String notificationTitle = getString(R.string.channel_name);
            String notificationText = getString(R.string.channel_name);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
            builder.setContentTitle(notificationTitle);
            builder.setContentText(notificationText);
            builder.setSmallIcon(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_light);

            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);


            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            managerCompat.notify(1, builder.build());

        }
    }

    private void setLayout() {
        mainBinding.wrcViewTasks.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mainBinding.wrcViewTasks.setLayoutManager(mLayoutManager);
    }

    private void updateTaskAdapter() {
        TaskUtils.getAllTasks(this);
        if (TaskUtils.getAllTasks(this) != null) {
            bindAdapter(TaskUtils.getAllTasks(this));
        }
    }


    private void bindAdapter(ArrayList<Task> taskArray) {
        mAdapter = new TaskRecyclerViewAdapter(taskArray, getApplicationContext());
        mainBinding.wrcViewTasks.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();
        updateTaskAdapter();
    }


}