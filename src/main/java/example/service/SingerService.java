package example.service;

import example.model.entity.SingerEntity;
import example.model.service.SingerServiceModel;

import java.util.List;

public interface SingerService {
    void seedSinger();

    List<String> findAllSingers();

    SingerEntity findByName(String singerEntity);

    boolean containsSinger(String name);

    void addSinger(SingerServiceModel singerServiceModel);

    void deleteSinger(Long id);

    List<SingerEntity> findAll();
}

