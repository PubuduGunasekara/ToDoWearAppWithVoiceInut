package com.example.pubudupraneethgunasekaraassignment2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.pubudupraneethgunasekaraassignment2.R;
import com.example.pubudupraneethgunasekaraassignment2.databinding.ActivityAddTaskBinding;
import com.example.pubudupraneethgunasekaraassignment2.model.Task;
import com.example.pubudupraneethgunasekaraassignment2.utils.ConfirmUtils;
import com.example.pubudupraneethgunasekaraassignment2.utils.Constants;
import com.example.pubudupraneethgunasekaraassignment2.utils.TaskUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class AddTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private int mYear, mMonth, mDay, mHour, mMinute;

    private ArrayList<Task> taskList;
    ActivityAddTaskBinding addTaskBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_task);
        addTaskBinding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        View view = addTaskBinding.getRoot();
        setContentView(view);

        init();

    }

    private void init() {
        addTaskBinding.addDate.setOnClickListener(this);
        addTaskBinding.addTime.setOnClickListener(this);
        addTaskBinding.submitTask.setOnClickListener(this);

        taskList = new ArrayList<>();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == addTaskBinding.addDate.getId()) {
            getDateFieldData();
        }
        if (v.getId() == addTaskBinding.addTime.getId()) {
            getTimeFieldData();
        }
        if (v.getId() == addTaskBinding.submitTask.getId()) {
            addToTaskList();
        }
    }

    private void getDateFieldData() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        addTaskBinding.addDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void getTimeFieldData() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        addTaskBinding.addTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void addToTaskList() {

        String taskName = "Task 1";
        String taskDate = addTaskBinding.addDate.getText().toString();
        String taskTime = addTaskBinding.addTime.getText().toString();
        String taskId = String.valueOf(System.currentTimeMillis());

        if (taskName != null && taskDate != "Select Date" && taskTime != "Select Time") {
            Task taskDetails = new Task(taskId, taskName, taskDate, taskTime);
            //taskList.add(taskDetails);

            ArrayList<Task> existingTasks = TaskUtils.getAllTasks(this);

            // Add the new task to the ArrayList
            existingTasks.add(taskDetails);

            TaskUtils.saveTask(existingTasks, this);

            //ConfirmUtils.showSavedMessage(getString(R.string.task_saved), this);

            Intent intent = new Intent(AddTaskActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }
}