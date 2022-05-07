package example.service.impl;

import example.model.entity.SingerEntity;
import example.model.entity.SongEntity;
import example.model.entity.UserEntity;
import example.model.service.SongServiceModel;
import example.repository.SongRepository;
import example.repository.UserRepository;
import example.service.SingerService;
import example.service.SongsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongsServiceImpl implements SongsService {
    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SingerService singerService;

    public SongsServiceImpl(SongRepository songRepository, ModelMapper modelMapper, UserRepository userRepository, SingerService singerService) {
        this.songRepository = songRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.singerService = singerService;
    }

    @Override
    public boolean containsSong(String name) {
        return songRepository.findByName(name).isPresent();
    }

    @Override
    public void createSong(SongServiceModel songServiceModel) {
        SongEntity songEntity =modelMapper.map(songServiceModel, SongEntity.class);
        UserEntity creator = userRepository.findByUsername(songServiceModel.getUser())
                .orElseThrow(() -> new IllegalArgumentException("Creator " + songServiceModel.getUser() + " could not be found."));


        songEntity.setUserEntity(creator);

        SingerEntity singerEntity = singerService.findByName(songServiceModel.getSingerEntity());

        songEntity.setSingerEntity(singerEntity);
        this.songRepository.save(songEntity);
    }

    @Override
    public SongEntity findEntityById(Long songId) {
        return songRepository.findById(songId)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public List<SongEntity> findAll() {
        return songRepository.findAll()
                .stream().map(song -> modelMapper.map(song, SongEntity.class))
                .collect(Collectors.toList());
    }

//    @Override
//    public List<SongEntity> findAll() {
//        return songRepository.findAll()
//                .stream().map(song -> modelMapper.map(song, SongEntity.class))
//                .collect(Collectors.toList());
//    }


}
