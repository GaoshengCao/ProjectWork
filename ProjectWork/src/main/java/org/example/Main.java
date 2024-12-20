package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {

        try {
            // Initialize the TelegramBotsApi object
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Register the bot
            VelkozBot bot = new VelkozBot();
            botsApi.registerBot(bot);  // Register your bot here

        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }

    }
}