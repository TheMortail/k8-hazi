package eu.pontsystems.hazi.service;

import eu.pontsystems.hazi.dto.AdDto;
import eu.pontsystems.hazi.persistence.entity.Ad;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Consumer {
    private final AdService adService;

    @KafkaListener(topics = "hazi", groupId = "group_id")
    public void consume(AdDto dto) {
       Ad ad = new Ad(UUID.randomUUID(), dto.getName(), dto.getDescription());
       System.out.println("Got data from kafka"+dto);
       adService.save(ad);
    }

}
