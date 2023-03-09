package org.telegrambot.trade.info.controller;


import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    public TelegramBot(String botToken) {
        this(new DefaultBotOptions(), botToken);
    }
    public TelegramBot(DefaultBotOptions options, String botToken) {
        super(options, botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }
}
