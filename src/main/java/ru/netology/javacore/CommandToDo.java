package ru.netology.javacore;

public class CommandToDo {

    private IncomingType type;
    private String task;

    public CommandToDo(IncomingType type, String task) {
        this.type = type;
        this.task = task;
    }

    public IncomingType getType() {
        return type;
    }

    public String getTask() {
        return task;
    }

    @Override
    public String toString() {
        return "[" + type + ", " + task + "]";
    }
}
