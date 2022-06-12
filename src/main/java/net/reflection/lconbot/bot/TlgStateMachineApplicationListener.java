package net.reflection.lconbot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

@Slf4j
public class TlgStateMachineApplicationListener implements StateMachineListener<BotState, BotEvent> {

    @Override
    public void stateChanged(State<BotState, BotEvent> state, State<BotState, BotEvent> state1) {
        log.info("Change state " + state + " -> " + state1);

    }

    @Override
    public void stateEntered(State<BotState, BotEvent> state) {
        log.info("Entered id " + state);

    }

    @Override
    public void stateExited(State<BotState, BotEvent> state) {
        log.info("Exited from " + state);

    }

    @Override
    public void stateMachineError(StateMachine<BotState, BotEvent> stateMachine, Exception e) {
        log.error("Error " + stateMachine.getState().getId() + e);

    }

    @Override
    public void eventNotAccepted(Message<BotEvent> message) {

    }

    @Override
    public void transition(Transition<BotState, BotEvent> transition) {

    }

    @Override
    public void transitionStarted(Transition<BotState, BotEvent> transition) {

    }

    @Override
    public void transitionEnded(Transition<BotState, BotEvent> transition) {

    }

    @Override
    public void stateMachineStarted(StateMachine<BotState, BotEvent> stateMachine) {

    }

    @Override
    public void stateMachineStopped(StateMachine<BotState, BotEvent> stateMachine) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<BotState, BotEvent> stateContext) {

    }

}
