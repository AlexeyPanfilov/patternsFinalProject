package ru.netology.javacore;

import java.util.*;
import java.util.stream.Collectors;

public class Todos {
    private Set<String> tasksList = new TreeSet<>();

    public void addTask(String task) {
        if (tasksList.size() < 7) tasksList.add(task);
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
