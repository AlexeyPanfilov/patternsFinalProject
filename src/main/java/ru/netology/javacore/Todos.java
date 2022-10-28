package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final int TASKS_LIST_SIZE = 7;
    private Map<String, Integer> tasksList = new TreeMap<>();
    int indexOfTask = 1;

    public void addTask(String task) {
        if (tasksList.size() < TASKS_LIST_SIZE) {
            tasksList.put(task, indexOfTask);
            indexOfTask++;
        }
    }

    public void removeTask(String task) {
        tasksList.remove(task);
    }

    public void restoreTask () {
        int lastIndexOfTask = 0;
        String taskToRestore = "";
        for (String task : tasksList.keySet()) {
            int taskIndex = tasksList.get(task);
            if (taskIndex > lastIndexOfTask) {
                lastIndexOfTask = taskIndex;
                taskToRestore = task;
            }
        }
        tasksList.remove(taskToRestore);
    }

    public String getAllTasks() {
        StringBuilder tasksToClient = new StringBuilder();
        for (String task : tasksList.keySet()) {
            tasksToClient.append(task).append(" ");
        }
        return tasksToClient.toString().trim();
    }

    public Map<String, Integer> getTasksList() {
        return tasksList;
    }
}
