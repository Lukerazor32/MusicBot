package com.example.telegram_bot.command;

import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.state.State;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.telegram_bot.command.CommandName.*;

public class HelpCommand implements State {
    private final SendBotMessageService sendBotMessageService;

    public final static String HELP_MESSAGE = String.format("<b>Доступные команды:</b>" +
            "\n\n" +
            "\uD83C\uDFB5 %s - начать работу бота" +
            "\n" +
            "\uD83D\uDEAB %s - остановить работу бота" +
            "\n" +
            "\uD83C\uDD98 %s - все команды бота" +
            "\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void startState(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }

    @Override
    public void execute(Update update) {

    }

    @Override
    public String nextState() {
        return EXIT.getCommandName();
    }
}
