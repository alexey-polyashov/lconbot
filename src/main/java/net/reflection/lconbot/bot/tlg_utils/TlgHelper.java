package net.reflection.lconbot.bot.tlg_utils;

import lombok.RequiredArgsConstructor;
import net.reflection.lconbot.bot.BotEvent;
import net.reflection.lconbot.bot.TlgFeign;
import net.reflection.lconbot.config.TelegramConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class TlgHelper {

    private final TelegramConfig telegramConfig;
    private final TlgFeign tlgFeign;

    public InlineKeyboardMarkup getInlineKeyboard(List<Map<String,String>> keysRows){

       List<List<InlineKeyboardButton>> kRows = new ArrayList<>();

        for (Map<String, String> rowMap : keysRows) {

            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();

            for (Map.Entry<String, String> kv : rowMap.entrySet()){
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(kv.getKey());
                button.setCallbackData(kv.getValue());
                keyboardButtonsRow.add(button);
            }

            kRows.add(keyboardButtonsRow);
        }

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(kRows);

        return inlineKeyboardMarkup;
    }

    public void sendMessageHere(SendMessage preparedMessage, Long chatId){
        tlgFeign.sendTextMessage(telegramConfig.getBotToken(), chatId ,preparedMessage.getText());
    }

    public String getCallBackCode(BotEvent event, String step, String code){
        return event.toString() + "#" + step + "#" + code;
    }

    public String getStepFromCallBack(String callBack){
        String[] parts = callBack.split("#");
        if(parts.length>1){
            return parts[1];
        }
        return "";
    }

    public String getCodeFromCallBack(String callBack){
        String[] parts = callBack.split("#");
        if(parts.length>2){
            return parts[2];
        }
        return "";
    }

    public BotEvent getEventFromCallBack(String callBack){
        String[] parts = callBack.split("#");
        if(parts.length>0){
            return BotEvent.valueOf(parts[0]);
        }
        return BotEvent.UNKNOWN;
    }

}
