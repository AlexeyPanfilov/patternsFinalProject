package ru.netology.javacore;

import java.util.*;

public class Todos {
    private final int TASKS_LIST_SIZE = 7;
    private Set<String> tasksList = new TreeSet<>();
    private Deque<CommandToDo> operationsLog = new ArrayDeque<>();
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

    public void log (CommandToDo whatToDo) {
        if (whatToDo.getType().equals(IncomingType.ADD) && !isTaskListFull) {
            operationsLog.push(whatToDo);
        } else if (whatToDo.getType().equals(IncomingType.REMOVE)
                && tasksList.size() != 0 && tasksList.contains(whatToDo.getTask())) {
            operationsLog.push(whatToDo);
        }
//        for (CommandToDo lineOfLog : operationsLog) {
//            System.out.println("OperationsLog: " + lineOfLog);
//        }
    }

    public void restoreOperation () {
//        System.out.println("OperationsLog for restore: " + operationsLog.size());
        if (!operationsLog.isEmpty()) {
            CommandToDo line = operationsLog.peek();
            IncomingType lastType = line.getType();
            String lastTask = line.getTask();
//            for (CommandToDo lineOfLog : operationsLog) {
//                System.out.println(lineOfLog);
//            }
//            System.out.println("LogLastLine: " + line);
            if (lastType.equals(IncomingType.ADD)) {
                this.removeTask(lastTask);
            } else if (lastType.equals(IncomingType.REMOVE)) {
                this.addTask(lastTask);
            }
            operationsLog.pop();
        }
    }

    public Deque<CommandToDo> getOperationsLog() {
        return operationsLog;
    }
}
