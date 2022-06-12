package net.reflection.lconbot.bot.action;

import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.guard.Guard;

public class RegisterCompleeted implements Guard<BotState, BotEvent> {

    @Override
    public boolean evaluate(StateContext<BotState, BotEvent> stateContext) {
        System.out.println("RegisterCompleeted");
        return false;
    }
}
