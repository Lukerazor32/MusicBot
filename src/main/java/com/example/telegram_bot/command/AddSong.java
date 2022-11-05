package com.example.telegram_bot.command;

import com.example.telegram_bot.bot.Music_bot;
import com.example.telegram_bot.service.SendBotMessageService;
import com.example.telegram_bot.state.State;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import static com.example.telegram_bot.command.CommandName.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

@Component
public class AddSong implements State {
    private final SendBotMessageService sendBotMessageService;
    private final String AdminID = "1395425257";
    private final Music_bot music_bot;
    private String state = ADDSONG.getCommandName();


    public AddSong(SendBotMessageService sendBotMessageService, Music_bot music_bot) {
        this.sendBotMessageService = sendBotMessageService;
        this.music_bot = music_bot;
    }

    @Override
    public void startState(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Здравствуй, админ!\nЗагрузи новую песню!");
    }

    @Override
    public void execute(Update update) {
        ArrayList<String> SongInfo = new ArrayList<>();
        if(update.getMessage().hasAudio()) {
            String audioId = update.getMessage().getAudio().getFileId();
            String audioName = update.getMessage().getAudio().getFileName();
            String audioPath = "C:/Users/Maksim/IdeaProjects/telegram_bot/src/main/resources/audio/" + audioName;

            try {
                URL audioURL = new URL("https://api.telegram.org/bot" + music_bot.getBotToken() + "/getFile?file_id=" + audioId);
                BufferedReader br = new BufferedReader(new InputStreamReader(audioURL.openStream()));
                String getFileResponse = br.readLine();

                JSONObject jresult = new JSONObject(getFileResponse);
                JSONObject path = jresult.getJSONObject("result");
                String audioPathJson = path.getString("file_path");

                File localAudio = new File(audioPath);
                InputStream is = new URL("https://api.telegram.org/file/bot" + music_bot.getBotToken() + "/" + audioPathJson).openStream();
                if (!localAudio.exists()) {
                    FileUtils.copyInputStreamToFile(is, localAudio);
                    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "успешно");
                    SongInfo.add(audioPath);
                }
                else {
                    sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Такой файл уже существует");
                }
                br.close();
                is.close();
                state = ADDMOODS.getCommandName();
            }
            catch (IOException e) {
                e.printStackTrace();
                state = ADDMOODS.getCommandName();
            }
        }
        else {
            sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), "Пожалуйста, загрузите песню");
            state = ADDSONG.getCommandName();
        }
    }

    @Override
    public String nextState() {
        return state;
    }
}
