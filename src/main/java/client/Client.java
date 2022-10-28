package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private static final int PORT = 8989;
    private static final String HOST = "localHost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ADD для добавления задачи, REM для удаления, RES для изменения");
            String operation = scanner.nextLine().toUpperCase();
            String type = "";
            String task = "";
            DataForServer dataForServer = new DataForServer();
            switch (operation) {
                case ("ADD"):
                    System.out.println("Введите название задачи для добавления");
                    task = scanner.nextLine();
                    type = "ADD";
                    dataForServer = new DataForServer(type, task);
                    break;
                case ("REM"):
                    System.out.println("Введите название задачи для удаления");
                    task = scanner.nextLine();
                    type = "REMOVE";
                    dataForServer = new DataForServer(type, task);
                    break;
                case ("RES"):
                    type = "RESTORE";
                    dataForServer = new DataForServer(type);
                    break;
                default:
                    System.out.println("Некорректный ввод операции");
                    break;
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            String requestToServer = gson.toJson(dataForServer);
            System.out.println(requestToServer);

            try (Socket clientSocket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                out.println(requestToServer);
                System.out.println(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
