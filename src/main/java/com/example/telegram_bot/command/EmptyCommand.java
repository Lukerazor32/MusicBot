package com.example.telegram_bot.command;

import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.state.State;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.example.telegram_bot.command.CommandName.EXIT;

public class EmptyCommand implements State {
    private final SendBotMessageService sendBotMessageService;

    public EmptyCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void startState(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Введите одну из команд");
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Введите одну из команд");
    }

    @Override
    public String nextState() {
        return EXIT.getCommandName();
    }
}
