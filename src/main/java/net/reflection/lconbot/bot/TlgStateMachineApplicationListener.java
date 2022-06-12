package net.reflection.lconbot.bot;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.transition.Transition;

public class TlgStateMachineApplicationListener implements StateMachineListener<BotState, BotEvent> {
    @Override
    public void stateChanged(State<BotState, BotEvent> state, State<BotState, BotEvent> state1) {

    }

    @Override
    public void stateEntered(State<BotState, BotEvent> state) {

    }

    @Override
    public void stateExited(State<BotState, BotEvent> state) {

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
    public void stateMachineError(StateMachine<BotState, BotEvent> stateMachine, Exception e) {

    }

    @Override
    public void extendedStateChanged(Object o, Object o1) {

    }

    @Override
    public void stateContext(StateContext<BotState, BotEvent> stateContext) {

    }
}
