package command;

import com.example.telegram_bot.bot.Music_bot;
import com.example.telegram_bot.command.CommandContainer;
import com.example.telegram_bot.command.CommandName;
import com.example.telegram_bot.command.UnknownCommand;
import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.service.TelegramUserService;
import com.example.telegram_bot.state.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

public class CommandContainerTest {
    private CommandContainer commandContainer;
    private String chatId = "1234567890";

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        TelegramUserService telegramUserService = Mockito.mock(TelegramUserService.class);
        Music_bot music_bot = Mockito.mock(Music_bot.class);
        commandContainer = new CommandContainer(sendBotMessageService, telegramUserService, music_bot);
    }

    @Test
    public void shouldGetAllTheExistingCommands() {
        //when-then
        Arrays.stream(CommandName.values())
                .forEach(commandName -> {
                    State command = commandContainer.retrieveCommand(commandName.getCommandName(), chatId);
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {
        //given
        String unknownCommand = "/fgjhdfgdfg";
        String chatId = "1234567890";

        //when
        State command = commandContainer.retrieveCommand(unknownCommand, chatId);

        //then
        Assertions.assertEquals(UnknownCommand.class, command.getClass());
    }
}
