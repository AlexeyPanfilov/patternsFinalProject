package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataFromClient {
    private String type;
    private String task;

    public DataFromClient() {}

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    public String[] parseInputData (String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        DataFromClient dataFromClient = gson.fromJson(json, DataFromClient.class);
        this.task = dataFromClient.getTask();
        this.type = dataFromClient.getType().toUpperCase();
        System.out.println("type: " + type);
        return new String[]{type, task};
    }
}
