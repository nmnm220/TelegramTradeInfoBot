package org.telegrambot.trade.info.socket;

import org.telegrambot.trade.info.controller.TelegramBot;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelegramSocketServer extends Thread {
    private final int port = 6666;
    private final ServerSocket serverSocket;
    TelegramBot telegramBot;

    public TelegramSocketServer(TelegramBot telegramBot) throws IOException {
        this.telegramBot = telegramBot;
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(60000);
    }
    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                String textToSend = in.readUTF();
                telegramBot.sendMessage(textToSend);
                server.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
