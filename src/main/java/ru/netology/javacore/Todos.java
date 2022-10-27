package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private final int TASKSLISTSIZE = 7;
    private Set<String> tasksList = new TreeSet<>();

    public void addTask(String task) {
        if (tasksList.size() < TASKSLISTSIZE) tasksList.add(task);
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public String getAllTasks() {
        return tasksList.toString();
    }

    public Set<String> getTasksList() {
        return tasksList;
    }
}
