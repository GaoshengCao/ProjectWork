package org.example;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        try {
//            // Initialize the TelegramBotsApi object
//            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//
//            // Register the bot
//            VelkozBot bot = new VelkozBot();
//            botsApi.registerBot(bot);  // Register your bot here
//
//        } catch (TelegramApiException e) {
//            System.out.println(e.getMessage());
//        }


        //Inserimento Build/Stats/Counter
        DataBase dataBase = new DataBase();
        WebScraper webScraper = new WebScraper();

        List<Champion> champions = new ArrayList<>();

        champions = webScraper.scrapeLinks();

        webScraper.scrapeInParallel(champions);
        System.out.println("Ciao");



        //Inserimento Rune
//        List<String> rune = webScraper.scrapeRunes();
//        int n = 2;
//        int i = 1;
//        boolean Precision = true;
//        String key = "keystone";
//        String po1 = "slot 1";
//        String po2 = "slot 2";
//        String po3 = "slot 3";
//        for (String runa : rune) {
//            if (Precision){
//                dataBase.insertRune(runa,key);
//                i++;
//                if (i == 4){
//                    Precision = false;
//                    i = 1;
//                }
//
//            }
//            else{
//                switch (n){
//                    case 1:
//                        dataBase.insertRune(runa,key);
//                        i++;
//                        if(i == 4){
//                            i = 1;
//                            n++;
//                        }
//                        break;
//                    case 2:
//                        dataBase.insertRune(runa,po1);
//                        i++;
//                        if(i == 4){
//                            i = 1;
//                            n++;
//                        }
//                        break;
//                    case 3:
//                        dataBase.insertRune(runa,po2);
//                        i++;
//                        if(i == 4){
//                            i = 1;
//                            n++;
//                        }
//                        break;
//                    case 4:
//                        dataBase.insertRune(runa,po3);
//                        i++;
//                        if(i == 4){
//                            i = 1;
//                            n = 1;
//                        }
//                        break;
//                }
//                            }
//
//        }

        //Inserimento Incantesimi evocatore in Database
//        List<String> summ = webScraper.scrapeSpell();
//        for (String sum : summ) {
//            dataBase.insertSummoner(sum);
//        }

    }
}