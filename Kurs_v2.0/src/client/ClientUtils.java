package client;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientUtils extends JFrame {
    // адрес сервера
    private static final String SERVER_HOST = "localhost";
    // порт
    private static final int SERVER_PORT = 3443;
    // клиентский сокет
    public static Socket clientSocket;
    // входящее сообщение
    public static Scanner inMessage;
    // исходящее сообщение
    public static PrintWriter outMessage;

    public void clientConnect() {
        try {
            // подключаемся к серверу
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

