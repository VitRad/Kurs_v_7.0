package client;

public class ClientStarter {
    public static void main(String[] args) {
        new ClientUtils().clientConnect();
        new ClientWindow().windowGen();
    }
}
