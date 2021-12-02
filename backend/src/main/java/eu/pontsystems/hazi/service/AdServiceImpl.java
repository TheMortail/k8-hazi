package eu.pontsystems.hazi.service;

import eu.pontsystems.hazi.persistence.entity.Ad;
import eu.pontsystems.hazi.persistence.repository.AdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService{
    private final AdsRepository repository;

    @Override
    public void save(Ad ad) {
        repository.save(ad);
    }

    @Override
    public List<Ad> getAll() {
        return repository.findAll();
    }


}
