package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final int TASKS_LIST_SIZE = 7;
    private Set<String> tasksList = new TreeSet<>();
    private Deque<String[]> operationsLog = new ArrayDeque<>();
    private boolean isTaskListFull;

    public void addTask(String task) {
        if (tasksList.size() < TASKS_LIST_SIZE) {
            tasksList.add(task);
            isTaskListFull = false;
        } else {
            isTaskListFull = true;
        }
    }

    public void removeTask(String task) {
        if (!tasksList.isEmpty()) {
            tasksList.remove(task);
        }
    }

    public String getAllTasks() {
        StringBuilder tasksToClient = new StringBuilder();
        for (String task : tasksList) {
            tasksToClient.append(task).append(" ");
        }
        return tasksToClient.toString().trim();
    }

    public Set<String> getTasksList() {
        return tasksList;
    }

    public int getTASKS_LIST_SIZE() {
        return TASKS_LIST_SIZE;
    }

    public void log (String type, String task) {
        String[] logs = {type, task};
        if (type.equals("ADD") && !isTaskListFull) {
            operationsLog.push(logs);
        } else if (type.equals("REMOVE") && tasksList.size() != 0 && tasksList.contains(task)) {
            operationsLog.push(logs);
        }
//        for (String[] lineOfLog : operationsLog) {
//            System.out.println("OperationsLog: " + Arrays.toString(lineOfLog));
//        }
    }

    public void restoreOperation () {
        System.out.println("OperationsLog for restore: " + operationsLog.size());
        if (!operationsLog.isEmpty()) {
            String[] line = operationsLog.peek();
            String lastType = line[0];
            String lastTask = line[1];
            for (String[] lineOfLog : operationsLog) {
                System.out.println(Arrays.toString(lineOfLog));
            }
            System.out.println("LogLastLine: " + Arrays.toString(line));
            if (lastType.equals("ADD")) {
                this.removeTask(lastTask);
            } else if (lastType.equals("REMOVE")) {
                this.addTask(lastTask);
            }
            operationsLog.pop();
        }
    }

    public Deque<String[]> getOperationsLog() {
        return operationsLog;
    }
}
