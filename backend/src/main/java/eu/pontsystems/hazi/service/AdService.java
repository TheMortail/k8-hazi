package eu.pontsystems.hazi.service;

import eu.pontsystems.hazi.persistence.entity.Ad;

import java.util.List;

public interface AdService {
    void save(Ad ad);
    List<Ad> getAll();
}
