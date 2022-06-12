package net.reflection.lconbot.bot;

import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;

import java.util.HashMap;

public class BotStateMachinePersister implements StateMachinePersist<BotState, BotEvent, Long> {

    private final HashMap<Long, StateMachineContext<BotState, BotEvent>> contexts = new HashMap<>();

    @Override
    public void write(StateMachineContext<BotState, BotEvent> context, Long contextObj) throws Exception {
        contexts.put(contextObj, context);
    }

    @Override
    public StateMachineContext<BotState, BotEvent> read(Long contextObj) throws Exception {
        return contexts.get(contextObj);
    }
}
