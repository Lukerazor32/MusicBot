package com.example.telegram_bot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    void execute(Update update);

    default boolean readyState() {
        return true;
    }
}
