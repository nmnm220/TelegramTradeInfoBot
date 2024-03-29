package org.telegrambot.trade.info.controller;


import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegrambot.trade.info.socket.ClientConnection;
import org.telegrambot.trade.info.socket.TelegramSocketServer;

public class TelegramBot extends TelegramLongPollingBot {
    private final String botName = "mytradingbotsinfo_bot";
    private long chatId = 316400150;
    private String receivedText;
    private TelegramSocketServer server;

    public void setClientConnection(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    private ClientConnection clientConnection;
    public TelegramBot(String botToken) {
        this(new DefaultBotOptions(), botToken);
    }
    public TelegramBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }
    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            receivedText = update.getMessage().getText();
            clientConnection.sendText(receivedText);
        }
    }
    public void sendMessage(String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(textToSend);
        //message.enableHtml(true);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
