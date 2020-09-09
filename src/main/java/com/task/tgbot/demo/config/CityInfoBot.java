package com.task.tgbot.demo.config;

import com.task.tgbot.demo.entity.CityInfo;
import com.task.tgbot.demo.logger.Log;
import com.task.tgbot.demo.service.CityService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public final class CityInfoBot extends TelegramLongPollingCommandBot {
    private String botName;
    private String botToken;
    private CityService cityService;

    @Autowired
    public CityInfoBot(@Value("${bot.name}") String botName,
                       @Value("${bot.token}") String botToken,
                       CityService cityService) {
        this.botName = botName;
        this.botToken = botToken;
        this.cityService=cityService;
        Log.info(">>>>>>>>BOT INITIATED: %s %s", botName, botToken);
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (!update.hasMessage()) {
            return;
        }

        Message msg = update.getMessage();
        User user = msg.getFrom();
        String msgFromUser = msg.getText();

        CityInfo cityInfo = cityService.findByName(msgFromUser);
        String responseMsg = cityInfo == null
                ? "посетите центр " + msgFromUser //default response.
                : cityInfo.getInfo();


        SendMessage answer = new SendMessage();
        answer.setText(responseMsg);
        answer.setChatId(msg.getChatId());

        replyToUser(answer, user);
    }

    private void replyToUser(SendMessage message, User user) {
        try {
            execute(message);
            Log.info("info replayed to %s", user.getId());
        } catch (TelegramApiException e) {
            Log.error("response to user.id=%s failed", user.getId());
        }
    }
}
