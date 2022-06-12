package net.reflection.lconbot.bot.action;

import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;

public class RemindUser implements Action<BotState, BotEvent> {
    @Override
    public void execute(StateContext<BotState, BotEvent> stateContext) {
        System.out.println("RemindUser");

    }
}
