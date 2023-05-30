package org.telegrambot.trade.info.socket;

import org.telegrambot.trade.info.controller.TelegramBot;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {
    private final Socket socket;
    private PrintWriter outputWriter;
    private final TelegramBot telegramBot;

    public ClientConnection(Socket socket, TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
        this.socket = socket;
        telegramBot.setClientConnection(this);
    }

    public void sendText(String textToSend) {
        outputWriter.println(textToSend);
        System.out.println(textToSend);
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader inputBuffer = new BufferedReader(inputReader);
            OutputStream outputStream = socket.getOutputStream();
            this.outputWriter = new PrintWriter(outputStream, true);
            String input = "";
            StringBuilder textToSend = new StringBuilder();
            while (true) {
                Thread.sleep(50);
                //if ((input = inputBuffer.readLine()) != null) {
                while (inputBuffer.ready()) {
                    input = inputBuffer.readLine();
                    textToSend.append(input).append("\n");
                }
                if (!textToSend.isEmpty()) {
                    telegramBot.sendMessage(textToSend.toString());
                    System.out.println(textToSend);
                }
                textToSend.setLength(0);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
