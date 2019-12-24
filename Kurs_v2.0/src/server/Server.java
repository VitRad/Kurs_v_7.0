package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // порт, который будет прослушивать наш сервер
    static final int PORT = 3443;
    // список клиентов, которые будут подключаться к серверу
    public static ArrayList<ClientHandler> clients = new ArrayList<>();
    public static ArrayList<String> history;

    public void serverConnect() throws IOException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        history = new ArrayList<>();
        try {
						// создаём серверный сокет на определенном порту
            serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен!");
						// запускаем бесконечный цикл
            while (true) {
                // ждём подключений от сервера
                clientSocket = serverSocket.accept();
                // создаём обработчик клиента, который подключился к серверу
                ClientHandler client = new ClientHandler();
                client.clientSession(clientSocket, this);
                clients.add(client);
                // каждое подключение клиента обрабатываем в новом потоке
                new Thread(client).start();
                //добавим вывод истории сообщений в консоль сервера

            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
					// закрываем подключение
                clientSocket.close();
                System.out.println("Сервер остановлен");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    // отправляем сообщение всем клиентам
    public void sendMessageToAllClients(String msg) {
        //Добавим проверку,
        //если сообщение адресовано конкретному клиенту, ему и отправим

        //иначе, ссобщения получат все пользователи
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }

    }

		// удаляем клиента из коллекции при выходе из чата
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

        //Вывести историю чата в консоль
    public void printHistory(){
        System.out.println("------Вывод истории сообщений:-------");
        for (String h: history){
            System.out.println(h);
        }
        System.out.println("------End history--------");
    }
}
