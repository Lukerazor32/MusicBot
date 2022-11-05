package com.example.telegram_bot.state;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface State {
    void startState(Update update);

    void execute(Update update);

    String nextState();
}
