package example.service;

import example.model.service.LogServiceModel;

import java.util.List;

public interface LogService {
    void createLog(String action, Long songId);

    List<LogServiceModel> findAllLogs();
}
