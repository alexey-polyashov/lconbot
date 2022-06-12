package net.reflection.lconbot.controller;

import lombok.AllArgsConstructor;
import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
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

    private final StateMachineFactory<BotState, BotEvent> stateMachineFactory;
    
    @PostMapping("")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update){
        return meteoBot.onWebhookUpdateReceived(update);
    }
    
    @GetMapping("/test")
    public String test(){
        final StateMachine<BotState, BotEvent> stateMachine = stateMachineFactory.getStateMachine();
        stateMachine.sendEvent(BotEvent.INIT_START);
        return "test";
    }

    @GetMapping
    public String check(){
        return "ok";
    }

}
