package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;
import java.util.Scanner;

public class Client {

    private static final int PORT = 8989;
    private static final String HOST = "localHost";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите название задачи");
            String task = scanner.nextLine();
            System.out.println("Введите ADD для добавления задачи, REM для удаления, RES для изменения");
            String operation = scanner.nextLine().toUpperCase();
            String type = "";
            switch (operation) {
                case ("ADD"):
                    type = "ADD";
                    break;
                case ("REM"):
                    type = "REMOVE";
                    break;
                case ("RES"):
                    type = "RESTORE";
                    break;
                default:
                    System.out.println("Некорректный ввод операции");
                    break;
            }
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            DataForServer dataForServer = new DataForServer(type, task);
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
