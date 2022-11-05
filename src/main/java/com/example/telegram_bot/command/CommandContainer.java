package com.example.telegram_bot.command;

import com.example.telegram_bot.bot.Music_bot;
import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.service.TelegramUserService;
import com.example.telegram_bot.state.State;
import com.google.common.collect.ImmutableMap;

import static com.example.telegram_bot.command.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, State> commandMap;
    private final ImmutableMap<String, State> commandAdminMap;
    private final State unknownCommand;
    private final String AdminID = "1395425257";

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, Music_bot music_bot) {
        commandMap = ImmutableMap.<String, State>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(EXIT.getCommandName(), new EmptyCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);

        commandAdminMap = ImmutableMap.<String, State>builder()
                .put(ADDSONG.getCommandName(), new AddSong(sendBotMessageService, music_bot))
                .put(ADDMOODS.getCommandName(), new AddMoods(sendBotMessageService, music_bot))
                .build();
    }

    public State retrieveCommand(String commandIdentifier, String chatId) {
        if (commandAdminMap.containsKey(commandIdentifier) && chatId.equals(AdminID)) return commandAdminMap.get(commandIdentifier);
        else return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
