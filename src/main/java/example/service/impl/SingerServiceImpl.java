package example.service.impl;

import com.google.gson.Gson;
import example.model.service.SingerServiceModel;
import example.repository.SingerRepository;
import example.model.entity.SingerEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import example.service.SingerService;

import org.springframework.core.io.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {
    private final SingerRepository singerRepository;
    private final Gson gson;
    private final Resource singerFiles;
    private final ModelMapper modelMapper;

    public SingerServiceImpl(SingerRepository singerRepository, Gson gson, @Value("classpath:init/singer.json") Resource singerFiles, ModelMapper modelMapper) {
        this.singerRepository = singerRepository;
        this.gson = gson;
        this.singerFiles = singerFiles;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedSinger() {
        if (singerRepository.count() == 0){
            try {
                SingerEntity[] singerEntities =
                        gson.fromJson(Files.readString(Path.of(singerFiles.getURI())), SingerEntity[].class);

                Arrays.stream(singerEntities)
                        .forEach(singerRepository::save);

            } catch (Exception e){
                throw new IllegalStateException("Cannot seed singers!");
            }
        }
    }

    @Override
    public List<String> findAllSingers() {
        return singerRepository.findAllSingers();
    }

    @Override
    public SingerEntity findByName(String singerEntity) {
        return singerRepository.findByName(singerEntity)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public boolean containsSinger(String name) {
        return singerRepository.findSingerEntityByName(name).isPresent();
    }

    @Override
    public void addSinger(SingerServiceModel singerServiceModel) {
        singerRepository.save(modelMapper.map(singerServiceModel, SingerEntity.class));
    }

    @Override
    public void deleteSinger(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public List<SingerEntity> findAll() {
        return singerRepository.findAll();
    }
}
