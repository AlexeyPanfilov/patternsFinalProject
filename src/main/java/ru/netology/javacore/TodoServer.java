package ru.netology.javacore;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    protected int port;
    protected Todos todos;

    public TodoServer(int port, Todos todos) {
        this.port = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Starting server at " + port + "...");
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            DataFromClient dataFromClient = new DataFromClient();
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream())) {
                    String clientsInput = in.readLine();
//                    System.out.println(clientsInput);
                    CommandToDo whatToDo = dataFromClient.parseInputData(clientsInput);
                    if (dataFromClient.getType() == null) {
                        out.println("Ошибка ввода данных");
                        continue;
                    }
                    switch (dataFromClient.getType()) {
                        case ADD:
                            // В данном блоке последовательность выполнения операций имеет логическое значение

                            todos.addTask(dataFromClient.getTask());
                            todos.log(whatToDo);
                            break;
                        case REMOVE:
                            // В данном блоке последовательность выполнения операций имеет логическое значение

                            todos.log(whatToDo);
                            todos.removeTask(dataFromClient.getTask());
                            break;
                        case RESTORE:
                            todos.restoreOperation();
                            break;
                        default:
                            out.println("Ошибка ввода данных");
                            break;
                    }
//                    System.out.println(todos.getAllTasks());
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
