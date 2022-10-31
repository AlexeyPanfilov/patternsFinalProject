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
                    System.out.println(clientsInput);
                    dataFromClient.parseInputData(clientsInput);
                    switch (dataFromClient.getType()) {
                        case ("ADD"):
                            todos.addTask(dataFromClient.getTask());
                            todos.log(dataFromClient.getType(), dataFromClient.getTask());
                            break;
                        case ("REMOVE"):
                            todos.log(dataFromClient.getType(), dataFromClient.getTask());
                            todos.removeTask(dataFromClient.getTask());
                            break;
                        case ("RESTORE"):
//                            System.out.println(todos.getTasksList());
                            todos.restoreOperation();
                            break;
                        default:
                            out.println("Ошибка ввода");
                            break;
                    }
                    System.out.println(todos.getAllTasks());
                    out.println(todos.getAllTasks());
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
