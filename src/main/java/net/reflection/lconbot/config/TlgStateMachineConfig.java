package net.reflection.lconbot.config;

import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.BotState;
import net.reflection.lconbot.bot.BotStateMachinePersister;
import net.reflection.lconbot.bot.TlgStateMachineApplicationListener;
import net.reflection.lconbot.bot.action.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.guard.Guard;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class TlgStateMachineConfig extends EnumStateMachineConfigurerAdapter<BotState, BotEvent> {

    @Override
    public void configure(final StateMachineConfigurationConfigurer<BotState, BotEvent> config) throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(new TlgStateMachineApplicationListener());
    }

    @Override
    public void configure(final StateMachineStateConfigurer<BotState, BotEvent> states) throws Exception {
        states
                .withStates()
                .initial(BotState.INIT)
                .end(BotState.END)
                .states(EnumSet.allOf(BotState.class))
                .state(BotState.INIT, null, registerNewUser())
        ;
    }

    @Override
    public void configure(final StateMachineTransitionConfigurer<BotState, BotEvent> transitions) throws Exception {

        transitions
                .withExternal()
                .source(BotState.INIT)
                .target(BotState.STARTED)
                .event(BotEvent.INIT_START)
                .action(registerNewUser(), errorAction())

                .and()
                .withExternal()
                .source(BotState.STARTED)
                .target(BotState.PAUSED)
                .event(BotEvent.START_COMPLEET)
                .guard(registerCompleet())
                .action(preparePlan(), errorAction())

                .and()
                .withExternal()
                .source(BotState.PAUSED)
                .target(BotState.REMIND)
                .event(BotEvent.REMIND)
                .action(remindUser(), errorAction());
    }

    @Bean
    public Action<BotState, BotEvent> registerNewUser(){
        return new RegisterNewUser();
    }

    @Bean
    public Guard<BotState, BotEvent> registerCompleet() {
        return new RegisterCompleeted();
    }

    @Bean
    public Action<BotState, BotEvent> preparePlan() {
        return new PreparePlan();
    }

    @Bean
    public Action<BotState, BotEvent> remindUser() {
        return new RemindUser();
    }

    @Bean
    public Action<BotState, BotEvent> errorAction() {
        return new ErrorAction();
    }

    @Bean
    public StateMachinePersister<BotState, BotEvent, Long> persister() {
        return new DefaultStateMachinePersister<>(new BotStateMachinePersister());
    }


}
