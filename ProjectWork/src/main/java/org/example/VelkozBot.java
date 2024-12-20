package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class VelkozBot extends TelegramLongPollingBot {
    final private String botAPI = "8128295411:AAHhbc8zMPW_S1VFG9SLhFZZ076Eyoe7sgQ";
    final private String botUsername = "velkoz_helper_bot";
    TelegramLongPollingBot a;
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botAPI;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            String text = message.getText();
            Long chatId = message.getChatId();

            if (text.equals("/start")) {
                sendMessage(chatId, "Hello I'm Vel'koz Bot");
            }
        }
    }

    private void sendMessage(Long chatId,String text) {
        try{
            execute(new SendMessage(chatId.toString(), text));
        }catch ( TelegramApiException e ){
            e.printStackTrace();
        }
    }
}
