package net.reflection.lconbot.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import net.reflection.lconbot.bot.LconBot;

@RestController
@AllArgsConstructor
public class MainController {

    private final LconBot meteoBot;

    @PostMapping("")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return meteoBot.onWebhookUpdateReceived(update);
    }

    @GetMapping
    public String check(){
        return "ok";
    }

}
