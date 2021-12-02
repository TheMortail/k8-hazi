package eu.pontsystems.hazi.controller;

import eu.pontsystems.hazi.dto.AdDto;
import eu.pontsystems.hazi.service.Producer;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MiddleController {
    private final Producer producer;

    @PostMapping(value = "/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody AdDto dto) {
        producer.sendMessageToTopic(dto);
    }



}
