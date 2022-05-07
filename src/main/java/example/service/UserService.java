package example.service;

import example.model.entity.UserEntity;
import example.model.service.UserServiceModel;

public interface UserService {
    boolean userExist(String username);

    void registerAndLogin(UserServiceModel userServiceModel);

    void seedUsers();

    UserEntity findByName(String username);
}
