package org.telegrambot.trade.info;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import org.telegrambot.trade.info.apikey.ApiKey;
import org.telegrambot.trade.info.controller.TelegramBot;
import org.telegrambot.trade.info.socket.TelegramSocketServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        TelegramBot telegramBot = new TelegramBot(ApiKey.getAPIKey());
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        try {
            TelegramSocketServer server = new TelegramSocketServer(telegramBot);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
