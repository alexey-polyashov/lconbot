package net.reflection.lconbot.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import net.reflection.lconbot.bot.LconBot;
import net.reflection.lconbot.bot.TelegramFacade;


@Configuration
@AllArgsConstructor
public class ApplicationConfig {

    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public LconBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) {

        LconBot bot = new LconBot(telegramFacade, setWebhook);

        bot.setBotPath(telegramConfig.getWebhookPath());
        bot.setBotUserName(telegramConfig.getBotUserName());
        bot.setBotToken(telegramConfig.getBotToken());

        return bot;
    }

}
