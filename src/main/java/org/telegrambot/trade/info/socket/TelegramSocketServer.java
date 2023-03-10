package org.telegrambot.trade.info.socket;

import org.telegrambot.trade.info.controller.TelegramBot;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TelegramSocketServer extends Thread {
    private final int port = 6666;
    private final ServerSocket serverSocket;
    private final TelegramBot telegramBot;
    private String textFromTelegram = "test";

    public TelegramSocketServer(TelegramBot telegramBot) throws IOException {
        this.telegramBot = telegramBot;
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(10000);
    }

    public void run() {
        while (true) {
            try {
                Socket server = serverSocket.accept();
                DataInputStream in = new DataInputStream(server.getInputStream());
                String textToSend = in.readUTF();
                telegramBot.sendMessage(textToSend);
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                out.writeUTF(textFromTelegram);
                if (textFromTelegram != null) {
                    out.writeUTF(textFromTelegram);
                    textFromTelegram = null;
                }
                server.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void setTextFromTelegram(String text) {
        textFromTelegram = text;
    }
}
