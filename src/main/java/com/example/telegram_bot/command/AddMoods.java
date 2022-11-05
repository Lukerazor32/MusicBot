package com.example.telegram_bot.command;

import com.example.telegram_bot.bot.Music_bot;
import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.state.State;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.example.telegram_bot.command.CommandName.*;

public class AddMoods implements State {
    private final SendBotMessageService sendBotMessageService;
    private final Music_bot music_bot;
    private final String state = ADDMOODS.getCommandName();

    public AddMoods(SendBotMessageService sendBotMessageService, Music_bot music_bot) {
        this.sendBotMessageService = sendBotMessageService;
        this.music_bot = music_bot;
    }

    @Override
    public void startState(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Введите настроения (пример: 1 2 3)");
    }

    @Override
    public void execute(Update update) {

    }

    @Override
    public String nextState() {
        return state;
    }
}
