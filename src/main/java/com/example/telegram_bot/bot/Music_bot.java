package com.example.telegram_bot.bot;

import com.example.telegram_bot.command.CommandContainer;
import com.example.telegram_bot.service.SendBotMessageServiceImpl;
import com.example.telegram_bot.service.TelegramUserService;
import com.example.telegram_bot.state.State;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Music_bot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    private State state = null;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    public Music_bot(TelegramUserService telegramUserService) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, this);
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String chatId = update.getMessage().getChatId().toString();
            if (update.getMessage().hasText()) {
                String message = update.getMessage().getText().trim();

                if (message.startsWith(COMMAND_PREFIX)) {
                    String commandIdentifier = message.split(" ")[0].toLowerCase();

                    commandContainer.retrieveCommand(commandIdentifier, chatId).startState(update);
                    setState(commandContainer.retrieveCommand(commandIdentifier, chatId));
                    setState(commandContainer.retrieveCommand(state.nextState(), chatId));
                }
                else {
                    state.execute(update);
                    if (state != commandContainer.retrieveCommand(state.nextState(), chatId)) {
                        setState(commandContainer.retrieveCommand(state.nextState(), chatId));
                        state.startState(update);
                    }
                }
            }
            else {
                state.execute(update);
                if (state != commandContainer.retrieveCommand(state.nextState(), chatId)) {
                    setState(commandContainer.retrieveCommand(state.nextState(), chatId));
                    state.startState(update);
                }
            }

        }
    }

    void setState(State state) {
        this.state = state;
    }
}
