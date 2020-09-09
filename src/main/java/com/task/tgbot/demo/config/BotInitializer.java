package com.task.tgbot.demo.config;

import com.task.tgbot.demo.logger.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;


@Component //initializer implemented as eager singleton
public class BotInitializer {
    @Autowired
    private CityInfoBot cityInfoBot;
    private TelegramBotsApi telegramBotsApi;

    static {
        ApiContextInitializer.init(); //should be called first, so it was moved to static context
    }


    @PostConstruct
    public void init() {
        telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(cityInfoBot);
        } catch (TelegramApiRequestException e) {
            throw new Error(e);
        }
        Log.info("BOT started");
    }
}
