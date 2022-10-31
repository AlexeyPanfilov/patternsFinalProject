package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TodosTests {

    Todos todos = new Todos();

    @Test
    @DisplayName("Тест на добавление задачи в список")
    void addTask() {
        todos.addTask("Task1");
        Assertions.assertEquals("Task1", todos.getAllTasks());
    }

    @Test
    @DisplayName("Проверка невозможности добавления более 7 задач в список")
    void maxTasksSize() {
        for (int i = 0; i < (todos.getTASKS_LIST_SIZE() + 2); i++) {
            todos.addTask("Task" + i);
        }
        Assertions.assertEquals(todos.getTASKS_LIST_SIZE(), todos.getTasksList().size());
    }

    @Test
    @DisplayName("Тест на удаление задачи из списка")
    void removeTask() {
        todos.addTask("Task");
        todos.addTask("Task1");
        todos.removeTask("Task");
        Assertions.assertEquals("Task1", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тест вывода списка задач на экран")
    void getAllTasks() {
        todos.addTask("Task");
        todos.addTask("Task1");
        Assertions.assertEquals("Task Task1", todos.getAllTasks());
    }

    @Test
    @DisplayName("RESTORE тест")
    void restore() {
        for (int i = 0; i < 2; i++) {
            todos.addTask("Task" + i);
        }
        todos.restoreOperation();
        Assertions.assertEquals("Task0 Task1", todos.getAllTasks());
    }

    @Test
    @DisplayName("Loging тест")
    void log() {
        todos.addTask("t1");
        todos.log("ADD","t1");
        todos.log("ADD", "t2");
        todos.log("REMOVE", "t1");
        List<String[]> log = new ArrayList<>();
        log.add(new String[]{"ADD", "t1"});
        log.add(new String[]{"ADD", "t2"});
        log.add(new String[]{"REMOVE", "t1"});
        int i = log.size();
        for (String[] line : todos.getOperationsLog()) {
            i--;
            Assertions.assertEquals(Arrays.toString(log.get(i)), Arrays.toString(line));
        }
    }

    @Test
    @DisplayName("Тест при попытке логгирования операций, если список задач заполнен (в лог должны перестать " +
            "записываться операции ADD")
    void noLogIfMaxTasksInList() {
        for (int i = 0; i <= todos.getTASKS_LIST_SIZE(); i++) {
            todos.addTask("Task" + i);
        }
        todos.log("ADD","t8");
        todos.log("ADD", "t9");
        todos.log("REMOVE", "t7");
        for (String[] line : todos.getOperationsLog()) {
            Assertions.assertEquals("[REMOVE, t7]", Arrays.toString(line));
        }
    }
}
