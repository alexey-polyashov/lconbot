package net.reflection.lconbot.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TelegramConfig {
    @Value("${telegram.webhook-path}")
    String webhookPath;
    @Value("${telegram.bot-token}")
    String botToken;
    @Value("${telegram.user-name}")
    String botUserName;
}
