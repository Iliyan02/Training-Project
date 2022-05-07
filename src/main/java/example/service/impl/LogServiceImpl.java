package example.service.impl;

import example.model.entity.LogEntity;
import example.model.entity.SongEntity;
import example.model.entity.UserEntity;
import example.model.service.LogServiceModel;
import example.repository.LogRepository;
import example.service.LogService;
import example.service.SongsService;
import example.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {
    private final SongsService songsService;
    private final UserService userService;
    private final LogRepository logRepository;
    private final ModelMapper modelMapper;

    public LogServiceImpl(SongsService songsService, UserService userService, LogRepository logRepository, ModelMapper modelMapper) {
        this.songsService = songsService;
        this.userService = userService;
        this.logRepository = logRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void createLog(String action, Long songId) {
        SongEntity songEntity = songsService
                .findEntityById(songId);

        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();

        String username = authentication.getName();

        UserEntity userEntity = userService.findByName(username);

        LogEntity logEntity = new LogEntity()
                .setSongEntity(songEntity)
                .setUserEntity(userEntity)
                .setAction(action)
                .setDateTime(LocalDateTime.now());

        logRepository.save(logEntity);
    }

    @Override
    public List<LogServiceModel> findAllLogs() {
        return logRepository
                .findAll()
                .stream()
                .map(logEntity -> {
                    LogServiceModel logServiceModel = modelMapper
                            .map(logEntity, LogServiceModel.class);
                    logServiceModel.setSong(logEntity.getSongEntity().getName())
                            .setUser(logEntity.getUserEntity().getUsername());

                    return logServiceModel;
                }).collect(Collectors.toList());
    }
}
