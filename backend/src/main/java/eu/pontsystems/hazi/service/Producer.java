package eu.pontsystems.hazi.service;

import eu.pontsystems.hazi.dto.AdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    @Autowired
    private KafkaTemplate<String, AdDto> kafkaTemplate;

    public void sendMessageToTopic(final AdDto dto) {
        System.out.println("Send data to kafka "+dto.toString());
        kafkaTemplate.send("hazi", dto);
    }
}
