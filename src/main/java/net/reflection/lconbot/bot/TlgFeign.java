package net.reflection.lconbot.bot;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="forex-service", url="https://api.telegram.org")
public interface TlgFeign {

    @GetMapping("/bot{apiKey}/sendMessage?chat_id={chatId}&text={text}")
    void sendTextMessage(@PathVariable("apiKey") String apiKey,
                         @PathVariable("chatId") Long chatId,
                         @PathVariable("text") String text
                         );

}
