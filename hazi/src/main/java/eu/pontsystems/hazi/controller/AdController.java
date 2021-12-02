package eu.pontsystems.hazi.controller;

import eu.pontsystems.hazi.service.AdService;
import eu.pontsystems.hazi.persistence.entity.Ad;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController("/api/ad")
@AllArgsConstructor
public class AdController {
    private final AdService adService;

    @GetMapping("/api/ad/getAll")
    public ResponseEntity<List<Ad>> getAll(){
        return ResponseEntity.of(Optional.of(adService.getAll()));
    }
}
