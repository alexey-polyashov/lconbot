package net.reflection.lconbot.bot.action;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PreparedMesages {

    private List<SendMessage> messages = new ArrayList<>();

    public PreparedMesages() {
        this.messages = new ArrayList<>();
    }

    void add(SendMessage sm){
        messages.add(sm);
    }
}
