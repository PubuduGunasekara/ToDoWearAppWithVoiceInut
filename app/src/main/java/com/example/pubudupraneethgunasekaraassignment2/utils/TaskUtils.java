package com.example.pubudupraneethgunasekaraassignment2.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pubudupraneethgunasekaraassignment2.model.Task;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TaskUtils {
    private static final String LIST_KEY = "List_key";

    public static void saveTask(ArrayList<Task> taskArray, Context context) {

  if (taskArray != null) {

            Gson gson = new Gson();
            String jsonString = gson.toJson(taskArray);

            SharedPreferences sharedPref = context.getSharedPreferences("task_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(LIST_KEY, jsonString);
            editor.apply();
        }
    }

    public static ArrayList<Task> getAllTasks(Context context) {

        SharedPreferences sharedPref = context.getSharedPreferences("task_details", Context.MODE_PRIVATE);

        String jsonString = sharedPref.getString(LIST_KEY, "");

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Task>>() {
        }.getType();
        ArrayList<Task> taskList = gson.fromJson(jsonString, type);
        return taskList;
    }

    public static void deleteTask(String id, Context context) {
        if (id != null) {
            SharedPreferences sharedPref = context.getSharedPreferences("task_details", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove(id);
            editor.commit();
        }
    }
}
