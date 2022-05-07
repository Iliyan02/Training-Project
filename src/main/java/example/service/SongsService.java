package example.service;

import example.model.entity.SongEntity;
import example.model.service.SongServiceModel;
import example.model.view.SongViewModel;

import java.util.List;

public interface SongsService {
    boolean containsSong(String name);

    void createSong(SongServiceModel songServiceModel);

    SongEntity findEntityById(Long songId);

    void deleteSong(Long id);

    List<SongEntity> findAll();

//    List<SongEntity> findAll();

}
