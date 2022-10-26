package ru.netology.javacore;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

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
                    if (dataFromClient.getType().equals("ADD")) todos.addTask(dataFromClient.getTask());
                    else if (dataFromClient.getType().equals("REMOVE")) todos.removeTask(dataFromClient.getTask());
                    else {
                        out.println("Ошибка ввода");
                        continue;
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
