package org.telegrambot.trade.info.socket;

import org.telegrambot.trade.info.controller.TelegramBot;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelegramSocketServer extends Thread {
    private final int port = 6666;
    private final ServerSocket serverSocket;
    private final TelegramBot telegramBot;

    public TelegramSocketServer(TelegramBot telegramBot) throws IOException {
        this.telegramBot = telegramBot;
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientConnection connection = new ClientConnection(socket, telegramBot);
                connection.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
