package net.reflection.lconbot.bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;


@Getter
@Setter
public class MeteoBot extends SpringWebhookBot {

    private String botPath;
    private String botUserName;
    private String botToken;

    private TelegramFacade telegramFacade;


    public MeteoBot(TelegramFacade telegramFacade, DefaultBotOptions options, SetWebhook setWebhook) {
        super(options, setWebhook);
        this.telegramFacade = telegramFacade;
    }
    public MeteoBot(TelegramFacade telegramFacade, SetWebhook setWebhook) {
        super(setWebhook);
        this.telegramFacade = telegramFacade;
    }

    @Override
    public String getBotUsername() {
        return this.botUserName;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update);
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }
}
