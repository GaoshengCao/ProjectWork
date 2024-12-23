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
            String command;
            String prompt = "";
            if (text.split(" ").length > 1) {
                command = text.split(" ")[0];
                prompt = text.split(" ")[1];
                if (text.split(" ").length > 2) {
                    prompt += " " + text.split(" ")[2];
                }
            }else
                command = text;



            Long chatId = message.getChatId();

            switch (command){
                case "/start":
                    this.start(chatId);
                    break;
                case "/help":
                    this.help(chatId);
                    break;
                case "/champion":
                    this.champion(chatId, prompt);
                    break;
                case "/build":
                    this.build(chatId, prompt);
                    break;
                case "/counter":
                    this.counter(chatId, prompt);
                    break;
                case "/stats":
                    this.stats(chatId, prompt);
                    break;
                case "/top":
                    this.top(chatId, prompt);
                    break;
                case "/allchampion":
                    this.allchampions(chatId, prompt);
                    break;
                case "/role":
                    this.role(chatId);
                    break;
                default:
                    sendMessage(chatId, "Scusa Non ho capito cosa hai detto riprova in unaltro modo.");

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
    private void start(Long chatId){
            sendMessage(chatId, "Hello I'm Vel'koz Bot, I'm here to provide you with impormantion about champions of League of Legends, /help to know more");
    }
    private void help(Long chatId){String helpText = "";
        helpText += "/start : Viene Avviato la conversazione";helpText += "\n";
        helpText += "/help : Visualizzi questo messaggio";helpText += "\n";
        helpText += "/champion <champion name> : Risponde con tutte le informazioni rilevanti al campione," +
                "statistiche, build e counter. es. /champion aatrox;";helpText += "\n";
        helpText += "//build <champion name>: Restituisce la build da mettere sul campione. es /build aatrox;";helpText += "\n";
        helpText += "/counter <champion name> : Visualizza i top 3 campioni contro il campione inserito. es. /counter aatrox.";helpText += "\n";
        helpText += "/stats <champion name> : Visualizza il WinRate, PickRate ,BanRate e Tier del campione inserito. es. /stats aatrox.";helpText += "\n";
        helpText += "/top <role> : Visualizza quali sono i top 5 campioni per il ruolo(top,jungle,mid,adc,support) selezionato. /top jungle. ";helpText += "\n";
        helpText += "/allchampion : restituisce elenco intero dei campioni. !!Attenzione, sono in tutto 169 campioni.!!";helpText += "\n";
        helpText += "/role : Visualizza tutti i ruoli possibili";helpText += "\n";
        sendMessage(chatId, helpText);
    }
    private void champion(Long chatId, String prompt){
        DataBase db = new DataBase();

        if (prompt.contains("&")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            String par3 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 += "amp;";
            par3 = par3.substring(0, 1).toUpperCase() + par3.substring(1);
            prompt = par1 + par2 + par3;

        }else if (prompt.contains(" ")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else if (prompt.contains("'")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else {
            prompt = prompt.substring(0, 1).toUpperCase() + prompt.substring(1);
        }
        String responce = "";
        responce += prompt + " Info:\n";
        responce += "\n";
        responce += db.getBuild(prompt);
        responce += "\n";
        responce += "\n";
        responce += db.getCounter(prompt);
        responce += "\n";
        responce += "\n";
        responce += db.getStats(prompt);

        sendMessage(chatId, responce);
    }
    private void stats(Long chatId, String prompt){
        DataBase db = new DataBase();
        if (prompt.contains("&")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            String par3 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 += "amp;";
            par3 = par3.substring(0, 1).toUpperCase() + par3.substring(1);
            prompt = par1 + par2 + par3;

        }else if (prompt.contains(" ")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else if (prompt.contains("'")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else {
            prompt = prompt.substring(0, 1).toUpperCase() + prompt.substring(1);
        }
        String responce = "";
        responce += db.getStats(prompt);
        sendMessage(chatId, responce);
    }
    private void build(Long chatId, String prompt){
        DataBase db = new DataBase();
        if (prompt.contains("&")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            String par3 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 += "amp;";
            par3 = par3.substring(0, 1).toUpperCase() + par3.substring(1);
            prompt = par1 + par2 + par3;

        }else if (prompt.contains(" ")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else if (prompt.contains("'")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else {
            prompt = prompt.substring(0, 1).toUpperCase() + prompt.substring(1);
        }
        String responce = "";
        responce += db.getBuild(prompt);
        sendMessage(chatId, responce);
    }
    private void counter(Long chatId, String prompt){DataBase db = new DataBase();
        if (prompt.contains("&")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            String par3 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 += "amp;";
            par3 = par3.substring(0, 1).toUpperCase() + par3.substring(1);
            prompt = par1 + par2 + par3;

        }else if (prompt.contains(" ")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else if (prompt.contains("'")){
            String par1 = prompt.split(" ")[0];
            String par2 = prompt.split(" ")[1];
            par1 = par1.substring(0, 1).toUpperCase() + par1.substring(1);
            par2 = par2.substring(0, 1).toUpperCase() + par2.substring(1);
            prompt = par1 + par2;
        }else {
            prompt = prompt.substring(0, 1).toUpperCase() + prompt.substring(1);
        }
        String responce = "";
        responce += db.getCounter(prompt);
        sendMessage(chatId, responce);
    }
    private void top(Long chatId, String prompt){
        DataBase db = new DataBase();
        prompt = prompt.substring(0, 1).toUpperCase() + prompt.substring(1);
        sendMessage(chatId, db.getTop(prompt));
    }
    private void allchampions(Long chatId, String prompt){
        DataBase db = new DataBase();
        sendMessage(chatId, db.getAllChampions());
    }
    private void role(Long chatId){
        String roles = "-Top\n-Jungle\n-Mid\n-Bot\n-Support.";
        sendMessage(chatId,roles);
    }
}
