package ru.netology.javacore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodosTests {

    Todos todos = new Todos();

    @Test
    @DisplayName("Тест на добавление задачи в список")
    void addTask () {
        todos.addTask("Task1");
        Assertions.assertEquals("[Task1]", todos.getAllTasks());
    }

    @Test
    @DisplayName("Проверка невозможности добавления более 7 задач в список")
    void maxTasksSize () {
        for (int i = 0; i < 666; i++) {
            todos.addTask("Task" + i);
        }
        Assertions.assertEquals(7, todos.getTasksList().size());
    }

    @Test
    @DisplayName("Тест на удаление задачи из списка")
    void removeTask () {
        todos.addTask("Task");
        todos.addTask("Task1");
        todos.removeTask("Task");
        Assertions.assertEquals("[Task1]", todos.getAllTasks());
    }

    @Test
    @DisplayName("Тест вывода списка задач на экран")
    void getAllTasks() {
        todos.addTask("Task");
        todos.addTask("Task1");
        Assertions.assertEquals("[Task, Task1]", todos.getAllTasks());
    }
}
